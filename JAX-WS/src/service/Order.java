package service;

public class Order {
    private String restaurantName;
    private String itemName;

    public Order() {
    }

    public Order(String restaurantName, String itemName) {
        this.restaurantName = restaurantName;
        this.itemName = itemName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
