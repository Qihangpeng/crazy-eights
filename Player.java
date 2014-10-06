package crazy.eights;
/**
 *
 * @author brendon
 */

public class Player {
    Pile<Card> hand;
    
    public Player(Pile<Card> hand) {
        this.hand = hand;
    }
    
    public void printHand() {
        for(int i = 0; i < hand.size(); ++i) {
            System.out.println((i+1)+". "+hand.get(i));
        }
    }
}
