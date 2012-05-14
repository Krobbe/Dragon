package client.event;

/**
 * A single event
 * @author hajo
 *
 */
public class Event {
    // All possible events listed
	//When you input a new tag, please put in alphabetical order.
    public enum Tag {
    	COMMUNITY_CARDS_CHANGED,	//getValue(): iCard
    	DO_FOLD,
    	DO_CALL,
    	DO_CHECK,
    	DO_RAISE,
    	GO_TO_CREATETABLE,
    	GO_TO_JOINTABLE,
    	GO_TO_MAIN,
    	CREATE_TABLE,
    	GO_TO_REGISTER,
    	GO_TO_STATISTICS,
    	HANDS_CHANGED,
    	JOIN_TABLE,
    	LOGIN_FAILED,
    	LOGIN_SUCCESS,
    	LOGOUT,
    	REGISTER_SUCCESS,
    	REGISTER_BACK,
    	REGISTER_FAILED,
    	REQUEST_CALL,
    	REQUEST_CHECK,
    	REQUEST_FOLD,
    	REQUEST_RAISE,
    	TRY_LOGIN, //The string is in the form "username password"
    	TRY_REGISTER, //ArrayList<char[]>
    	CURRENT_BET_CHANGED,
    	POT_CHANGED,
    	HAND_DISCARDED,
    	BALANCE_CHANGED,
    	OWN_CURRENT_BET_CHANGED,
    	TURN_CHANGED
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
