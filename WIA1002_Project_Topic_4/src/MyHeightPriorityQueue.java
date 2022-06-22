
import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 *
 * @author User
 */
public class MyHeightPriorityQueue extends Height {
    public MyHeightPriorityQueue() {
    }
    final LinkedList<Height> list = new LinkedList<>();
    
    public void offer(Height e){
        list.addLast(e);
    }
    
    @SuppressWarnings("empty-statement")
    public Height poll(){
        if(list.isEmpty())
            throw new  EmptyStackException();
        
        else{
            Height e = list.get(list.size()-1);;
            list.remove(list.size()-1);
            return e;
        }
    }
    
    public Height peek(){
        return list.get(list.size()-1);
    }
    
    public void remove(int i){
        list.remove(i);
    }
    
    public String displayQueue(int i){
        return "Name  :   " + list.get(i).getName() + "\n" + "Height:   " + list.get(i).getHeight() + "cm" + "\n";
    }
    public int getSize(){
        return list.size();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public Height get(int i){
        return list.get(i);
    }
    
    /**
     * 
     * @param theMyHeightPriorityQueue
     * @return
     * Function to sort the Height Queue
     */
    public MyHeightPriorityQueue sortTheMyHeightPriorityQueue(MyHeightPriorityQueue theMyHeightPriorityQueue) {
        MyHeightPriorityQueue tmpMyHeightPriorityQueue = new MyHeightPriorityQueue();

        while (!theMyHeightPriorityQueue.isEmpty()) {
            Height tmpPair = theMyHeightPriorityQueue.poll();

            while (!tmpMyHeightPriorityQueue.isEmpty() && tmpMyHeightPriorityQueue.peek().getHeight() < tmpPair.getHeight()) {
                theMyHeightPriorityQueue.offer(tmpMyHeightPriorityQueue.poll());
            }

            tmpMyHeightPriorityQueue.offer(tmpPair);
        }

        return tmpMyHeightPriorityQueue;
    }
}
