

import java.util.*;


public class MyGenericStack<E> {
    private final ArrayList<E> list;

    public MyGenericStack() {
        this.list = new ArrayList<>();
    }
    
        //This method returns the Stack size -->>
    public int getSize(){
        return list.size();
    }
    
        //This method returns the top element of the Stack -->>
    public E peek(){
        return list.get(list.size()-1);
    }
    
        //This method returns and removes the top element of the Stack -->>
    @SuppressWarnings("empty-statement")
    public E pop(){
        try{
            E e = list.get(list.size()-1);;
            list.remove(list.size()-1);
            return e;
        }
        catch(NullPointerException n){
            throw new EmptyStackException();
        }
    }
    
        //This method adds a new element to the stack -->>
    public void push(E e){
        try{
            list.add(e);
        }
        catch(NullPointerException n){
            throw new EmptyStackException();
        }
    }
    
        //This method checks if the Stck is empty -->>
    public boolean isEmpty(){
        return list.isEmpty();
    }
    
        //This method gets an element from the Stack -->>
    public E getElement(int e) {
        try{
            return list.get(e);
        }
        catch(NoSuchElementException n){
            throw new NoSuchElementException();
        }
    }
    
        //This method gets an index of an element from the Stack -->>
    public int getIndexOf(E e){
        try{
            return list.indexOf(e);
        }
        catch(NoSuchElementException n){
            throw new NoSuchElementException();
        }
    }
    public void sort(){
        Collections.reverse(list);
    }
    
    public void clear(){
        list.clear();
    }

    @Override
    public String toString() {
        return "Stack: " + list;
    }

        //This mthod checks if elements of two lists are equal -->>
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MyGenericStack<?> other = (MyGenericStack<?>) obj;
        return Objects.equals(this.list, other.list);
    }

}
