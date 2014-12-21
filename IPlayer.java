package crazy8s;

import crazy8s.Card.Suit;
import java.util.List;

/**
 *
 * @author brendon
 */
public interface IPlayer {
    
    public List<Card> getHand();
    public Integer getCommand();
    public Suit getSuit();
//    public void printHand();
}
