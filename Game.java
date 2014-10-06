package crazy.eights;

/**
 *
 * @author brendon
 */

import java.util.ArrayList;

public class Game {
    
    
    public static void main(String[] args) {
        Deck deck = new Deck();
        ArrayList<Player> players = new ArrayList<>();
        
        
        deck.shuffle(deck.drawPile);
        players.add(new Player(deck.dealHand()));
//        System.out.println(deck.discardPile.top());
//        players.get(0).printHand();
//        System.out.println(deck.playCard(players.get(0).hand, 7));
//        System.out.println(deck.discardPile.top());
        System.out.println(deck);
    }
}
