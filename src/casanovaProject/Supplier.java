package casanovaProject;

public class Supplier {
    private String supplierId;
    private String supplierName;
    private String contact;

    public Supplier(String supplierId, String supplierName, String contact) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.contact = contact;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
} 