package casanovaProject;

public class Product extends BaseInventoryItem {
    private String brand;
    private String supplier;
    
    public Product(String productId, String productName, String brand, int quantity, double price, String category, String supplier) {
        super(productId, productName, price, quantity, category);
        this.brand = brand;
        this.supplier = supplier;
    }
    
    public String getProductId() {
        return getId();
    }
    
    public String getProductName() {
        return getName();
    }
    
    public String getBrand() {
        return brand;
    }
    
    public String getSupplier() {
        return supplier;
    }
    
    @Override
    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            super.setQuantity(quantity);
        } else {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }
    
    // Additional methods specific to Product
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}
