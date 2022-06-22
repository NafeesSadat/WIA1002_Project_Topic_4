
/**
 *
 * @author User
 */
import java.util.EmptyStackException;
import java.util.LinkedList;


public class MyAgilityPriorityQueue extends Agility {

    public MyAgilityPriorityQueue() {
    }
    final LinkedList<Agility> list = new LinkedList<>();
    
    public void offer(Agility e){
        list.addLast(e);
    }
    
    @SuppressWarnings("empty-statement")
    public Agility poll(){
        if(list.isEmpty())
            throw new  EmptyStackException();
        
        else{
            Agility e = list.get(list.size()-1);;
            list.remove(list.size()-1);
            return e;
        }
    }
    
    public Agility peek(){
        return list.get(list.size()-1);
    }
    
    public void remove(int i){
        list.remove(i);
    }
    
    public String displayQueue(int i){
        return "Name:    " + list.get(i).getName() + "\n" + "Agility:   " + list.get(i).getAgility() + "\n";
    }
    public int getSize(){
        return list.size();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public Agility get(int i){
        return list.get(i);
    }
    
    /**
     * Function to sort the Agility Queue
     * @param theMyAgilityPriorityQueue
     * @return
     */
    public  MyAgilityPriorityQueue sortTheMyAgilityPriorityQueue(MyAgilityPriorityQueue theMyAgilityPriorityQueue) {
        MyAgilityPriorityQueue tmpMyAgilityPriorityQueue = new MyAgilityPriorityQueue();

        while (!theMyAgilityPriorityQueue.isEmpty()) {
            Agility tmpPair = theMyAgilityPriorityQueue.poll();

            while (!tmpMyAgilityPriorityQueue.isEmpty() && tmpMyAgilityPriorityQueue.peek().getAgility() < tmpPair.getAgility()) {
                theMyAgilityPriorityQueue.offer(tmpMyAgilityPriorityQueue.poll());
            }

            tmpMyAgilityPriorityQueue.offer(tmpPair);
        }

        return tmpMyAgilityPriorityQueue;
    }
}
