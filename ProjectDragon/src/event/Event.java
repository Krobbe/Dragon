package event;

/**
 * A single event
 * @author hajo
 *
 */
public class Event {
    // All possible events listed
	//When you input a new tag, please put in alphabetical order.
    public enum Tag {
    	MAKE_BET,
    	SERVER_ADD_TABLE_CARDS,		//getValue(): List<iCard>
    	SERVER_CREATE_TABLE,		//getValue(): List<iPlayer>
    	SERVER_DISTRIBUTE_CARDS,	//getValue(): Map<iPlayer, iHand>
    	SERVER_DISTRIBUTE_POT,		//getValue(): Bet containing a player and h
    								//how much that should be added to his balance.
    	SERVER_FOLD,				//getValue(): iPlayer
    	SERVER_NEXT_TURN,			//getValue(): iPlayer
    	SERVER_NEW_ROUND,
    	SERVER_SET_OWN_CURRENT_BET,	//getValue(): Bet
    	SERVER_SET_PLAYER_UNACTIVE,	//getValue(): iPlayer
    	SERVER_SET_TURN,			//getValue(): iPlayer
    	SERVER_UPDATE_BET,			//getValue(): Bet
    	SERVER_UPDATE_POT,			//getValue(): Pot
    	SHOWDOWN_DONE //ej mottaget eller publicerat någonstans
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
