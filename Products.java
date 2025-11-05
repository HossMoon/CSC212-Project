//Attributes: productId, name, price, stock, list of reviews


  //Operations:
  //Add/remove/update products.

  //Search by ID or name (linear).

  //Track out-of-stock products.
public class Products{
  
  //add product 
  public void add(int productId , String name , double price , int stock ){
    
  }
  // remove a product
  public void remove(int productId){

  }
  // update a product 
  public void update(int productId , String name , double price , int stock){

  }
  // search by id
  public Product search(int productId){
     return null;
  }
  // search by name
  public boolean search(String name){
      return true;
  }
  // print product details where stock == 0 
  public void trackOutOfStock(){

  }
  // get product ids as a string of format "id1;id2;..;idn"
  public String getProuductIds(){
      return "";
  }
  
}
