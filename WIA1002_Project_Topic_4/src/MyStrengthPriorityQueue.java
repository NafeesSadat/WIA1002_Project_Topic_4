

/**
 *
 * @author User
 */
import java.util.EmptyStackException;
import java.util.LinkedList;


public class MyStrengthPriorityQueue extends Strength {

    public MyStrengthPriorityQueue() {
    }
    final LinkedList<Strength> list = new LinkedList<>();
    
    public void offer(Strength e){
        list.addLast(e);
    }
    
    @SuppressWarnings("empty-statement")
    public Strength poll(){
        if(list.isEmpty())
            throw new  EmptyStackException();
        
        else{
            Strength e = list.get(list.size()-1);;
            list.remove(list.size()-1);
            return e;
        }
    }
    
    public Strength peek(){
        return list.get(list.size()-1);
    }
    
    public void remove(int i){
        list.remove(i);
    }
    
    public String displayQueue(int i){
        return "Name   :    " + list.get(i).getName() + "\n" + "Strength:   " + list.get(i).getStrength() + "\n";
    }
    public int getSize(){
        return list.size();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public Strength get(int i){
        return list.get(i);
    }
    
    /**
     * Function to sort the Strength Queue
     * @param theMyStrengthPriorityQueue
     * @return
     */
    public  MyStrengthPriorityQueue sortTheMyStrengthPriorityQueue(MyStrengthPriorityQueue theMyStrengthPriorityQueue) {
        MyStrengthPriorityQueue tmpMyStrengthPriorityQueue = new MyStrengthPriorityQueue();

        while (!theMyStrengthPriorityQueue.isEmpty()) {
            Strength tmpPair = theMyStrengthPriorityQueue.poll();

            while (!tmpMyStrengthPriorityQueue.isEmpty() && tmpMyStrengthPriorityQueue.peek().getStrength() < tmpPair.getStrength()) {
                theMyStrengthPriorityQueue.offer(tmpMyStrengthPriorityQueue.poll());
            }

            tmpMyStrengthPriorityQueue.offer(tmpPair);
        }

        return tmpMyStrengthPriorityQueue;
    }
}
