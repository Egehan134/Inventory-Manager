package casanovaProject;
import casanovaProject.Product;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.border.TitledBorder;


import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class inventoryManagementGUI {
	private DatabaseManager dbManager;
	private JFrame frmInventoryManager;
	private JTable table;
	private JTable supplierTable;
	private JTable table_1;  // sales table
	private JTextField txtProductId;
	private JTextField txtProductName;
	private JTextField txtBrand;
	private JTextField txtQuantity;
	private JTextField txtPrice;
	private JTextField txtCategory;
	private JTextField txtSupplierId;
	private JTextField txtSupplierName;
	private JTextField txtSupplierContact;
	private JTextField txtTotalPrice;
	private JTextField txtDate;
	private JTextField txtSalesQuantity;
	private JComboBox<String> comboBoxSupplier;
	private JComboBox<String> comboBox_1; 
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inventoryManagementGUI window = new inventoryManagementGUI();
					window.frmInventoryManager.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public inventoryManagementGUI() {
		dbManager = new DatabaseManager();  // Initialize database connection
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frmInventoryManager = new JFrame();
		frmInventoryManager.setTitle("Inventory Manager");
		frmInventoryManager.setResizable(false);
		frmInventoryManager.getContentPane().setBackground(new Color(106, 86, 80));
		frmInventoryManager.setBackground(new Color(2, 46, 81));
		frmInventoryManager.setBounds(100, 100, 1218, 763);
		frmInventoryManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmInventoryManager.getContentPane().setLayout(null);
		JPanel inventoryPanel = new JPanel();
		inventoryPanel.setBackground(new Color(176, 147, 130));
		
		        JPanel suppliersPanel = new JPanel();
		        suppliersPanel.setBackground(new Color(176, 147, 130));
		        
		                JPanel salesPanel = new JPanel();
		                salesPanel.setBackground(new Color(176, 147, 130));
		                
		                JPanel panelGenel = new JPanel();
		                panelGenel.setBackground(new Color(166, 119, 99));
		                panelGenel.setBounds(10, 53, 1184, 663);
		                frmInventoryManager.getContentPane().add(panelGenel);
		                panelGenel.setLayout(new CardLayout(0, 0));
		                
		                panelGenel.add(inventoryPanel, "Inventory");
		                inventoryPanel.setLayout(null);
		                
		                JScrollPane scrollPaneInventory = new JScrollPane();
		                scrollPaneInventory.setBounds(10, 30, 750, 620);
		                inventoryPanel.add(scrollPaneInventory);
		                scrollPaneInventory.setBorder(BorderFactory.createEmptyBorder());
		                
		                table = new JTable();
		                JTableHeader header = table.getTableHeader();
		                header.setFont(new Font("SansSerif", Font.BOLD, 14));
		                header.setBackground(Color.DARK_GRAY);
		                header.setForeground(Color.WHITE);
		                header.setReorderingAllowed(false); 
		                table.setRowHeight(25); 
		                table.setIntercellSpacing(new Dimension(10, 5)); 
		                table.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
		                table.setBackground(new Color(255, 255, 255));
		                table.setForeground(new Color(0, 0, 0));
		                DefaultTableModel model = new DefaultTableModel(
		                	    new Object[][] {},
		                	    new String[] {
		                	        "Product ID", "Product Name", "Quantity", "Price", "Category", "Supplier"
		                	    });
		                table.setModel(model);
		                table.getColumnModel().getColumn(2).setPreferredWidth(53);
		                table.setColumnSelectionAllowed(true);
		                table.setCellSelectionEnabled(true);
		                scrollPaneInventory.setViewportView(table);
		                
		                JPanel panel_1 = new JPanel();
		                panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Inventory Management", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		                panel_1.setBackground(new Color(176, 147, 130));
		                panel_1.setBounds(770, 25, 400, 625);
		                inventoryPanel.add(panel_1);
		                panel_1.setLayout(null);
		                
		                txtProductId = new JTextField();
		                txtProductId.setBackground(new Color(255, 255, 255));
		                txtProductId.setFont(new Font("Calibri", Font.BOLD, 15));
		                txtProductId.setBounds(145, 20, 245, 25);
		                panel_1.add(txtProductId);
		                txtProductId.setColumns(10);
		                
		                txtProductName = new JTextField();
		                txtProductName.setFont(new Font("Calibri", Font.BOLD, 15));
		                txtProductName.setBounds(145, 50, 245, 25);
		                panel_1.add(txtProductName);
		                txtProductName.setColumns(10);
		                
		                txtQuantity = new JTextField();
		                txtQuantity.setFont(new Font("Calibri", Font.BOLD, 15));
		                txtQuantity.setBounds(145, 80, 245, 25);
		                panel_1.add(txtQuantity);
		                txtQuantity.setColumns(10);
		                
		                txtPrice = new JTextField();
		                txtPrice.setFont(new Font("Calibri", Font.BOLD, 15));
		                txtPrice.setBounds(145, 110, 245, 25);
		                panel_1.add(txtPrice);
		                txtPrice.setColumns(10);
		                
		                txtCategory = new JTextField();
		                txtCategory.setFont(new Font("Calibri", Font.BOLD, 15));
		                txtCategory.setBounds(145, 140, 245, 25);
		                panel_1.add(txtCategory);
		                txtCategory.setColumns(10);
		                
		                comboBoxSupplier = new JComboBox<>();
		                comboBoxSupplier.setBounds(145, 170, 245, 25);
		                panel_1.add(comboBoxSupplier);
		                
		                JLabel lblNewLabel = new JLabel("Product ID:");
		                lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		                lblNewLabel.setBounds(10, 20, 125, 25);
		                panel_1.add(lblNewLabel);
		                
		                JLabel lblNewLabel_1 = new JLabel("Product Name:");
		                lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 17));
		                lblNewLabel_1.setBounds(10, 50, 125, 25);
		                panel_1.add(lblNewLabel_1);
		                
		                JLabel lblNewLabel_2 = new JLabel("Quantity:");
		                lblNewLabel_2.setFont(new Font("Segoe UI", Font.BOLD, 17));
		                lblNewLabel_2.setBounds(10, 80, 125, 25);
		                panel_1.add(lblNewLabel_2);
		                
		                JLabel lblNewLabel_3 = new JLabel("Price:");
		                lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 17));
		                lblNewLabel_3.setBounds(10, 110, 125, 25);
		                panel_1.add(lblNewLabel_3);
		                
		                JLabel lblNewLabel_4 = new JLabel("Category:");
		                lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 17));
		                lblNewLabel_4.setBounds(10, 140, 125, 25);
		                panel_1.add(lblNewLabel_4);
		                
		                JLabel lblNewLabel_5 = new JLabel("Supplier:");
		                lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 17));
		                lblNewLabel_5.setBounds(10, 170, 125, 25);
		                panel_1.add(lblNewLabel_5);
		                
		                JButton btnInventoryAdd = new JButton("Add");
		                btnInventoryAdd.setBackground(new Color(176, 147, 130));
		                btnInventoryAdd.setBounds(300, 13, 145, 30);
		                btnInventoryAdd.setOpaque(true); btnInventoryAdd.setBorderPainted(true); btnInventoryAdd.setBorder(null); btnInventoryAdd.setFocusPainted(false);
		                btnInventoryAdd.setFont(new Font("Segoe UI", Font.BOLD, 20));
		                btnInventoryAdd.setBorder(BorderFactory.createLineBorder(new Color(106, 86, 80))); btnInventoryAdd.setContentAreaFilled(false);
		                	
		                btnInventoryAdd.setBounds(10, 520, 125, 40);
		                panel_1.add(btnInventoryAdd);
		                
		                JButton btnEdit = new JButton("Edit");
		                btnEdit.setBackground(new Color(176, 147, 130));
		                btnEdit.setOpaque(true);
		                btnEdit.setBorderPainted(true);
		                btnEdit.setBorder(null);
		                btnEdit.setFocusPainted(false);
		                btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 20));
		                btnEdit.setBorder(BorderFactory.createLineBorder(new Color(106, 86, 80)));
		                btnEdit.setContentAreaFilled(false);
		                btnEdit.setBackground(new Color(176, 147, 130));
		                btnEdit.setBounds(270, 520, 120, 40);
		                panel_1.add(btnEdit);
		                
		                JButton btnDelete = new JButton("Delete");
		                btnDelete.setBackground(new Color(176, 147, 130));
		                btnDelete.setOpaque(true);
		                btnDelete.setBorderPainted(true);
		                btnDelete.setBorder(null);
		                btnDelete.setFocusPainted(false);
		                btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 20));
		                btnDelete.setBorder(BorderFactory.createLineBorder(new Color(106, 86, 80)));
		                btnDelete.setContentAreaFilled(false);
		                btnDelete.setBackground(new Color(176, 147, 130));
		                btnDelete.setBounds(145, 520, 115, 40);
		                panel_1.add(btnDelete);
		                
		                JButton btnInventoryClear = new JButton("Clear");
		                btnInventoryClear.addActionListener(new ActionListener() {
		                	public void actionPerformed(ActionEvent e) {
		                		clearFields();
		                	}
		                });
		                btnInventoryClear.setBounds(10, 570, 380, 40);
		                btnInventoryClear.setBackground(new Color(176, 147, 130));
		                btnInventoryClear.setOpaque(true);
		                btnInventoryClear.setBorderPainted(true);
		                btnInventoryClear.setBorder(null);
		                btnInventoryClear.setFocusPainted(false);
		                btnInventoryClear.setFont(new Font("Segoe UI", Font.BOLD, 20));
		                btnInventoryClear.setBorder(BorderFactory.createLineBorder(new Color(106, 86, 80)));
		                btnInventoryClear.setContentAreaFilled(false);

		                panel_1.add(btnInventoryClear);
		                panelGenel.add(suppliersPanel, "Suppliers");
		                suppliersPanel.setLayout(null);
		                
		                JScrollPane scrollPaneSuppliers = new JScrollPane();
		                scrollPaneSuppliers.setBounds(10, 30, 750, 620);
		                suppliersPanel.add(scrollPaneSuppliers);
		                scrollPaneSuppliers.setBorder(BorderFactory.createEmptyBorder());
		                
		                supplierTable = new JTable();
		                JTableHeader header1 = supplierTable.getTableHeader();
		                header1.setFont(new Font("SansSerif", Font.BOLD, 14));
		                header1.setBackground(Color.DARK_GRAY);
		                header1.setForeground(Color.WHITE);
		                header1.setReorderingAllowed(false); 
		                supplierTable.setRowHeight(25); 
		                supplierTable.setIntercellSpacing(new Dimension(10, 5)); 
		                supplierTable.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
		                supplierTable.setForeground(new Color(0, 0, 0));
		                DefaultTableModel supplierModel = new DefaultTableModel(
		                    new Object[][] {},
		                    new String[] {
		                        "Supplier ID", "Supplier Name", "Contact"
		                    }
		                );
		                supplierTable.setModel(supplierModel);
		                scrollPaneSuppliers.setViewportView(supplierTable);
		                
		                JPanel panel_2 = new JPanel();
		                panel_2.setBackground(new Color(176, 147, 130));
		                panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Suppliers Management", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		                panel_2.setBounds(770, 25, 400, 625);
		                suppliersPanel.add(panel_2);
		                panel_2.setLayout(null);
		                
		                JButton btnSupplierAdd = new JButton("Add");
		                btnSupplierAdd.setBackground(new Color(176, 147, 130));
		                btnSupplierAdd.setBounds(10, 520, 125, 40);
		                btnSupplierAdd.setOpaque(true);
		                btnSupplierAdd.setBorderPainted(true);
		                btnSupplierAdd.setBorder(null);
		                btnSupplierAdd.setFocusPainted(false);
		                btnSupplierAdd.setFont(new Font("Segoe UI", Font.BOLD, 20));
		                btnSupplierAdd.setBorder(BorderFactory.createLineBorder(new Color(106, 86, 80)));
		                btnSupplierAdd.setContentAreaFilled(false);
		                panel_2.add(btnSupplierAdd);

		                JButton btnSupplierDelete = new JButton("Delete");
		                btnSupplierDelete.setBackground(new Color(176, 147, 130));
		                btnSupplierDelete.setOpaque(true);
		                btnSupplierDelete.setBorderPainted(true);
		                btnSupplierDelete.setBorder(null);
		                btnSupplierDelete.setFocusPainted(false);
		                btnSupplierDelete.setFont(new Font("Segoe UI", Font.BOLD, 20));
		                btnSupplierDelete.setBorder(BorderFactory.createLineBorder(new Color(106, 86, 80)));
		                btnSupplierDelete.setContentAreaFilled(false);
		                btnSupplierDelete.setBounds(145, 520, 115, 40);
		                panel_2.add(btnSupplierDelete);

		                JButton btnSupplierEdit = new JButton("Edit");
		                btnSupplierEdit.setBackground(new Color(176, 147, 130));
		                btnSupplierEdit.setOpaque(true);
		                btnSupplierEdit.setBorderPainted(true);
		                btnSupplierEdit.setBorder(null);
		                btnSupplierEdit.setFocusPainted(false);
		                btnSupplierEdit.setFont(new Font("Segoe UI", Font.BOLD, 20));
		                btnSupplierEdit.setBorder(BorderFactory.createLineBorder(new Color(106, 86, 80)));
		                btnSupplierEdit.setContentAreaFilled(false);
		                btnSupplierEdit.setBounds(270, 520, 120, 40);
		                panel_2.add(btnSupplierEdit);

		                JButton btnSupplierClear = new JButton("Clear");
		                btnSupplierClear.setBackground(new Color(176, 147, 130));
		                btnSupplierClear.setOpaque(true);
		                btnSupplierClear.setBorderPainted(true);
		                btnSupplierClear.setBorder(null);
		                btnSupplierClear.setFocusPainted(false);
		                btnSupplierClear.setFont(new Font("Segoe UI", Font.BOLD, 20));
		                btnSupplierClear.setBorder(BorderFactory.createLineBorder(new Color(106, 86, 80)));
		                btnSupplierClear.setContentAreaFilled(false);
		                btnSupplierClear.setBounds(10, 570, 380, 40);
		                panel_2.add(btnSupplierClear);
		                
		                JLabel lblNewLabel_6 = new JLabel("Supplier ID:");
		                lblNewLabel_6.setFont(new Font("Segoe UI", Font.BOLD, 17));
		                lblNewLabel_6.setBounds(10, 20, 125, 25);
		                panel_2.add(lblNewLabel_6);
		                
		                txtSupplierId = new JTextField();
		                txtSupplierId.setFont(new Font("Calibri", Font.BOLD, 15));
		                txtSupplierId.setColumns(10);
		                txtSupplierId.setBackground(Color.WHITE);
		                txtSupplierId.setBounds(145, 20, 245, 25);
		                panel_2.add(txtSupplierId);
		                
		                JLabel lblNewLabel_1_2 = new JLabel("Supplier Name:");
		                lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.BOLD, 17));
		                lblNewLabel_1_2.setBounds(10, 50, 125, 25);
		                panel_2.add(lblNewLabel_1_2);
		                
		                txtSupplierName = new JTextField();
		                txtSupplierName.setFont(new Font("Calibri", Font.BOLD, 15));
		                txtSupplierName.setColumns(10);
		                txtSupplierName.setBounds(145, 50, 245, 25);
		                panel_2.add(txtSupplierName);
		                
		                JLabel lblNewLabel_2_2 = new JLabel("Contact:");
		                lblNewLabel_2_2.setFont(new Font("Segoe UI", Font.BOLD, 17));
		                lblNewLabel_2_2.setBounds(10, 80, 125, 25);
		                panel_2.add(lblNewLabel_2_2);
		                
		                txtSupplierContact = new JTextField();
		                txtSupplierContact.setFont(new Font("Calibri", Font.BOLD, 15));
		                txtSupplierContact.setColumns(10);
		                txtSupplierContact.setBounds(145, 80, 245, 25);
		                panel_2.add(txtSupplierContact);
		                panelGenel.add(salesPanel, "Sales");
		                salesPanel.setLayout(null);
		                
		                JScrollPane scrollPaneSales = new JScrollPane();
		                scrollPaneSales.setBounds(10, 30, 750, 620);
		                salesPanel.add(scrollPaneSales);
		                scrollPaneSales.setBorder(BorderFactory.createEmptyBorder());
		                
		                table_1 = new JTable();
		                JTableHeader header2 = table_1.getTableHeader();
		                header2.setFont(new Font("SansSerif", Font.BOLD, 14));
		                header2.setBackground(Color.DARK_GRAY);
		                header2.setForeground(Color.WHITE);
		                header2.setReorderingAllowed(false); 
		                table_1.setRowHeight(25); 
		                table_1.setIntercellSpacing(new Dimension(10, 5)); 
		                table_1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
		                table_1.setForeground(new Color(0, 0, 0));
		                DefaultTableModel salesModel = new DefaultTableModel(
		                    new Object[][] {},
		                    new String[] {
		                        "Sale ID", "Product", "Quantity", "Date", "Total Price"
		                    }
		                );
		                table_1.setModel(salesModel);
		                scrollPaneSales.setViewportView(table_1);
		                
		                JPanel panel_3 = new JPanel();
		                panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Sales Records Management", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		                panel_3.setBackground(new Color(176, 147, 130));
		                panel_3.setBounds(770, 25, 400, 625);
		                salesPanel.add(panel_3);
		                panel_3.setLayout(null);
		                
		                comboBox_1 = new JComboBox<>();
		                comboBox_1.setFont(new Font("Calibri", Font.BOLD, 15));
		                comboBox_1.setBounds(146, 20, 245, 25);
		                panel_3.add(comboBox_1);
		                
		                JButton btnSalesRecordsAdd = new JButton("Add");
		                btnSalesRecordsAdd.setBackground(new Color(176, 147, 130));
		                btnSalesRecordsAdd.setOpaque(true);
		                btnSalesRecordsAdd.setBorderPainted(true);
		                btnSalesRecordsAdd.setBorder(null);
		                btnSalesRecordsAdd.setFocusPainted(false);
		                btnSalesRecordsAdd.setFont(new Font("Segoe UI", Font.BOLD, 20));
		                btnSalesRecordsAdd.setBorder(BorderFactory.createLineBorder(new Color(106, 86, 80)));
		                btnSalesRecordsAdd.setContentAreaFilled(false);
		                btnSalesRecordsAdd.setBounds(10, 520, 125, 40);
		                panel_3.add(btnSalesRecordsAdd);

		                JButton btnSalesRecordsDelete = new JButton("Delete");
		                btnSalesRecordsDelete.setBackground(new Color(176, 147, 130));
		                btnSalesRecordsDelete.setOpaque(true);
		                btnSalesRecordsDelete.setBorderPainted(true);
		                btnSalesRecordsDelete.setBorder(null);
		                btnSalesRecordsDelete.setFocusPainted(false);
		                btnSalesRecordsDelete.setFont(new Font("Segoe UI", Font.BOLD, 20));
		                btnSalesRecordsDelete.setBorder(BorderFactory.createLineBorder(new Color(106, 86, 80)));
		                btnSalesRecordsDelete.setContentAreaFilled(false);
		                btnSalesRecordsDelete.setBounds(145, 520, 115, 40);
		                panel_3.add(btnSalesRecordsDelete);

		                JButton btnSalesRecordsClear = new JButton("Clear");
		                btnSalesRecordsClear.setBackground(new Color(176, 147, 130));
		                btnSalesRecordsClear.setOpaque(true);
		                btnSalesRecordsClear.setBorderPainted(true);
		                btnSalesRecordsClear.setBorder(null);
		                btnSalesRecordsClear.setFocusPainted(false);
		                btnSalesRecordsClear.setFont(new Font("Segoe UI", Font.BOLD, 20));
		                btnSalesRecordsClear.setBorder(BorderFactory.createLineBorder(new Color(106, 86, 80)));
		                btnSalesRecordsClear.setContentAreaFilled(false);
		                btnSalesRecordsClear.setBounds(10, 570, 380, 40);
		                panel_3.add(btnSalesRecordsClear);

		                JButton btnSalesRecordsEdit = new JButton("Edit");
		                btnSalesRecordsEdit.setBackground(new Color(176, 147, 130));
		                btnSalesRecordsEdit.setOpaque(true);
		                btnSalesRecordsEdit.setBorderPainted(true);
		                btnSalesRecordsEdit.setBorder(null);
		                btnSalesRecordsEdit.setFocusPainted(false);
		                btnSalesRecordsEdit.setFont(new Font("Segoe UI", Font.BOLD, 20));
		                btnSalesRecordsEdit.setBorder(BorderFactory.createLineBorder(new Color(106, 86, 80)));
		                btnSalesRecordsEdit.setContentAreaFilled(false);
		                btnSalesRecordsEdit.setBounds(270, 520, 120, 40);
		                panel_3.add(btnSalesRecordsEdit);
		                
		                txtTotalPrice = new JTextField();
		                txtTotalPrice.setFont(new Font("Calibri", Font.BOLD, 15));
		                txtTotalPrice.setColumns(10);
		                txtTotalPrice.setBounds(146, 112, 245, 25);
		                panel_3.add(txtTotalPrice);
		                
		                JLabel lblNewLabel_4_2 = new JLabel("Total Price:");
		                lblNewLabel_4_2.setFont(new Font("Segoe UI", Font.BOLD, 17));
		                lblNewLabel_4_2.setBounds(11, 112, 125, 25);
		                panel_3.add(lblNewLabel_4_2);
		                
		                JLabel lblNewLabel_3_2 = new JLabel("Date:");
		                lblNewLabel_3_2.setFont(new Font("Segoe UI", Font.BOLD, 17));
		                lblNewLabel_3_2.setBounds(11, 82, 125, 25);
		                panel_3.add(lblNewLabel_3_2);
		                
		                txtDate = new JTextField();
		                txtDate.setFont(new Font("Calibri", Font.BOLD, 15));
		                txtDate.setColumns(10);
		                txtDate.setBounds(146, 82, 245, 25);
		                panel_3.add(txtDate);
		                
		                txtSalesQuantity = new JTextField();
		                txtSalesQuantity.setFont(new Font("Calibri", Font.BOLD, 15));
		                txtSalesQuantity.setColumns(10);
		                txtSalesQuantity.setBounds(146, 52, 245, 25);
		                panel_3.add(txtSalesQuantity);
		                
		                JLabel lblNewLabel_2_3 = new JLabel("Quantity:");
		                lblNewLabel_2_3.setFont(new Font("Segoe UI", Font.BOLD, 17));
		                lblNewLabel_2_3.setBounds(11, 52, 125, 25);
		                panel_3.add(lblNewLabel_2_3);
		                
		                JLabel lblNewLabel_1_3 = new JLabel("Item Name:");
		                lblNewLabel_1_3.setFont(new Font("Segoe UI", Font.BOLD, 17));
		                lblNewLabel_1_3.setBounds(11, 22, 125, 25);
		                panel_3.add(lblNewLabel_1_3);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(166, 119, 99));
		panel.setBounds(10, 10, 1184, 50);
		frmInventoryManager.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.setBackground(new Color(176, 147, 130));
		btnInventory.setForeground(new Color(106, 86, 80));
		btnInventory.setFont(new Font("Calisto MT", Font.BOLD, 18));
		btnInventory.setBounds(10, 13, 145, 30);
		btnInventory.setOpaque(true); btnInventory.setBorderPainted(false); btnInventory.setBorder(null); btnInventory.setFocusPainted(false); btnInventory.setFocusPainted(false);
		panel.add(btnInventory);
		
		
		JButton btnSuppliers = new JButton("Suppliers");
		btnSuppliers.setBackground(new Color(156, 109, 89));
		btnSuppliers.setForeground(new Color(106, 86, 80));
		btnSuppliers.setFont(new Font("Calisto MT", Font.BOLD, 18));
		btnSuppliers.setBounds(155, 13, 145, 30);
		btnSuppliers.setOpaque(true); btnSuppliers.setBorderPainted(false); btnSuppliers.setBorder(null); btnSuppliers.setFocusPainted(false); btnSuppliers.setFocusPainted(false);
		panel.add(btnSuppliers);
		
		JButton btnSalesRecords = new JButton("Sales Records");
		btnSalesRecords.setBackground(new Color(156, 109, 89));
		btnSalesRecords.setForeground(new Color(106, 86, 80));
		btnSalesRecords.setFont(new Font("Calisto MT", Font.BOLD, 18));
		btnSalesRecords.setBounds(300, 13, 145, 30);
		btnSalesRecords.setOpaque(true); btnSalesRecords.setBorderPainted(false); btnSalesRecords.setBorder(null); btnSalesRecords.setFocusPainted(false); btnSalesRecords.setFocusPainted(false);
		panel.add(btnSalesRecords);
		                
		btnInventory.addActionListener(e -> switchPanel(panelGenel, "Inventory"));
		btnSuppliers.addActionListener(e -> switchPanel(panelGenel, "Suppliers"));
		btnSalesRecords.addActionListener(e -> switchPanel(panelGenel, "Sales"));
		btnInventory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Buton Inventory'e tıklandığında
            	btnInventory.setBackground(new Color(176, 147, 130));

            	btnSuppliers.setBackground(new Color(156, 109, 89));
 
            	btnSalesRecords.setBackground(new Color(156, 109, 89));
            }
        });
		btnSuppliers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Buton Suppliers'a tıklandığında
            	btnInventory.setBackground(new Color(156, 109, 89));

            	btnSuppliers.setBackground(new Color(176, 147, 130));

            	btnSalesRecords.setBackground(new Color(156, 109, 89));
            }
        });
		btnSalesRecords.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Buton SalesRecords'a tıklandığında
            	btnInventory.setBackground(new Color(156, 109, 89));

            	btnSuppliers.setBackground(new Color(156, 109, 89));

            	btnSalesRecords.setBackground(new Color(176, 147, 130));
            }
        });
		                
		// Add brand field
		txtBrand = new JTextField();
		txtBrand.setEnabled(false);
		txtBrand.setFont(new Font("Calibri", Font.BOLD, 15));
		txtBrand.setBounds(145, 200, 245, 25);
		txtBrand.setVisible(false);
		panel_1.add(txtBrand);
		
		JButton btnExport = new JButton("Export Data");
		btnExport.addActionListener(e -> exportInventoryData());
		btnExport.setBounds(205, 470, 185, 40);
		btnExport.setBackground(new Color(176, 147, 130));
		btnExport.setOpaque(true); btnExport.setBorderPainted(true); btnExport.setBorder(null); btnExport.setFocusPainted(false);
		btnExport.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnExport.setBorder(BorderFactory.createLineBorder(new Color(106, 86, 80))); btnExport.setContentAreaFilled(false);
		panel_1.add(btnExport);
		
		
		JButton btnImport = new JButton("Import Data");
		btnImport.addActionListener(e -> importInventoryData());
		btnImport.setBounds(10, 470, 185, 40);
		btnImport.setBackground(new Color(176, 147, 130));
		btnImport.setOpaque(true); btnImport.setBorderPainted(true); btnImport.setBorder(null); btnImport.setFocusPainted(false);
		btnImport.setFont(new Font("Segoe UI", Font.BOLD, 20));
		btnImport.setBorder(BorderFactory.createLineBorder(new Color(106, 86, 80))); btnImport.setContentAreaFilled(false);
		panel_1.add(btnImport);


		// Modify Add button action
		btnInventoryAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String productId = txtProductId.getText().trim();
                String productName = txtProductName.getText().trim();
                String brand = txtBrand.getText().trim();
                int quantity = Integer.parseInt(txtQuantity.getText().trim());
                double price = Double.parseDouble(txtPrice.getText().trim());
                String category = txtCategory.getText().trim();
                String supplier = comboBoxSupplier.getSelectedItem().toString();
                
                Product product = new Product(productId, productName, brand, quantity, price, category, supplier);
                dbManager.addProduct(product);
                
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[]{
                    product.getProductId(),
                    product.getProductName(),
                    product.getQuantity(),
                    product.getPrice(),
                    product.getCategory(),
                    product.getSupplier()
                });
                
                comboBox_1.addItem(product.getProductName());
                clearFields();
            }
        });

		// Modify Delete button action
		btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                String productId = table.getValueAt(row, 0).toString();
                dbManager.deleteProduct(productId);
                
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                String productName = table.getValueAt(row, 1).toString();
                model.removeRow(row);
                comboBox_1.removeItem(productName);
                clearFields();
            }
        });

		// Modify Edit button action
		btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                String productId = txtProductId.getText().trim();
                String productName = txtProductName.getText().trim();
                String brand = txtBrand.getText().trim();
                int quantity = Integer.parseInt(txtQuantity.getText().trim());
                double price = Double.parseDouble(txtPrice.getText().trim());
                String category = txtCategory.getText().trim();
                String supplier = comboBoxSupplier.getSelectedItem().toString();
                
                Product product = new Product(productId, productName, brand, quantity, price, category, supplier);
                dbManager.updateProduct(product);
                
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setValueAt(productName, row, 1);
                model.setValueAt(quantity, row, 2);
                model.setValueAt(price, row, 3);
                model.setValueAt(category, row, 4);
                model.setValueAt(supplier, row, 5);
                
                // Update product name in sales combo box if it changed
                String oldProductName = model.getValueAt(row, 1).toString();
                if (!oldProductName.equals(productName)) {
                    comboBox_1.removeItem(oldProductName);
                    comboBox_1.addItem(productName);
                }
                
                clearFields();
            }
        });

		// Add supplier button action
		btnSupplierAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String supplierId = txtSupplierId.getText();
                String supplierName = txtSupplierName.getText();
                String contact = txtSupplierContact.getText();
                
                Supplier supplier = new Supplier(supplierId, supplierName, contact);
                dbManager.addSupplier(supplier);
                
                DefaultTableModel model = (DefaultTableModel) supplierTable.getModel();
                model.addRow(new Object[]{
                    supplier.getSupplierId(),
                    supplier.getSupplierName(),
                    supplier.getContact()
                });
                
                comboBoxSupplier.addItem(supplierName);
                clearSupplierFields();
            }
        });

		// Add supplier delete button action
		btnSupplierDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = supplierTable.getSelectedRow();
                String supplierId = supplierTable.getValueAt(row, 0).toString();
                dbManager.deleteSupplier(supplierId);
                
                DefaultTableModel model = (DefaultTableModel) supplierTable.getModel();
                String supplierName = model.getValueAt(row, 1).toString();
                model.removeRow(row);
                comboBoxSupplier.removeItem(supplierName);
                clearSupplierFields();
            }
        });

		// Add supplier edit button action
		btnSupplierEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = supplierTable.getSelectedRow();
                String supplierId = txtSupplierId.getText();
                String supplierName = txtSupplierName.getText();
                String contact = txtSupplierContact.getText();
                
                Supplier supplier = new Supplier(supplierId, supplierName, contact);
                dbManager.updateSupplier(supplier);
                
                DefaultTableModel model = (DefaultTableModel) supplierTable.getModel();
                model.setValueAt(supplierName, row, 1);
                model.setValueAt(contact, row, 2);
                clearSupplierFields();
            }
        });

		// Add supplier clear button action
		btnSupplierClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearSupplierFields();
            }
        });

		// Load existing products and suppliers
		loadExistingProducts();
		loadExistingSuppliers();
		loadExistingSales();

		// Add table selection listener
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = table.getSelectedRow();
                    if (row >= 0) {
                        txtProductId.setText(table.getValueAt(row, 0).toString());
                        txtProductName.setText(table.getValueAt(row, 1).toString());
                        txtQuantity.setText(table.getValueAt(row, 2).toString());
                        txtPrice.setText(table.getValueAt(row, 3).toString());
                        txtCategory.setText(table.getValueAt(row, 4).toString());
                        comboBoxSupplier.setSelectedItem(table.getValueAt(row, 5).toString());
                        Product product = dbManager.getProductById(table.getValueAt(row, 0).toString());
                        if (product != null) {
                            txtBrand.setText(product.getBrand());
                        }
                    }
                }
            }
        });

		// Add supplier table selection listener
		supplierTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = supplierTable.getSelectedRow();
                    if (row >= 0) {
                        txtSupplierId.setText(supplierTable.getValueAt(row, 0).toString());
                        txtSupplierName.setText(supplierTable.getValueAt(row, 1).toString());
                        txtSupplierContact.setText(supplierTable.getValueAt(row, 2).toString());
                    }
                }
            }
        });

		// Populate product combo box in sales panel
		List<Product> products = dbManager.getAllProducts();
		for (Product product : products) {
		    comboBox_1.addItem(product.getProductName());
		}

		// Add sales record button action
		btnSalesRecordsAdd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String productName = comboBox_1.getSelectedItem().toString();
		        List<Product> products = dbManager.getAllProducts();
		        Product selectedProduct = null;
		        for (Product product : products) {
		            if (product.getProductName().equals(productName)) {
		                selectedProduct = product;
		                break;
		            }
		        }
		        
		        int quantity = Integer.parseInt(txtSalesQuantity.getText().trim());
		        double totalPrice = Double.parseDouble(txtTotalPrice.getText().trim());
		        String date = txtDate.getText().trim();
		        
		        SalesRecord sale = new SalesRecord(selectedProduct.getProductId(), quantity, date, totalPrice);
		        dbManager.addSale(sale);
		        
		        // Refresh the selected product after the sale
		        selectedProduct = dbManager.getProductById(selectedProduct.getProductId());
		        
		        DefaultTableModel salesModel = (DefaultTableModel) table_1.getModel();
		        salesModel.addRow(new Object[]{
		            salesModel.getRowCount() + 1,
		            productName,
		            quantity,
		            date,
		            totalPrice
		        });
		        
		        DefaultTableModel inventoryModel = (DefaultTableModel) table.getModel();
		        for (int i = 0; i < inventoryModel.getRowCount(); i++) {
		            if (inventoryModel.getValueAt(i, 0).toString().equals(selectedProduct.getProductId())) {
		                inventoryModel.setValueAt(selectedProduct.getQuantity(), i, 2);
		                break;
		            }
		        }
		        
		        clearSalesFields();
		    }
		});

		// Add sales record delete button action
		btnSalesRecordsDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int row = table_1.getSelectedRow();
		        String saleId = table_1.getValueAt(row, 0).toString();
		        dbManager.deleteSale(saleId);
		        
		        DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		        model.removeRow(row);
		        clearSalesFields();
		    }
		});

		// Add sales record clear button action
		btnSalesRecordsClear.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        clearSalesFields();
		    }
		});

		// Add sales table selection listener
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		            int row = table_1.getSelectedRow();
		            if (row >= 0) {
		                comboBox_1.setSelectedItem(table_1.getValueAt(row, 1).toString());
		                txtSalesQuantity.setText(table_1.getValueAt(row, 2).toString());
		                txtDate.setText(table_1.getValueAt(row, 3).toString());
		                txtTotalPrice.setText(table_1.getValueAt(row, 4).toString());
		            }
		        }
		    }
		});

		// Add sales record edit button action
		btnSalesRecordsEdit.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int row = table_1.getSelectedRow();
		        String saleId = table_1.getValueAt(row, 0).toString();
		        String productName = comboBox_1.getSelectedItem().toString();
		        
		        // Find the selected product
		        List<Product> products = dbManager.getAllProducts();
		        Product selectedProduct = null;
		        for (Product product : products) {
		            if (product.getProductName().equals(productName)) {
		                selectedProduct = product;
		                break;
		            }
		        }
		        
		        int quantity = Integer.parseInt(txtSalesQuantity.getText().trim());
		        double totalPrice = Double.parseDouble(txtTotalPrice.getText().trim());
		        String date = txtDate.getText().trim();
		        
		        SalesRecord sale = new SalesRecord(selectedProduct.getProductId(), quantity, date, totalPrice);
		        dbManager.updateSale(sale, saleId);
		        
		        DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		        model.setValueAt(productName, row, 1);
		        model.setValueAt(quantity, row, 2);
		        model.setValueAt(date, row, 3);
		        model.setValueAt(totalPrice, row, 4);
		        
		        clearSalesFields();
		    }
		});
	}
	
	private void clearFields() {
		txtProductId.setText("");
		txtProductName.setText("");
		txtBrand.setText("");
		txtQuantity.setText("");
		txtPrice.setText("");
		txtCategory.setText("");
	}
	
	private void loadExistingProducts() {
		List<Product> products = dbManager.getAllProducts();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		for (Product product : products) {
			model.addRow(new Object[]{
				product.getProductId(),
				product.getProductName(),
				product.getQuantity(),
				product.getPrice(),
				product.getCategory(),
				product.getSupplier()
			});
		}
	}
	
	 private void switchPanel(JPanel mainPanel, String panelName) {
	        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
	        cardLayout.show(mainPanel, panelName);
	    }

	private void loadExistingSuppliers() {
		List<Supplier> suppliers = dbManager.getAllSuppliers();
		DefaultTableModel model = (DefaultTableModel) supplierTable.getModel();
		
		for (Supplier supplier : suppliers) {
			model.addRow(new Object[]{
				supplier.getSupplierId(),
				supplier.getSupplierName(),
				supplier.getContact()
			});
			comboBoxSupplier.addItem(supplier.getSupplierName());
		}
	}

	private void clearSupplierFields() {
		txtSupplierId.setText("");
		txtSupplierName.setText("");
		txtSupplierContact.setText("");
	}

	private void clearSalesFields() {
		comboBox_1.setSelectedIndex(-1);
		txtSalesQuantity.setText("");
		txtDate.setText("");
		txtTotalPrice.setText("");
	}
	private void exportInventoryData() {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Export Inventory Data");
	    int userSelection = fileChooser.showSaveDialog(null);

	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        try (FileWriter writer = new FileWriter(fileChooser.getSelectedFile() + ".csv")) {
	            writer.write("Product ID,Product Name,Quantity,Price,Category,Supplier\n");

	            DefaultTableModel model = (DefaultTableModel) table.getModel();
	            for (int i = 0; i < model.getRowCount(); i++) {
	                writer.write(model.getValueAt(i, 0) + ","); // Product ID
	                writer.write(model.getValueAt(i, 1) + ","); // Product Name
	                writer.write(model.getValueAt(i, 2) + ","); // Quantity
	                writer.write(model.getValueAt(i, 3) + ","); // Price
	                writer.write(model.getValueAt(i, 4) + ","); // Category
	                writer.write(model.getValueAt(i, 5) + "\n"); // Supplier
	            }
	            JOptionPane.showMessageDialog(null, "Data exported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
	        } catch (IOException ex) {
	            JOptionPane.showMessageDialog(null, "An error occurred while exporting the data.", "Error", JOptionPane.ERROR_MESSAGE);
	            ex.printStackTrace();
	        }
	    }
	}


	private void importInventoryData() {
	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Import Inventory Data");
	    int userSelection = fileChooser.showOpenDialog(null);

	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        File file = fileChooser.getSelectedFile();

	        if (file != null) {
	            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
	                DefaultTableModel model = (DefaultTableModel) table.getModel();
	                model.setRowCount(0);

	                String line;
	                boolean headerRead = false;

	                while ((line = reader.readLine()) != null) {
	                    line = line.trim();

	                    if (!headerRead) {
	                        headerRead = true;
	                        continue;
	                    }

	                    String[] fields = line.split(",");

	                    // Alan sayısını kontrol et (6 alan bekleniyor)
	                    if (fields.length != 6) {
	                        throw new IOException("Invalid file format or corrupted data. Expected 6 fields, found " + fields.length);
	                    }

	                    // Miktar ve fiyat alanlarının sayısal olup olmadığını kontrol et
	                    try {
	                        int quantity = Integer.parseInt(fields[2].trim()); // Quantity
	                        double price = Double.parseDouble(fields[3].trim()); // Price

	                        model.addRow(new Object[]{
	                            fields[0].trim(), // Product ID
	                            fields[1].trim(), // Product Name
	                            quantity, // Quantity
	                            price, // Price
	                            fields[4].trim(), // Category
	                            fields[5].trim() // Supplier
	                        });
	                    } catch (NumberFormatException ex) {
	                        JOptionPane.showMessageDialog(null, "Invalid number format in the file for Quantity or Price. Please check the values and try again.", "Error", JOptionPane.ERROR_MESSAGE);
	                        return; // Hata sonrası metoddan çık
	                    }
	                }

	                JOptionPane.showMessageDialog(null, "Data imported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
	            } catch (IOException ex) {
	                JOptionPane.showMessageDialog(null, "An error occurred while importing the data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                ex.printStackTrace();
	            }
	        }
	    }
	}






	private void loadExistingSales() {
		List<SalesRecord> sales = dbManager.getAllSales();
		DefaultTableModel model = (DefaultTableModel) table_1.getModel();
		
		for (SalesRecord sale : sales) {
			Product product = dbManager.getProductById(sale.getProductId());
			String productName = product != null ? product.getProductName() : "Unknown";
			
			model.addRow(new Object[]{
				model.getRowCount() + 1,  // Sale ID
				productName,
				sale.getQuantity(),
				sale.getDate(),
				sale.getTotalPrice()
			});
	        }
	 }
}



















