
/**
 *
 * @author User
 */
import java.util.EmptyStackException;
import java.util.LinkedList;


public class MyCoordinationPriorityQueue extends Coordination {

    public MyCoordinationPriorityQueue() {
    }
    final LinkedList<Coordination> list = new LinkedList<>();
    
    public void offer(Coordination e){
        list.addLast(e);
    }
    
    @SuppressWarnings("empty-statement")
    public Coordination poll(){
        if(list.isEmpty())
            throw new  EmptyStackException();
        
        else{
            Coordination e = list.get(list.size()-1);;
            list.remove(list.size()-1);
            return e;
        }
    }
    
    public Coordination peek(){
        return list.get(list.size()-1);
    }
    
    public void remove(int i){
        list.remove(i);
    }
    
    public String displayQueue(int i){
        return "Name:               " + list.get(i).getName() + "\n" + "Coordination:   " + list.get(i).getCoordination() + "\n";
    }
    public int getSize(){
        return list.size();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public Coordination get(int i){
        return list.get(i);
    }
    
    /**
     * Function to sort the Coordination Queue
     * @param theMyCoordinationPriorityQueue
     * @return
     */
    public  MyCoordinationPriorityQueue sortTheMyCoordinationPriorityQueue(MyCoordinationPriorityQueue theMyCoordinationPriorityQueue) {
        MyCoordinationPriorityQueue tmpMyCoordinationPriorityQueue = new MyCoordinationPriorityQueue();

        while (!theMyCoordinationPriorityQueue.isEmpty()) {
            Coordination tmpPair = theMyCoordinationPriorityQueue.poll();

            while (!tmpMyCoordinationPriorityQueue.isEmpty() && tmpMyCoordinationPriorityQueue.peek().getCoordination() < tmpPair.getCoordination()) {
                theMyCoordinationPriorityQueue.offer(tmpMyCoordinationPriorityQueue.poll());
            }

            tmpMyCoordinationPriorityQueue.offer(tmpPair);
        }

        return tmpMyCoordinationPriorityQueue;
    }
}
