 //orderId, customer reference, list of products, total price, order date,
  //status (pending, shipped, delivered, canceled)


  //Operations:
  //Create/cancel order.

  //Update order status.

  //Search order by ID.
  // Time complexity:
// create: O(1)
// cancel: O(n)
// updateStatus: O(n)
// search: O(n)
// getOrdersBetweenDates: O(n)
public class Orders{

  class OrderNode { // linked list node
      Order order;
      OrderNode next;

      public OrderNode(Order order) {
          this.order = order;
          this.next = null;
      }
  }


   OrderNode head;

   public Orders(){
       this.head = null;
   }
 
  // create order
  public void create(int orderId , Customer customer , Products products , double totalPrice , String orderDate , String status){
      OrderNode newNode = new OrderNode(new Order(orderId, customer, products, totalPrice, orderDate, status));
      newNode.next = head;
      head = newNode;
  
  }

  // cancel order
  public void cancel(int orderId){
       //assume orderId is unique
        OrderNode current = head;
        if(current == null) return;
        if(current.order.getOrderId() == orderId){
            head = current.next;
            return;
        }
        while(current.next != null)
        {
          if(current.next.order.getOrderId() == orderId)
          {
            current.next= current.next.next;
            return;
          }
          current = current.next;
        }
  }
  // update order status
  public void updateStatus(int orderId , String status){
       //assume orderId is unique and status is pending, shipped, delivered, canceled
        OrderNode current = head;
        while(current != null)
        {
          if(current.order.getOrderId() == orderId)
          {
            current.order.setStatus(status);
            return;
          }
          current = current.next;
        }
  }
  // search order by id
  public Order search(int orderId){
      OrderNode current = head;
      while(current != null)
      {
        if(current.order.getOrderId() == orderId)
        {
          return current.order;
        }
        current = current.next;
      }
      return null;
  }
  // get orders between two dates
  // you should add the time complexity of this method as a comment
  public Orders getOrdersBetweenDates(String startDate, String endDate) {
      Orders result = new Orders();
      boolean found = false;
      OrderNode current = head;
      while(current != null)
     {
       if(compareDates(current.order.getOrderDate() , startDate) == 1 && compareDates(current.order.getOrderDate() , endDate) == -1)
       {
          result.create(current.order.getOrderId(), current.order.getCustomer(), current.order.getProducts(), current.order.getTotalPrice(), current.order.getOrderDate(), current.order.getStatus());
          found = true;
       }
        current = current.next;
     }
      if(!found) return null;
      return result;
  } //time complexity is O(n)

  private int compareDates(String date1, String date2) { //this method returns 1 if date1 is closer to present than date2 -1 otherwise 0 for equality
      // assume date format is "YYYY-MM-DD" single digit month and day are prefixed with 0
      // return -1 if date1 < date2, 0 if equal, 1 if date1 > date2 and 0 for equality
      int year1 = Integer.parseInt(date1.substring(0, 4));
      int month1=(date1.substring(5,7).charAt(0) == '0') ? Integer.parseInt(date1.substring(6,7)) : Integer.parseInt(date1.substring(5,7));
      int day1=(date1.substring(8,10).charAt(0) == '0') ? Integer.parseInt(date1.substring(9,10)) : Integer.parseInt(date1.substring(8,10));
      int year2 = Integer.parseInt(date2.substring(0, 4));
      int month2=(date2.substring(5,7).charAt(0) == '0') ? Integer.parseInt(date2.substring(6,7)) : Integer.parseInt(date2.substring(5,7));
      int day2=(date2.substring(8,10).charAt(0) == '0') ? Integer.parseInt(date2.substring(9,10)) : Integer.parseInt(date2.substring(8,10));
      if(year1 < year2) return -1;  //compare year
      if(year1 > year2) return 1;
      if(month1 < month2) return -1; //compare month
      if(month1 > month2) return 1;
      if(day1 < day2) return -1; //compare day
      if(day1 > day2) return 1;
      return 0; //equal
  }

  public void printOrders(){
    OrderNode current = head;
    while(current != null)
    {
      System.out.println("Order ID: " + current.order.getOrderId() + ", Customer ID: " + current.order.getCustomer().getCustomerId() + ", Total Price: " + current.order.getTotalPrice() + ", Order Date: " + current.order.getOrderDate() + ", Status: " + current.order.getStatus());
      current = current.next;
    }
  }
 
}
