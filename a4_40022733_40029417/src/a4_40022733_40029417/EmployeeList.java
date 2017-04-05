package a4_40022733_40029417;

public class EmployeeList {

    private class Node {

        private Employee data;
        private Node next;

        public Node() {
            data = null;
            next = null;
        }

        public Node(Employee data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public EmployeeList() {
        head = null;
        tail = null;
        size = 0;
    }

    public EmployeeList(Employee data) {
        tail = head = new Node(data, null);
        size = 1;
    }

    public void add(Employee data) {
        if(size == 0){
            tail = head = new Node(data, null);
            size++;
        }else{
           addToEnd(data); 
        }      
    }
    
    public void addToStart(Employee data) {
        head = new Node(data, head);
        size++;
    }

    public void addToEnd(Employee data) {
        tail.next = new Node(data, null);
        tail = tail.next;
        size++;
    }

    public void removeFirst() {
        head = head.next;
        size--;
    }

    public void removeLast() {
        if (size == 1 || head == null) {
            head = null;
            size = 0;
        } else if(size == 2){
            tail = head;
            head.next = null;
            size--;
        }else{
            Node temp = head;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            tail = temp;
            temp.next = null;
            size--;
            temp = null;
        }
    }
    
    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }
    
    public Employee getValue(int index) {
        if (index > size) {
            return null;
        } else {
            Node temp = head;
            for (int i = 1; i < index; i++) {
                temp = temp.next;
            }
            Employee x = temp.data.clone();
            temp = null;
            return x;
        }
    }

    public Employee getFirstValue() {
        return head.data.clone();
    }

    public Employee getLastValue() {
        return tail.data.clone();
    } 
}