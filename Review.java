public class Review {
    int reviewId;
    Customer customer;
    Product product;
    int rating; // 1 to 5
    String comment;


    public Review(int reviewId, Customer customer, Product product, int rating, String comment) {
        this.reviewId = reviewId;
        this.customer = customer;
        this.product = product;
        this.rating = rating;
        this.comment = comment;
    }

    public int getReviewId() { return reviewId; }
    public Customer getCustomer() { return customer; }
    public Product getProduct() { return product; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public void setReviewId(int reviewId) { this.reviewId = reviewId; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public void setProduct(Product product) { this.product = product; }
    public void setRating(int rating) { this.rating = rating; }
    public void setComment(String comment) { this.comment = comment; }
}
