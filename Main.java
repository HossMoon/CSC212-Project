 //Requirements

  
  // • Read data from CSV file that contains products, customers, orders, and reviews.
  
  // • You can add a product, customer, and place an order.
  
  // • Customers can add reviews to products.
  
  // • Extract reviews from a specific customer for all products with the most efficient linear data structure possible.
  
  // • Suggest "top 3 products" by average rating.
  
  // • All Orders between two dates
  
  // • Given two customers IDs, show a list of common products that have been reviewed with an average rating of more than 4 out of 5.
import java.io.*;
import java.util.*;
public class Main {
  static Products products= new Products();
  static Customers customers= new Customers();
  static Orders orders= new Orders();
  //static Reviews reviews= new Reviews();
public static void main(String[] args) {
  readCSVCustomer("customers.csv");
  readCSVProduct("products.csv");
  readCSVOrders("orders.csv");
  readCSVReviews("reviews.csv");
   Scanner scanner = new Scanner(System.in);
   int choice = 0;
   while(choice != -1) //UI loop to be switched to GUI later
   {
     System.out.println("Add product (1)");
     System.out.println("Register customer (2)");
      System.out.println("Place order (3)");
      System.out.println("Add review (4)");
      System.out.println("Extract reviews for a customer (5)");
      System.out.println("Suggest top 3 products by average rating (6)");
      System.out.println("list Orders between two dates (7)");
      System.out.println("list common products reviewed by two customers with average rating more than a specified rating of 5 (8)");
      System.out.println("Exit (-1)");
      System.out.println("Enter your choice: ");
      choice = scanner.nextInt();
      if(choice == 1)
      {
        //Add product
        System.out.println("Enter product ID: ");
        int productId = scanner.nextInt();
        System.out.println("Enter product name: ");
        String name = scanner.next();
        System.out.println("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.println("Enter product stock: ");
        int stock = scanner.nextInt();
        Product newProduct = new Product(productId , name , price , stock);
        if( products.search(productId) != null)
        {
          System.out.println("Product with this ID already exists.");
          continue;
        }
        products.add(productId , name , price , stock);
        writeCSVProduct("products.csv" , newProduct);
        System.out.println("Product added successfully.");

      }
      else if(choice == 2)
      {
        //Register customer
        System.out.println("Enter customer ID: ");
        int customerId = scanner.nextInt();
        System.out.println("Enter customer name: ");
        String name = scanner.next();
        System.out.println("Enter customer email: ");
        String email = scanner.next();
        Customer newCustomer = new Customer(customerId , name , email);
        if( customers.searchCustomer(customerId) != null)
        {
          System.out.println("Customer with this ID already exists.");
          continue;
        }
        customers.register(customerId , name , email);
        writeCSVCustomer("customers.csv" , newCustomer);
        System.out.println("Customer registered successfully.");
      }
      else if(choice == 3)
      {
        //Place order
        System.out.println("Enter order ID: ");
        int orderId = scanner.nextInt();
        if( orders.search(orderId) != null)
        {
          System.out.println("Order with this ID already exists.");
          continue;
        }
        System.out.println("Enter customer ID: ");
        int customerId = scanner.nextInt();
        Customer C = customers.searchCustomer(customerId);
        if(C == null)
        {
          System.out.println("Customer with this ID does not exist.");
          continue;
        }
        Products P = new Products();
        double totalPrice = 0.0;
        String moreProducts = "y";
        while(moreProducts.equals("y"))
        {
          System.out.println("Enter product ID to add to order: ");
          int productId = scanner.nextInt();
          Product temp = products.search(productId);
          if(temp == null)
          {
            System.out.println("Product with this ID does not exist.");
            continue;
          }
          P.add(temp.getProductId() , temp.getName() , temp.getPrice() , temp.getStock());
          totalPrice += temp.getPrice();
          System.out.println("Add more products? (y/n): ");
          moreProducts = scanner.next();
        }
        String orderDate = java.time.LocalDate.now().toString();
        String status = "Placed";
        Order newOrder = new Order(orderId , C , P , totalPrice , orderDate , status);
        orders.create(orderId , C ,  P , totalPrice , orderDate , status);
        writeCSVOrders("orders.csv" , newOrder);
        System.out.println("Order placed successfully.");
      }
      else if(choice == 4)
      {
        //Add review
        System.out.println("Enter review ID: ");
        int reviewId = scanner.nextInt();
        if( searchReviewGlobally(reviewId) != null)
        {
          System.out.println("Review with this ID already exists.");
          continue;
        }
        System.out.println("Enter customer ID: ");
        int customerId = scanner.nextInt();
        Customer C = customers.searchCustomer(customerId);
        if(C == null)
        {
          System.out.println("Customer with this ID does not exist.");
          continue;
        }
        System.out.println("Enter product ID: ");
        int productId = scanner.nextInt();
        Product P = products.search(productId);
        if(P == null)
        {
          System.out.println("Product with this ID does not exist.");
          continue;
        }
        System.out.println("Enter rating (1-5): ");
        int rating = scanner.nextInt();
        System.out.println("Enter comment: ");
        String comment = scanner.next();
        Review newReview = new Review(reviewId , C , P , rating , comment);

        //replaced review.add with the following to avoid error
        Reviews productReviews = P.getReviews();
        productReviews.add(reviewId , C , P , rating , comment);

        writeCSVReviews("reviews.csv" , newReview);
        System.out.println("Review added successfully.");
      }
      else if(choice == 5)
      {
        //Extract reviews for a customer
        System.out.println("Enter customer ID: ");
        int customerId = scanner.nextInt();
        if( customers.searchCustomer(customerId) == null)
        {
          System.out.println("Customer with this ID does not exist.");
          continue;
        }
        //updated the line below to use extractReviewsByCustomer method
        Review[] reviewCustomers = extractReviewsByCustomer(customerId);        System.out.println("Reviews for customer ID " + customerId + ":");
        for(Review cust : reviewCustomers)
        {
          System.out.println("Review ID: " + cust.getReviewId() + ", Product ID: " + cust.getProduct().getProductId() + ", Rating: " + cust.getRating() + ", Comment: " + cust.getComment());
        }

      }
      else if(choice == 6)
      {
        //Suggest top 3 products by average rating
          Products.ProductNode current = products.head;
          Product top1 = null;
          Product top2 = null;
          Product top3 = null;
          double top1_avg = 0.0;
          double top2_avg = 0.0;
          double top3_avg = 0.0;

          while (current != null) {
              Product currentProduct = current.product;
              Reviews productReviews = currentProduct.getReviews();
              double currentAvg = productReviews.getAverageRating();

              if (currentAvg > top1_avg) {
                  top3_avg = top2_avg;
                  top3 = top2;
                  top2_avg = top1_avg;
                  top2 = top1;

                  top1_avg = currentAvg;
                  top1 = currentProduct;
              } else if (currentAvg > top2_avg) {
                  top3_avg = top2_avg;
                  top3 = top2;

                  top2_avg = currentAvg;
                  top2 = currentProduct;
              } else if (currentAvg > top3_avg) {
                  top3_avg = currentAvg;
                  top3 = currentProduct;
              } current = current.next;
          }
          System.out.println("Top 3 products by average rating:");
          if (top1 != null) System.out.printf("1- %s, Rating: %.2f\n", top1.getName(), top1_avg);
          if (top2 != null) System.out.printf("2- %s, Rating: %.2f\n", top2.getName(), top2_avg);
          if (top3 != null) System.out.printf("3- %s, Rating: %.2f\n", top3.getName(), top3_avg);
      }

      else if(choice == 7)
      {
        //list Orders between two dates
      }
      else if(choice == 8)
      {
          //list common products reviewed by two customers with average rating > 4
          System.out.println("Enter first customer ID: ");
          int id1 = scanner.nextInt();
          if(customers.searchCustomer(id1) == null) {
              System.out.println("Customer 1 not found.");
              continue;
          }
          System.out.println("Enter second customer ID: ");
          int id2 = scanner.nextInt();
          if(customers.searchCustomer(id2) == null) {
              System.out.println("Customer 2 not found.");
              continue;
          }
          Products commonList = listCommonProductsWithMoreThanRating(id1, id2);
          System.out.println("Common products with rating > 4.0:");

          Products.ProductNode current = commonList.head;
          if (current == null)
              System.out.println("No common products found matching the criteria.");

          while (current != null) {
              Product p = current.product;
              System.out.println("- Product: " + p.getName());
              current = current.next;
          }
      }


   }

}

// CSV read and write methods
public static void readCSVProduct(String filePath){
    BufferedReader br = null;
    String line = "";
    try{
    br = new BufferedReader(new FileReader(filePath));
    br.readLine(); // skip header
    while((line = br.readLine())!= null){
        String[] values = line.split(",");

        if(values.length == 4) //avoid empty lines
        {
          //getting data
          int productId = Integer.parseInt(values[0]);
          String name = values[1].trim();
          double price = Double.parseDouble(values[2]);
          int stock = Integer.parseInt(values[3]);
          //add product to the system
          products.add(productId , name , price , stock);

        }

    }
    br.close();
  }catch(IOException e){
      e.printStackTrace();
  }
}

public static void readCSVCustomer(String filePath){
   BufferedReader br = null;
    String line = "";
    try{
        br = new BufferedReader(new FileReader(filePath));
        br.readLine(); // skip header
        while((line = br.readLine())!= null){
            String[] values = line.split(",");

            if(values.length == 3) //avoid empty lines
            {
              //getting data
              int customerId = Integer.parseInt(values[0]);
              String name = values[1].trim();
              String email = values[2].trim();
              //add customer to the system
              customers.register(customerId , name , email);

            }

        }
        br.close();
    }catch(IOException e){
        e.printStackTrace();
    }
}

public static void readCSVOrders(String filePath){
    BufferedReader br = null;
      String line = "";
      try{
          br = new BufferedReader(new FileReader(filePath));
          br.readLine(); // skip header
          while((line = br.readLine())!= null){
              String[] values = line.split(",");
  
              if(values.length == 6) //avoid empty lines
              {
                //getting data
                int orderId = Integer.parseInt(values[0]);
                int customerId = Integer.parseInt(values[1]);
                double totalPrice = Double.parseDouble(values[3]);
                String orderDate = values[4].trim();
                String status = values[5].trim();
                //place order for the customer
                customers.placeOrder(customerId , orderId , orderDate );
                Products P = new Products();
                String[] productIds = values[2].split(";");
                for(String i : productIds) //adding products list to the order
                {
                  int pid = Integer.parseInt(i);
                  Product temp = products.search(pid);
                  if(temp != null)
                  {
                    P.add(temp.getProductId() , temp.getName() , temp.getPrice() , temp.getStock());
                  }

                }
                Customer C = customers.searchCustomer(customerId); //getting customer object
                orders.create(orderId , C ,  P , totalPrice , orderDate , status); //add order to the system
  
              }
  
          }
          br.close();
      }catch(IOException e){
          e.printStackTrace();
      }
}

public static void readCSVReviews(String filePath){
    BufferedReader br = null;
      String line = "";
      try{
          br = new BufferedReader(new FileReader(filePath));
          br.readLine(); // skip header
          while((line = br.readLine())!= null){
              String[] values = line.split(",");
  
              if(values.length == 5) //avoid empty lines
              {
                //getting data
                int reviewId = Integer.parseInt(values[0]);
                int customerId = Integer.parseInt(values[1]);
                int productId = Integer.parseInt(values[2]);
                int rating = Integer.parseInt(values[3]);
                String comment = values[4].trim();
                //add review to the system
                Customer C = customers.searchCustomer(customerId);
                Product P = products.search(productId);
                //replaced review.add with the following to avoid error
                if ( P != null && C != null)
                {
                    Reviews productReviews = P.getReviews();
                    productReviews.add(reviewId , C , P , rating , comment);
                }

              }
  
          }
          br.close();
      }catch(IOException e){
          e.printStackTrace();
      }
}

public static void writeCSVProduct(String filePath , Product product){
        FileWriter fileWriter = null;
        try {
           fileWriter = new FileWriter(filePath, true); // append mode
           fileWriter.write(product.getProductId() + "," + product.getName() + "," + product.getPrice() + "," + product.getStock() + "\n");
        }catch (IOException e) {
           e.printStackTrace();
        }
           try {
               if (fileWriter != null) {
                   fileWriter.flush();
                   fileWriter.close();
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
}

public static void writeCSVCustomer(String filePath , Customer customer){
    FileWriter fileWriter = null;
    try {
       fileWriter = new FileWriter(filePath, true); // append mode
       fileWriter.write(customer.getCustomerId() + "," + customer.getName() + "," + customer.getEmail() + "\n");
    }catch (IOException e) {
       e.printStackTrace();
    }
       try {
           if (fileWriter != null) {
               fileWriter.flush();
               fileWriter.close();
           }
       } catch (IOException e) {
           e.printStackTrace();
       }

}

public static void writeCSVOrders(String filePath , Order order){
     FileWriter fileWriter = null;
     try{
        fileWriter = new FileWriter(filePath, true); // append mode
        String ProductIds = order.getProducts().getProuductIds();
        fileWriter.write(order.getOrderId() + "," + order.getCustomer().getCustomerId() + "," + ProductIds + "," + order.getTotalPrice() + "," + order.getOrderDate() + "," + order.getStatus() + "\n");
     }catch (IOException e) {
        e.printStackTrace();
     }
        try {
            if (fileWriter != null) {
                fileWriter.flush();
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}

public static void writeCSVReviews(String filePath , Review review){
    FileWriter fileWriter = null;
    try {
       fileWriter = new FileWriter(filePath, true); // append mode
       fileWriter.write(review.getReviewId() + "," + review.getCustomer().getCustomerId() + "," + review.getProduct().getProductId() + "," + review.getRating() + "," + review.getComment() + "\n");
    }catch (IOException e) {
       e.printStackTrace();
    }
       try {
           if (fileWriter != null) {
               fileWriter.flush();
               fileWriter.close();
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
}
//moved the method into main to use the static object products
public static Review[] extractReviewsByCustomer(int customerId) {
    int count = 0;
    Products.ProductNode currentProductNode = products.head;

    while (currentProductNode != null) {
        Reviews productReviews = currentProductNode.product.getReviews();
        Reviews.ReviewNode currentReviewNode = productReviews.head;

        while (currentReviewNode != null) {
            if (currentReviewNode.review.getCustomer().getCustomerId() == customerId) {
                count++;
            } currentReviewNode = currentReviewNode.next;
        } currentProductNode = currentProductNode.next;
    }
    Review[] customerReviews = new Review[count];
    int index = 0;
    currentProductNode = products.head;

    while (currentProductNode != null) {
        Reviews productReviews = currentProductNode.product.getReviews();
        Reviews.ReviewNode currentReviewNode = productReviews.head;

        while (currentReviewNode != null) {
            if (currentReviewNode.review.getCustomer().getCustomerId() == customerId) {
                customerReviews[index] = currentReviewNode.review; // Add to array
                index++;
            } currentReviewNode = currentReviewNode.next;
        } currentProductNode = currentProductNode.next;
    } return customerReviews;
}



public static Products getProductsReviewedBy(int customerId) {
    Products reviewedProducts = new Products();
    Products.ProductNode currentProductNode = products.head;

    while (currentProductNode != null) {
        Product p = currentProductNode.product;
        Reviews productReviews = p.getReviews();
        Reviews.ReviewNode currentReviewNode = productReviews.head;

        while (currentReviewNode != null) {
            if (currentReviewNode.review.getCustomer().getCustomerId() == customerId) {
                reviewedProducts.add(p.getProductId(), p.getName(), p.getPrice(), p.getStock());
                break;
            } currentReviewNode = currentReviewNode.next;
        } currentProductNode = currentProductNode.next;
    } return reviewedProducts;
}

public static Products listCommonProductsWithMoreThanRating(int customerId1, int customerId2) {
    Products finalList = new Products();
    Products list1 = getProductsReviewedBy(customerId1);
    Products list2 = getProductsReviewedBy(customerId2);
    Products.ProductNode current1 = list1.head;

    while (current1 != null) {
        Product p1 = current1.product;
        Product commonProduct = list2.search(p1.getProductId());
        if (commonProduct != null) {
            Product originalProduct = products.search(p1.getProductId());
            if (originalProduct.getReviews().getAverageRating() > 4.0)
                finalList.add(p1.getProductId(), p1.getName(), p1.getPrice(), p1.getStock());
        } current1 = current1.next;
    } return finalList;
}

//made this method to avoid using a static object of type Review
public static Review searchReviewGlobally(int reviewId) {
    Products.ProductNode currentProductNode = products.head;

    while (currentProductNode != null) {
        Reviews productReviews = currentProductNode.product.getReviews();
        Review foundReview = productReviews.search(reviewId);
        if (foundReview != null) return foundReview;
        currentProductNode = currentProductNode.next;
    } return null;
}



}
