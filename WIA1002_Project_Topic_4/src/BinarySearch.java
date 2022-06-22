

/**
 *
 * @author User
 */
public class BinarySearch {
    private int array[];
    private int target;

    public BinarySearch() {
    }

    public BinarySearch(int[] array, int target) {
        this.array = array;
        this.target = target;
    }
    
    @SuppressWarnings("null")
    public int findFirstOccurrence(int[] array, int target){
        int low = 0, high = array.length-1;
        int result = -1;
        
        while(low <= high){
            int mid = low + (high-low)/2;
            
            if(array[mid] == target){ 
                result = mid;
                high = mid -1;
            } 
                
            
            else if(target < array[mid]) 
                low = mid + 1;
            
            else 
                high = mid - 1; 
        }
        return result;
    }
    
    public int findLastOccurrence(int[] array, int target){
        int low = 0, high = array.length-1;
        int result = -1;
        
        
        while(low <= high){
            int mid = low + (high-low)/2;
            
            if(array[mid] == target){
                result = mid;
                low = mid + 1;
            } 
                
            
            else if(target > array[mid]) 
                high = mid - 1;
                
            else 
                low = mid + 1; 
        }
        return result;
    }
}
