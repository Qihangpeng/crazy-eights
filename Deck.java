package crazyeights;

/**
 * @author brendon-boldt
 */

import java.util.ArrayList;

public class Deck {
    ArrayList drawPile = new ArrayList();
    
    public Deck() {
        for(int i = 0; i < Card.Suit.values().length; ++i) {
            for(int j = 0; j < Card.Rank.values().length; ++j) {
                drawPile.add(new Card(Card.Rank.values()[j],Card.Suit.values()[i]));
            }
        }
    }
    
    public void deal() {
        for(int i = 0; i < 5 ; ++i) {
            System.out.println(drawPile.get(i));
        }
    }
}
