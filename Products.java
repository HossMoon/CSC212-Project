  //Operations:
  //Add/remove/update products.

  //Search by ID or name (linear).

  //Track out-of-stock products.

    // Time complexity:
//add: O(1)
//remove: O(n)
//update: O(?)
//search: O(n)
//trackOutOfStock: O(?)



public class Products{

    class ProductNode { // linked list node
      Product product;
      ProductNode next;

      public ProductNode(Product product) {
        this.product = product;
        this.next = null;
      }
    }

    ProductNode head;

  //add product 
  public void add(int productId , String name , double price , int stock){
      ProductNode newNode = new ProductNode(new Product(productId, name, price, stock, null));
      ProductNode current = head;
      newNode = head;
      newNode.next = current;
  }
  // remove a product
  public boolean remove(int productId){
      ProductNode current = head;
      ProductNode previous = null;
      boolean found = false;

      if (head == null)
          return false;
      if (head.next == null && head.product.productId == productId) {
          head = null;
          return true;
      }
      if (head.product.productId == productId) {
          head = head.next;
          return true;
      }
      while (current.next != null) {
          if (current.product.productId == productId) {
              found = true;
              break;
          }
          previous = current;
          current = current.next;
      } if (current.product.productId == productId) found = true;
      if (found) {
          previous.next = current.next;
          return true;
      } return false;
  }

  // update a product 
  public boolean update(int productId , String name , double price , int stock){
      ProductNode current = head;
      boolean found = false;
      while (current.next != null) {
          if (current.product.productId == productId) {
              found = true;
              break;
          }
          current = current.next;
      } if (current.product.productId == productId) found = true;
      if (found) {
          current.product.setName(name);
          current.product.setPrice(price);
          current.product.setStock(stock);
          return true;
      } return false;
  }
  // search by id
  public boolean search(int productId){
      ProductNode current = head;
      while (current.next != null) {
          if (current.product.productId == productId) return true;
          current = current.next;
      } return false;
  }
  // search by name
  public boolean search(String name){
      ProductNode current = head;
      while (current.next != null) {
          if (current.product.name.equalsIgnoreCase(name)) return true;
          current = current.next;
      } return false;
  }
  // print product details where stock == 0 
  public void trackOutOfStock(){

  }
  
}
