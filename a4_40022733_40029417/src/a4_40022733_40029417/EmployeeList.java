//Kevin Tan 40022733, Sagar Patel 40029417
//COMP249
//Assignment #4
//April 10, 2017

/*
LinkedList that stores Employee objects, opted to use head and tail Nodes along with a counter for the size as Nodes are added/removed.
 */
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

    //Initializes head and tail if there are no nodes
    //Otherwise adds elements on to the end
    //Size is incremented when Nodes are being added, and decremented when Nodes are being removed
    public void add(Employee data) {
        if(size == 0){
            tail = head = new Node(data, null);
            size++;
        }else{
           addToEnd(data); 
        }      
    }
    
    //New Node points to head and head moves to that Node
    public void addToStart(Employee data) {
        head = new Node(data, head);
        size++;
    }

    //New Node is added to element after tail, and tail is moved to new end of list
    public void addToEnd(Employee data) {
        tail.next = new Node(data, null);
        tail = tail.next;
        size++;
    }

    //Moves head to next Node, removing first Node through garbage collection
    public void removeFirst() {
        head = head.next;
        size--;
    }

    //Checks special cases where list has 1 Node, 2 Nodes where there's be one Node being both head and tail
    //General case, pointed goes to second last Node, tail is pointed to that Node, and last element is removed
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
    
    //Returns a deep copy of Employee in Node at specified index
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

    //Returns deep copies of first Node or last Node
    public Employee getFirstValue() {
        return head.data.clone();
    }

    public Employee getLastValue() {
        return tail.data.clone();
    } 
}