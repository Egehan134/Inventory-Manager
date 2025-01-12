package casanovaProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:mobilestore.db";
    private Connection connection;
    
    public DatabaseManager() {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            // Establish connection
            connection = DriverManager.getConnection(DB_URL);
            createTables();
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void createTables() {
        try {
            Statement statement = connection.createStatement();
            
            // Create Products table
            statement.execute("""
                CREATE TABLE IF NOT EXISTS Products (
                    productId TEXT PRIMARY KEY,
                    productName TEXT NOT NULL,
                    brand TEXT NOT NULL,
                    quantity INTEGER NOT NULL,
                    price REAL NOT NULL,
                    category TEXT NOT NULL,
                    supplier TEXT NOT NULL
                )
            """);
            
            // Create Suppliers table
            statement.execute("""
                CREATE TABLE IF NOT EXISTS Suppliers (
                    supplierId TEXT PRIMARY KEY,
                    supplierName TEXT NOT NULL,
                    contact TEXT
                )
            """);
            
            // Create Sales table
            statement.execute("""
                CREATE TABLE IF NOT EXISTS Sales (
                    saleId INTEGER PRIMARY KEY AUTOINCREMENT,
                    productId TEXT NOT NULL,
                    quantity INTEGER NOT NULL,
                    date TEXT NOT NULL,
                    totalPrice REAL NOT NULL,
                    FOREIGN KEY(productId) REFERENCES Products(productId)
                )
            """);
            
        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Add a new product to database
    public void addProduct(Product product) {
        try {
            String sql = """
                INSERT INTO Products (productId, productName, brand, quantity, price, category, supplier) 
                VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
            
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, product.getProductId());
            pstmt.setString(2, product.getProductName());
            pstmt.setString(3, product.getBrand());
            pstmt.setInt(4, product.getQuantity());
            pstmt.setDouble(5, product.getPrice());
            pstmt.setString(6, product.getCategory());
            pstmt.setString(7, product.getSupplier());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Update product quantity
    public void updateQuantity(String productId, int newQuantity) {
        try {
            String sql = "UPDATE Products SET quantity = ? WHERE productId = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, newQuantity);
            pstmt.setString(2, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Get all products
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM Products";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Product product = new Product(
                    rs.getString("productId"),
                    rs.getString("productName"),
                    rs.getString("brand"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getString("category"),
                    rs.getString("supplier")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }
    
    // Get product by ID
    public Product getProductById(String productId) {
        try {
            String sql = "SELECT * FROM Products WHERE productId = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, productId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return new Product(
                    rs.getString("productId"),
                    rs.getString("productName"),
                    rs.getString("brand"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getString("category"),
                    rs.getString("supplier")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    // Delete product
    public void deleteProduct(String productId) {
        try {
            String sql = "DELETE FROM Products WHERE productId = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, productId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Supplier Methods
    public void addSupplier(Supplier supplier) {
        try {
            String sql = "INSERT INTO Suppliers (supplierId, supplierName, contact) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, supplier.getSupplierId());
            pstmt.setString(2, supplier.getSupplierName());
            pstmt.setString(3, supplier.getContact());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Suppliers";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                suppliers.add(new Supplier(
                    rs.getString("supplierId"),
                    rs.getString("supplierName"),
                    rs.getString("contact")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }
    
    public void updateSupplier(Supplier supplier) {
        try {
            String sql = "UPDATE Suppliers SET supplierName = ?, contact = ? WHERE supplierId = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, supplier.getSupplierName());
            pstmt.setString(2, supplier.getContact());
            pstmt.setString(3, supplier.getSupplierId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteSupplier(String supplierId) {
        try {
            String sql = "DELETE FROM Suppliers WHERE supplierId = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, supplierId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Sales Methods
    public void addSale(SalesRecord sale) {
        try {
            connection.setAutoCommit(false);  // Start transaction
            
            try {
                // First update product quantity
                String updateProduct = "UPDATE Products SET quantity = quantity - ? WHERE productId = ?";
                PreparedStatement updateStmt = connection.prepareStatement(updateProduct);
                updateStmt.setInt(1, sale.getQuantity());
                updateStmt.setString(2, sale.getProductId());
                int rowsAffected = updateStmt.executeUpdate();
                
                if (rowsAffected == 0) {
                    throw new SQLException("Product not found or update failed");
                }
                
                // Then record the sale
                String sql = "INSERT INTO Sales (productId, quantity, date, totalPrice) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, sale.getProductId());
                pstmt.setInt(2, sale.getQuantity());
                pstmt.setString(3, sale.getDate());
                pstmt.setDouble(4, sale.getTotalPrice());
                pstmt.executeUpdate();
                
                connection.commit();  // Commit transaction
            } catch (SQLException e) {
                connection.rollback();  // Rollback on error
                throw e;
            } finally {
                connection.setAutoCommit(true);  // Reset auto-commit
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to record sale: " + e.getMessage());
        }
    }
    
    public List<SalesRecord> getAllSales() {
        List<SalesRecord> sales = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Sales";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                sales.add(new SalesRecord(
                    rs.getString("productId"),
                    rs.getInt("quantity"),
                    rs.getString("date"),
                    rs.getDouble("totalPrice")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sales;
    }
    
    public void deleteSale(String saleId) {
        try {
            String sql = "DELETE FROM Sales WHERE saleId = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, saleId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Close database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateProduct(Product product) {
        try {
            String sql = "UPDATE Products SET productName = ?, brand = ?, quantity = ?, price = ?, category = ?, supplier = ? WHERE productId = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, product.getProductName());
            pstmt.setString(2, product.getBrand());
            pstmt.setInt(3, product.getQuantity());
            pstmt.setDouble(4, product.getPrice());
            pstmt.setString(5, product.getCategory());
            pstmt.setString(6, product.getSupplier());
            pstmt.setString(7, product.getProductId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void updateSale(SalesRecord sale, String saleId) {
        try {
            connection.setAutoCommit(false);  // Start transaction
            
            try {
                // First update the sale record
                String sql = "UPDATE Sales SET productId = ?, quantity = ?, date = ?, totalPrice = ? WHERE saleId = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, sale.getProductId());
                pstmt.setInt(2, sale.getQuantity());
                pstmt.setString(3, sale.getDate());
                pstmt.setDouble(4, sale.getTotalPrice());
                pstmt.setString(5, saleId);
                pstmt.executeUpdate();
                
                connection.commit();  // Commit transaction
            } catch (SQLException e) {
                connection.rollback();  // Rollback on error
                throw e;
            } finally {
                connection.setAutoCommit(true);  // Reset auto-commit
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update sale: " + e.getMessage());
        }
    }
} 