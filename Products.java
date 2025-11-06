    //Time complexity:
//add: O(1)
//remove: O(n)
//update: O(n)
//search: O(n)
//trackOutOfStock: O(n)
//getTopThreeProducts: O(n)
//printProducts: O(n)
//getProductsIds: O(n)
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
    private int size;
    public Products()
    {
        this.head=null;
        size=0;
    }

  //add product 
  public void add(int productId , String name , double price , int stock){
      ProductNode newNode = new ProductNode(new Product(productId, name, price, stock));
      //replicated from the create method from class orders
      newNode.next = head;
      head = newNode;
      size++;
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
          size--;
          return true;
      } return false;
  }

  // update a product 
  public boolean update(int productId , String name , double price , int stock){
      ProductNode current = head;
      boolean found = false;

      if (head == null) return false;
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
  public Product search(int productId){
      ProductNode current = head;
      while (current != null) {
          if (current.product.productId == productId) return current.product;
          current = current.next;
      } return null;
  }
  // search by name
  public Product search(String name){
      ProductNode current = head;
      while (current != null) {
          if (current.product.name.equalsIgnoreCase(name)) return current.product;
          current = current.next;
      } return null;
  }
  // print product details where stock == 0 
  public void trackOutOfStock(){
      ProductNode current = head;
      int outOfStockCount = 0;

      while (current.next != null) {
          if (current.product.stock == 0) {
              System.out.printf("ProductId: %d, Name: %s, Price: %.2f, Stock: %d\n",
                      current.product.productId,
                      current.product.name,
                      current.product.price,
                      current.product.stock);
              outOfStockCount++;
          }
          current = current.next;
       }
      if (current.product.stock == 0) {
          System.out.printf("ProductId: %d, Name: %s, Price: %.2f, Stock: %d\n",
                  current.product.productId,
                  current.product.name,
                  current.product.price,
                  current.product.stock);
          outOfStockCount++;
      } if (outOfStockCount == 0) System.out.println("There are no products out of stock");
  }
  public void getTopThreeProducts(){
    System.out.println("Top 3 products by average rating:");
     double values[] = new double[size];
     String[] iDs=getProductsIds().split(";");
     ProductNode current = head;
     int i=0;
     while(current!= null)
     {
        values[i]=current.product.getReviews().getAverageRating();
        i++;
        current=current.next;
     }
     sort(values, iDs);
     int count=1;
     int j=0;
     while(j<values.length)
     {
        double top = values[j];
        while( j<values.length && values[j] == top && count<4)
        {
         Product temp = this.search(Integer.parseInt(iDs[j]));
          System.out.println(temp.getName()+" "+temp.getProductId()+" average"+values[j]);
          j++;
        }
        if(count==3) break;
        count++;
     }
}
private void sort(double[] values , String[] iDs)
{
    for(int i=1 ; i<values.length ; i++)
    {
        for(int j=i ; j>0 ; j--)
        {
            if(values[j]>values[j-1])
            {
                double temp = values[j-1];
                values[j-1] = values[j];
                values[j] = temp;
                String temp2 = iDs[j-1];
                iDs[j-1]=iDs[j];
                iDs[j]=temp2;
            }

        }
    }
}

 public void printProducts(){
      ProductNode current = head;
      if (head == null) {
          System.out.println("No products available.");
          return;
      }
      while (current != null) {
          System.out.printf("ProductId: %d, Name: %s, Price: %.2f, Stock: %d\n",
                  current.product.productId,
                  current.product.name,
                  current.product.price,
                  current.product.stock);
          current = current.next;
      }
  }

  public String getProductsIds()
  {
      ProductNode current = head;
      StringBuilder ids  = new StringBuilder();
      while(current != null)
      {
        ids.append(current.product.getProductId());
        if(current.next != null)
          ids.append(";");
        current = current.next;
      }
      return ids.toString();
  }

  public void addReviewsToProduct(int productId, Reviews reviews) {
      ProductNode current = head;
      while (current != null) {
          if (current.product.getProductId() == productId) {
              current.product.setReviews(reviews);
              return;
          }
          current = current.next;
      }
  }

}
