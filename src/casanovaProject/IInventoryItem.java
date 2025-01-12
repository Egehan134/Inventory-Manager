package casanovaProject;

public interface IInventoryItem {
    String getId();
    String getName();
    double getPrice();
    int getQuantity();
    void setQuantity(int quantity);
    String getCategory();
} 