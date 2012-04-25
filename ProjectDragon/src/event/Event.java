package event;

/**
 * A single event
 * @author hajo
 *
 */
public class Event {
    // All possible events listed
    public enum Tag {
    	LOGIN,
    	LOGOUT,
    	GO_TO_REGISTER,
    	GO_TO_JOINTABLE,
    	GO_TO_CREATETABLE,
    	GO_TO_STATISTICS,
    	REGISTER_BACK,
    	REGISTER_ACCOUNT,
    	CREATE_TABLE,
    	JOIN_TABLE,
    	MAKE_BET,
    	DO_CALL,
    	DO_FOLD
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
