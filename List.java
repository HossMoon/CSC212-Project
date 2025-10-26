public class List <T> {
    private class Node{
        T data;
        Node next;
        Node prev;

        Node(T data){
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    private Node head;
    private Node tail;
    private Node current;
    private int size;
    public List()
    {
        this.head = null;
        this.tail = null;
        this.current = null;
        this.size = 0;
    }
    private void initlist(Node node)
    {
      this.head = node;
      this.tail = node;
      this.current = node;
      this.size = 1;
    }

    
    public void insertAftertCurrent(T data) {
        // TODO Auto-generated method stub
        if(head == null)
        {
            Node newNode = new Node(data);
            initlist(newNode);
            return;
        }
        Node temp = current.next;
        Node newNode = new Node(data);
        current.next= newNode;
        newNode.prev = current;
        newNode.next = temp;
        if(temp != null)
        {
            temp.prev = newNode;
        }
        size++;
        if(current == tail)
        {
            tail = newNode;
        }

    }


    
    public void removeCurrent() {
        // TODO Auto-generated method stub
        if( head == current)
        {
            head = head.next;
            current = head;
            if(current == null)
            {
                tail = null;
            }
            else
            {
                head.prev = null;
            }
            size--;
            return;

        }
        current.prev.next = current.next;
        if(current == tail)
        {
            tail = current.prev;
        }
        else
        {
            current.next.prev = current.prev;
        }
        current = current.prev;
        size--;
        
    }

   
    public void insertFirst(T data) {
        // TODO Auto-generated method stub
        if(head == null)
        {
            Node newNode = new Node(data);
            initlist(newNode);
            return;
        }
        Node newNode = new Node(data);
        newNode.next=head;
        head.prev = newNode;
        head = newNode;
        size++;
        
    }


    
    public void removeFirst() {
        // TODO Auto-generated method stub
        if(head == current)
        {
            head = head.next;
            current = head;
            if(current == null)
            {
                tail = null;
            }
            else
            {
                head.prev = null;
            }
            size--;
            return;
        }
        head = head.next;
        head.prev = null;
        size--;
    }

   
    public void insertLast(T data) {
        // TODO Auto-generated method stub
        if(head == null)
        {
            Node newNode = new Node(data);
            initlist(newNode);
            return;
        }
        Node newNode = new Node(data);
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;

    }

   
    public void removeLast() {
        // TODO Auto-generated method stub
        if(tail == current)
        {
            tail = tail.prev;
            current = tail;
            if(current == null)
            {
                head = null;
            }
            else
            {
                tail.next = null;
            }
            size--;
            return;
        }
        tail = tail.prev;
        tail.next = null;
        size--;

    }

    
    public T getFirst() {
        // TODO Auto-generated method stub
        return head.data;
    }

   
    public T getLast() {
        // TODO Auto-generated method stub
        return tail.data;
    }

    
    public T getCurrent() {
        // TODO Auto-generated method stub
        return current.data;
    }

    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return size==0;
    }

   
    public int size() {
        // TODO Auto-generated method stub
        return size;
    }

    
    public boolean isEnd() {
        // TODO Auto-generated method stub
        return current == tail;
    }

    public void next() {
        // TODO Auto-generated method stub
        current = current.next;
        if(current == null)
        {
            current = head;
        }
    }

    public void back() {
        // TODO Auto-generated method stub
        current = current.prev;
        if(current == null)
        {
            current = tail;
        }
    }

 
    public void insertBeforeCurrent(T data) {
        // TODO Auto-generated method stub
        if(head == null)
        {
            Node newNode = new Node(data);
            initlist(newNode);
            return;
        }
        if(head == current)
        {
            Node newNode = new Node(data);
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            size++;
            return;
        }
        Node newNode = new Node(data);
        Node temp = current.prev;
        newNode.next = current;
        newNode.prev = temp;
        temp.next = newNode;
        current.prev = newNode;
        size++;
    }

 
    public void reset() {
        // TODO Auto-generated method stub
        current = head;
    }
    
    
}
