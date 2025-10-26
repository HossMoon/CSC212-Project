public class Order {
    int orderId;
    Customer customer;
    Products products;
    double totalPrice;
    String orderDate;
    String status; // pending, shipped, delivered, canceled

    public Order(int orderId, Customer customer, Products products, double totalPrice, String orderDate, String status) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
        this.status = status;
    }
    public int getOrderId() {
        return orderId;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Products getProducts() {
        return products;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public String getStatus() {
        return status;
    }
    
}
