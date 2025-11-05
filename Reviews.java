 //Attributes: rating score (1-5) and text comment.
  //Linked to products.


  //Operations:
  //Add/edit review.

  //Get an average rating for product.
public class Reviews{


  // add review 
  public void add(int reviewId , Customer customer , Product product , int rating , String comment){
    
  }
  // edit review 
  public void edit(int reviewId , Customer customer , Product product , int rating , String comment){

  }
  // get average rating for product 
  public double getAverageRating(int productId){
     return 0.0;
  }
 // extract reviews for a specific customer using array is optinal use the most efficient linear data structure i suggested use a class Reviews itself as a return type
 // you should add the time complexity of this method as a comment
  public Review[] extractReviewCustomer(int customerId){
      return null;
  }
  
  // list common products reviewed by two customers with average rating more than a specified rating
  // you should add the time complexity of this method as a comment as well
  public Products listCommonProductsWithMoreThanRating(int customerId1 , int customerId2 , double rating){
      return null;
  }

  public Review search(int reviewId){
      return null;
  }
 

}
