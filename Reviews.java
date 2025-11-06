//Attributes: rating score (1-5) and text comment.
  //Linked to products.


  //Operations:
  //Add/edit review.

 //Get an average rating for product.
 //Time complexity:
//add: O(1)
//edit: O(n)
//trackOutOfStock: O(n)
//getAverageRating: O(n)
//listCommonProductsWithMoreThanFourO(n^2)
//extractReviewCustomer: O(n)

public class Reviews{

     class ReviewNode { // linked list node
         Review review;
         ReviewNode next;

         public ReviewNode(Review review) {
             this.review = review;
             this.next = null;
         }
     }

     ReviewNode head;
     public Reviews()
     {
      this.head=null;
      
     }

     // add review
     public void add(int reviewId , Customer customer , Product product , int rating , String comment){
         Reviews.ReviewNode newNode = new Reviews.ReviewNode(new Review(reviewId, customer, product, rating ,comment));//replicated from the create method from class orders
         newNode.next = head;
         
         head = newNode;
     }
     // edit review
     public boolean edit(int reviewId , Customer customer , Product product , int rating , String comment){
         ReviewNode current = head;
         boolean found = false;

         if (head == null) return false;
         while (current.next != null) {
             if (current.review.reviewId == reviewId) {
                 found = true;
                 break;
             }
             current = current.next;
         } if (current.review.reviewId == reviewId) found = true;
         if (found) {
             current.review.setCustomer(customer);
             current.review.setProduct(product);
             current.review.setRating(rating);
             current.review.setComment(comment);
             return true;
         } return false;
     }
     // get average rating for product, continuation in main to link with product
     public double getAverageRating(){
         ReviewNode current = head;
         double ratingTotal = 0;
         int count = 0;

         if (head == null) return 0.0;
         while (current != null) {
             ratingTotal += current.review.rating;
             count++;
             current = current.next;
         } return ratingTotal/count;
     }
     public Review search(int reviewId){
         Reviews.ReviewNode current = head;
         while (current != null) {
             if (current.review.reviewId == reviewId) return current.review;
             current = current.next;
         } return null;
     }

// extract reviews for a specific customer using array is optional use the most efficient linear data structure i suggested use a class Reviews itself as a return type
// you should add the time complexity of this method as a comment
// moved the method into main to use the static object products
  public Reviews extractReviewCustomer(int customerId){
      ReviewNode current = head;
      Reviews customerReviews = new Reviews();
      while(current != null)
      {
        if(current.review.getCustomer().getCustomerId() == customerId)
        {
          customerReviews.add(current.review.getReviewId(), current.review.getCustomer(), current.review.getProduct(), current.review.getRating(), current.review.getComment());
        }
        current = current.next;
      }
      return customerReviews;
  }


// list common products reviewed by two customers with average rating more than a specified rating
// you should add the time complexity of this method as a comment as well
 public Products listCommonProductsWithMoreThanFour(int customerId1 , int customerId2){
      ReviewNode current1 = head;
      Products commonProducts = new Products();
      while(current1 != null)
      {
        if(current1.review.getCustomer().getCustomerId() == customerId1 && current1.review.getRating() > 4.0)
        {
          ReviewNode current2 = head;
          while(current2 != null)
          {
            if(current2.review.getCustomer().getCustomerId() == customerId2 && current2.review.getProduct().getProductId() == current1.review.getProduct().getProductId() && current2.review.getRating() > 4.0)
            {
              commonProducts.add(current1.review.getProduct().getProductId(), current1.review.getProduct().getName(), current1.review.getProduct().getPrice(), current1.review.getProduct().getStock());
              break;
            }
            else
            {
              current2 = current2.next;
            }
           
          }
        }
        current1 = current1.next;
      }
      return commonProducts;
}

public void printReviews(){
    ReviewNode current = head;
    while(current != null)
    {
      System.out.println("Review ID: " + current.review.getReviewId() + ", Customer ID: " + current.review.getCustomer().getCustomerId() + ", Product ID: " + current.review.getProduct().getProductId() + ", Rating: " + current.review.getRating() + ", Comment: " + current.review.getComment());
      current = current.next;
    }
  }

  

}
