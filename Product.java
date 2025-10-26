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
   

}
