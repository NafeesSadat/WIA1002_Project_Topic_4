import java.util.LinkedList;

/**
 *
 * @author Nafees
 */

public class MyHashMap {
    private LinkedList<Entry>[] myHashMap = new LinkedList[2];
    int size = 0;

    public MyHashMap() {}
    
    //Method to put elements in the Map-->>
    public void put(Key key, Value value){
        if(size >= myHashMap.length)resize();
        
        int index = getIndex(key) % myHashMap.length;
        
        if(myHashMap[index] == null){
            myHashMap[index] = new LinkedList<>();
            myHashMap[index].add(new Entry(key, value));
            size++;
            return;
        }
        
        else{
            for(Entry entry : myHashMap[index]){
                if(entry.key.equals(key)){
                    entry.value = value;
                    size++;
                    return;
                }
            }
        }
        myHashMap[index].add(new Entry(key, value));
        size++;
    }
    
    //Method to get a key Value in the Map-->>
    public Value get(Key key){
        int index = getIndex(key) % myHashMap.length;
        
        if(myHashMap[index] == null)return null;
        
        for(Entry entry : myHashMap[index]){
            if(entry.key.equals(key))
                return entry.value;
        }
        
        return null;
    }
    
    //Method to remove elements from the Map-->>
    public void remove(Key key){
        if(key == null)return;
        int index = getIndex(key) % myHashMap.length;
        
        if(myHashMap[index] == null)return;
        
        Entry toRemove = null;
        for(Entry entry : myHashMap[index]){
            if(entry.key.equals(key))
                toRemove = entry;
        }
        if(toRemove == null)return;
        myHashMap[index].remove(toRemove);
        size--;
    }
    
    //Checks if a key exists in the Map-->>
    public boolean containsKey(Key key){
        if(key == null)return false;
        int index = getIndex(key) % myHashMap.length;
        
        if(myHashMap[index] == null)return false;
        
        for(Entry entry : myHashMap[index]){
            if(entry.key.equals(key))
                return true;
        }
        return false;
    }
    
    //Method to increase the map size-->>
    private void resize(){
        LinkedList<Entry>[] myOldHashMap = myHashMap;
        myHashMap = new LinkedList[size*2];
        size = 0;
        for (LinkedList<Entry> myOldHashMap1 : myOldHashMap) {
            if (myOldHashMap1 == null) {
                continue;
            }
            for (Entry entry : myOldHashMap1) {
                put(entry.key, entry.value);
            }
        }
    }
    
    //Method to get the index of a specific key-->>
    private int getIndex(Key key) {return key.hashCode();}
    
    //Returns the Map sizee-->>
    public int size(){return size;}
    
}
