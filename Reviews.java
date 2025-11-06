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
//listCommonProductsWithMoreThanRating O(n)

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
/*public Review[] extractReviewCustomer(int customerId){
      return null;
  }*/


// list common products reviewed by two customers with average rating more than a specified rating
// you should add the time complexity of this method as a comment as well
/*public Products listCommonProductsWithMoreThanRating(int customerId1 , int customerId2 , double rating){
      return null;
}*/

 }

