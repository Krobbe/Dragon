package event;

/**
 * A single event
 * @author hajo
 *
 */
public class Event {
    // All possible events listed
    public enum Tag {
    	//TODO Add events here, MAKE_BET
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
