
import java.util.EmptyStackException;
import java.util.LinkedList;


public class MyPriorityQueue extends DangerPair {
    final LinkedList<DangerPair> list = new LinkedList<>();
    
    public void offer(DangerPair e){
        list.addLast(e);
    }
    
    @SuppressWarnings("empty-statement")
    public DangerPair poll(){
        if(list.isEmpty())
            throw new  EmptyStackException();
        
        else{
            DangerPair e = list.get(list.size()-1);;
            list.remove(list.size()-1);
            return e;
        }
    }
    
    public DangerPair peek(){
        return list.get(list.size()-1);
    }

    public void displayQueue(){
        for (int i = 0; i <  list.size(); i++) {
            DangerPair danger = list.get(i);
            System.out.println(danger.getIndex()+ " " +danger.getRisk());
        }
    }
    public int getSize(){
        return list.size();
    }
    public boolean isEmpty(){
        return list.isEmpty();
    }
    public DangerPair get(int i){
        return list.get(i);
    }
}
