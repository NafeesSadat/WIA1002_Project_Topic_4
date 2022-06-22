
/**
 *
 * @author User
 */
import java.util.EmptyStackException;
import java.util.LinkedList;


public class MyIntelligencePriorityQueue extends Intelligence {

    public MyIntelligencePriorityQueue() {
    }
    final LinkedList<Intelligence> list = new LinkedList<>();
    
    public void offer(Intelligence e){
        list.addLast(e);
    }
    
    @SuppressWarnings("empty-statement")
    public Intelligence poll(){
        if(list.isEmpty())
            throw new  EmptyStackException();
        
        else{
            Intelligence e = list.get(list.size()-1);;
            list.remove(list.size()-1);
            return e;
        }
    }
    
    public Intelligence peek(){
        return list.get(list.size()-1);
    }
    
    public void remove(int i){
        list.remove(i);
    }
    
    public String displayQueue(int i){
        return "Name:             " + list.get(i).getName() + "\n" + "Intelligence:   " + list.get(i).getIntelligence() + "\n";
    }
    public int getSize(){
        return list.size();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public Intelligence get(int i){
        return list.get(i);
    }
    
    /**
     * Function to sort the Intelligence Queue
     * @param theMyIntelligencePriorityQueue
     * @return
     */
    public  MyIntelligencePriorityQueue sortTheMyIntelligencePriorityQueue(MyIntelligencePriorityQueue theMyIntelligencePriorityQueue) {
        MyIntelligencePriorityQueue tmpMyIntelligencePriorityQueue = new MyIntelligencePriorityQueue();

        while (!theMyIntelligencePriorityQueue.isEmpty()) {
            Intelligence tmpPair = theMyIntelligencePriorityQueue.poll();

            while (!tmpMyIntelligencePriorityQueue.isEmpty() && tmpMyIntelligencePriorityQueue.peek().getIntelligence() < tmpPair.getIntelligence()) {
                theMyIntelligencePriorityQueue.offer(tmpMyIntelligencePriorityQueue.poll());
            }

            tmpMyIntelligencePriorityQueue.offer(tmpPair);
        }

        return tmpMyIntelligencePriorityQueue;
    }
}
