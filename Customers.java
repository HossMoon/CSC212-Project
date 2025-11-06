// orders list

  
  //Operations:
  //Register new customer

  //Place a new order for a specific customer

  //View order history
  //Time complexity:
  // register: O(1)
  // placeOrder: O(n)
  // viewOrderHistory: O(n)
  // searchCustomer: O(n)
public class Customers{
    class CustomerNode { // linked list node
        Customer customer;
        CustomerNode next;

        public CustomerNode(Customer customer) {
            this.customer = customer;
            this.next = null;
        }
    }
   CustomerNode head;
    public Customers(){
        this.head = null;
    }
  // register new customer
  public void register(int customerId , String name , String email){
      CustomerNode newNode = new CustomerNode(new Customer(customerId, name, email));
      newNode.next = head;
      head = newNode;
  }

  // place new order for a specific customer
  public  void placeOrder(int customerId , Order order){
      CustomerNode current = head;
      while(current != null)
      {
        if(current.customer.getCustomerId() == customerId)
        {
          current.customer.getOrders().create(order.getOrderId(), order.getCustomer(), order.getProducts(), order.getTotalPrice(), order.getOrderDate(), order.getStatus());
          return;
        }
        current = current.next;
      }
  }

  // print order history
  public void viewOrderHistory(int customerId){
      CustomerNode current = head;
      while(current != null)
      {
        if(current.customer.getCustomerId() == customerId)
        {
         System.out.println("Order history for customer ID " + customerId + ":");
         current.customer.getOrders().printOrders();
         return;
        }
        current = current.next;
      }
  }

  public Customer searchCustomer(int customerId){
      CustomerNode current = head;
      while(current != null)
      {
        if(current.customer.getCustomerId() == customerId)
        {
          return current.customer;
        }
        current = current.next;
      }
      return null;
  }
  
}
