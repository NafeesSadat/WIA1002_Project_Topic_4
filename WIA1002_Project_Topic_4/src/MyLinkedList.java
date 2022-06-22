
/**
 *
 * @author User
 * @param <E>
 */
public class MyLinkedList<E> {
    private Node<E> head;
    private int size;

    public MyLinkedList() {
        size=0;
    }
    
//    Adding element from first index -->>
    public void addFirst(E e){
        
        Node<E> newNode = new Node<>(e);
        
//      Checking if the list is empty -->>  

        if(head == null){
            head = newNode;
            size++;
            return;
        }
 
        newNode.next = head; // Pointing new head's next to old head;
        head = newNode;     // Assigning the new head;
        
        size++;
    }
    
//    Adding element from last index -->>
    public void addLast(E e){
        
        Node<E> newNode = new Node<>(e);
        
//        Checking if the list is empty -->>

        if(head == null){
            head = newNode;
            size++;
            return;
        }
        
        Node<E> currNode = head;
        
//        Traverse to get the last element/ Traverse over a Linked List -->>

        while(currNode.next != null){
            currNode = currNode.next;
        }
        currNode.next = newNode;  // Point the second last node to new node
        
        size++; 
    }
    
//    At element at any Index -->>
    public void add(int index, E e){
        
//        Checking if index out of range -->>

        if(index < 0 || index > size)
             throw new IndexOutOfBoundsException();
        
//        Index = 0 means adding a element in the first index of the list -->>
        
        else if(index == 0){
            addFirst(e);
        }
        
//        Index = size means adding a element in the last index of the list -->>
        
        else if(index == size){
            addLast(e);
        }
        
        else{
            Node<E> newNode=new Node<>(e);
            Node<E> currNode = head;
            for (int i = 1; i < size; i++) {
                if(i == index){
                    Node<E> tmp = currNode.next;
                    currNode.next = newNode;
                    newNode.next = tmp;
                }
                currNode = currNode.next;  //currNode = second last element before given index;
                size++;
            }
        }
        
    }
    
//    Removing the first element -->>
    public void removeFirst(){
        
        if(head == null){
            return;
        }
        
        head = head.next; //Removing the head;
        size--;
        
    }
    
//    Remove the first element of the list -->>
    public void removeLast(){
        
        if(head == null)
            return;
        
        else if(size==1){
            head = null;
        }
        
        else{
            Node<E> secondLastNode = head;
            
//            Traverse to get the second last element -->>

            for (int i = 1; i < size - 1; i++) {
                secondLastNode = secondLastNode.next;
            }
            secondLastNode.next = null;   
        }
        
        size--;
        
    }
    
//    Remove the last element of the list -->>
    public void remove(int index){
        
        if(index < 0 || index > size)
             throw new IndexOutOfBoundsException();
        
        else if(index==0){
            removeFirst();
        }
        
        else if(index == size-1){
            removeLast();
        }
        
        else {
            Node<E> currNode = head;
            for (int i = 1; i < index; i++) {
                currNode = currNode.next;
            }
            currNode.next = currNode.next.next;
            size--;
        }
    }
    
//    print Linked list -->>
    public void printList(){
        
        Node<E> currNode = head;
        
//        If the list is empty --> print null;
        
        if(head == null){
            System.out.println(head);
            return;
        }
        
//        Traverse until next node is null/ Traverse over a Linked List -->>

        while(currNode != null){
            System.out.print(currNode.element + " ");
            currNode = currNode.next;
        }
        
        System.out.println("");
        
    }
    
//    To clear the List -->>
    public void clear(){
        size = 0;
        head = null;
    }
    
//    Get the Linked List size -->>
    public int getSize() {
        return size;
    }
    
//    Checking if an element is in the list -->>
    public boolean contains(E e) {
        
        if(head == null){
            return false;
        }
        
        Node<E> searchingNode = new Node<>(e);
        Node<E> currNode = head;
        boolean contains = false;
        
        while(currNode.next != null){
            if(currNode.element == searchingNode.element)
                contains = true;
            currNode = currNode.next;
        }
        
        return contains;
        
  }
    
//    Get an element from a index -->>
    public E get(int index) {
        if(index == 0)
            return head.element;
        else{
            Node<E> currNode = head;
            for (int i = 1; i < index; i++) {
                currNode = currNode.next;
            }
            return currNode.next.element;
        }
  }
    
//    Replace the element at a specified position -->>
    public void set(int index, E e) {
        
        Node<E> newNode = new Node<>(e);
        
        if(index == 0){
            Node<E> tmp = head;
            head = newNode;
            newNode.next = tmp;
            size++;
            return;
        }
        
        Node<E> currNode = head;
        for (int i = 1; i < index; i++) {
            currNode = currNode.next;
        }
        Node<E> tmp = currNode.next; //CurrNode = element before index; tmp = index element;
        currNode.next = newNode;
        newNode.next = tmp.next;
  } 
    
}