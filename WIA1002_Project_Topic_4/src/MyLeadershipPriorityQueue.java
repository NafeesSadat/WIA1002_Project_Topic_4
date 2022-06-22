/**
 *
 * @author User
 */
import java.util.EmptyStackException;
import java.util.LinkedList;


public class MyLeadershipPriorityQueue extends Leadership {

    public MyLeadershipPriorityQueue() {
    }
    final LinkedList<Leadership> list = new LinkedList<>();
    
    public void offer(Leadership e){
        list.addLast(e);
    }
    
    @SuppressWarnings("empty-statement")
    public Leadership poll(){
        if(list.isEmpty())
            throw new  EmptyStackException();
        
        else{
            Leadership e = list.get(list.size()-1);;
            list.remove(list.size()-1);
            return e;
        }
    }
    
    public Leadership peek(){
        return list.get(list.size()-1);
    }
    
    public void remove(int i){
        list.remove(i);
    }
    
    public String displayQueue(int i){
        return "Name:           " + list.get(i).getName() + "\n" + "Leadership:   " + list.get(i).getLeadership() + "\n";
    }
    public int getSize(){
        return list.size();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public Leadership get(int i){
        return list.get(i);
    }
    
    /**
     * Function to sort the Leadership Queue
     * @param theMyLeadershipPriorityQueue
     * @return
     */
    public  MyLeadershipPriorityQueue sortTheMyLeadershipPriorityQueue(MyLeadershipPriorityQueue theMyLeadershipPriorityQueue) {
        MyLeadershipPriorityQueue tmpMyLeadershipPriorityQueue = new MyLeadershipPriorityQueue();

        while (!theMyLeadershipPriorityQueue.isEmpty()) {
            Leadership tmpPair = theMyLeadershipPriorityQueue.poll();

            while (!tmpMyLeadershipPriorityQueue.isEmpty() && tmpMyLeadershipPriorityQueue.peek().getLeadership() < tmpPair.getLeadership()) {
                theMyLeadershipPriorityQueue.offer(tmpMyLeadershipPriorityQueue.poll());
            }

            tmpMyLeadershipPriorityQueue.offer(tmpPair);
        }

        return tmpMyLeadershipPriorityQueue;
    }
}
