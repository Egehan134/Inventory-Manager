package casanovaProject;

public class SalesRecord {
    private String productId;
    private int quantity;
    private String date;
    private double totalPrice;

    public SalesRecord(String productId, int quantity, String date, double totalPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
} 