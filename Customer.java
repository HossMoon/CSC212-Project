public class Customer {
     private int customerId;
     private String name;
     private String email;
     private Orders orders;
        public Customer(int customerId, String name, String email) {
            this.customerId = customerId;
            this.name = name;
            this.email = email;
            this.orders = new Orders();
        }
        public int getCustomerId() {
            return customerId;
        }
        public String getName() {
            return name;
        }
        public String getEmail() {
            return email;
        }
        public Orders getOrders() {
            return orders;
        }


    
}
