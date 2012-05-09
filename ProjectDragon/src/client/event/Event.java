package client.event;

/**
 * A single event
 * @author hajo
 *
 */
public class Event {
    // All possible events listed
    public enum Tag {
    	
    	REQUEST_CALL,
    	REQUEST_FOLD,
    	REQUEST_CHECK,
    	REQUEST_RAISE,
    	DO_FOLD,
    	DO_CALL,
    	DO_CHECK,
    	DO_RAISE
   
    }
    private final Tag tag;
    // The new value 
    private final Object value;
    public Event(Tag tag, Object value){
        this.tag = tag;
        this.value = value;
    }
    public Tag getTag() {
        return tag;
    }
    public Object getValue() {
        return value;
    }
    @Override
    public String toString() {
        return "Event [tag=" + tag + ", value=" + value + "]";
    } 
    
    
}
