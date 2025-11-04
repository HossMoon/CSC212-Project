public class Product {
    int productId;
    String name;
    double price;
    int stock;
    Reviews reviews;
    public Product(int productId, String name, double price, int stock, Reviews reviews) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.reviews = reviews;
    }
    public int getProductId() {
        return productId;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public int getStock() {
        return stock;
    }
    public Reviews getReviews() {
        return reviews;
    }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
    //public void setReviews(Reviews reviews) { this.reviews = reviews; }
}
