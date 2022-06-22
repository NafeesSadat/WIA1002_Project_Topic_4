

import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 *
 * @author User
 */
public class MyWeightPriorityQueue extends Weight {
    public MyWeightPriorityQueue() {
    }
    final LinkedList<Weight> list = new LinkedList<>();
    
    public void offer(Weight e){
        list.addLast(e);
    }
    
    @SuppressWarnings("empty-statement")
    public Weight poll(){
        if(list.isEmpty())
            throw new  EmptyStackException();
        
        else{
            Weight e = list.get(list.size()-1);;
            list.remove(list.size()-1);
            return e;
        }
    }
    
    public Weight peek(){
        return list.get(list.size()-1);
    }
    
    public void remove(int i){
        list.remove(i);
    }
    
    public String displayQueue(int i){
        return "Name :    " + list.get(i).getName() + "\n" + "Weight:   " + list.get(i).getWeight() + "kg" + "\n";
    }
    public int getSize(){
        return list.size();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public Weight get(int i){
        return list.get(i);
    }
    public MyWeightPriorityQueue sortTheMyWeightPriorityQueue(MyWeightPriorityQueue theMyWeightPriorityQueue) {
        MyWeightPriorityQueue tmpMyWeightPriorityQueue = new MyWeightPriorityQueue();

        while (!theMyWeightPriorityQueue.isEmpty()) {
            Weight tmpPair = theMyWeightPriorityQueue.poll();

            while (!tmpMyWeightPriorityQueue.isEmpty() && tmpMyWeightPriorityQueue.peek().getWeight() < tmpPair.getWeight()) {
                theMyWeightPriorityQueue.offer(tmpMyWeightPriorityQueue.poll());
            }

            tmpMyWeightPriorityQueue.offer(tmpPair);
        }

        return tmpMyWeightPriorityQueue;
    }
}
