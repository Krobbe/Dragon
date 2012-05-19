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
    	BALANCE_CHANGED,			//getValue(): IPlayer
    	COMMUNITY_CARDS_CHANGED,	//getValue(): ICard
    	CREATE_TABLE,				//getValue(): ArrayList<String> (cost, chips, players)
    	CREATE_TABLE_VIEW,			//getValue(): Table
    	CURRENT_BET_CHANGED,		//table.getTableCards()
    	DO_FOLD,
    	DO_CALL,
    	DO_CHECK,
    	DO_RAISE,
    	GET_ACCOUNT_INFORMATION,
    	GET_ACTIVE_GAMES,
    	GO_TO_CREATETABLE,
    	GO_TO_JOINTABLE,
    	GO_TO_MAIN,
    	GO_TO_REGISTER,
    	GO_TO_STATISTICS,
    	GO_TO_TABLE,
    	HANDS_CHANGED,	// getValue(): player of the hands that changes
    	HAND_DISCARDED,	// getValue(): player, the player who's hand was discarded
    	JOIN_TABLE,
    	LEAVE_TABLE,
    	LOGIN_FAILED,
    	LOGIN_SUCCESS,
    	LOGOUT_SUCCESS,
    	NEW_PLAYER_ADDED,			//getValue(): string, name of the player added
    	OWN_CURRENT_BET_CHANGED,	//getValue(): Bet
    	POT_CHANGED,				//getValue(): int
    	PUBLISH_ACTIVE_GAMES,		//getValue(): List<IServerGame>
    	PUBLISH_ACCOUNT_INFORMATION,//getValue(): Account
    	REGISTER_SUCCESS,
    	REGISTER_BACK,
    	REGISTER_FAILED,
    	REQUEST_CALL,
    	REQUEST_CHECK,
    	REQUEST_FOLD,
    	REQUEST_RAISE,
    	TRY_LOGIN, //The string is in the form "username password"
    	TRY_LOGOUT,
    	TRY_REGISTER, //ArrayList<char[]>
    	TURN_CHANGED,	// getValue(): int, index of the new player
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
