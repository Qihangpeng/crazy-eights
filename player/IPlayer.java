package crazy8s.player;

import crazy8s.deck.Card;
import crazy8s.deck.Card.Suit;
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
