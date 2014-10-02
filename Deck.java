package crazy.eights;

/**
 * Deck Class
 * Uses ArrayList class to make a standard 52-card deck.
 * @authors Krisztián Köves, Brendon Boldt
 * @date 9/29/2014
 */

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    ArrayList<Card> drawPile;
    ArrayList<ArrayList<Card>> hands;
    
    public Deck() {
        this.drawPile = new ArrayList<>();
        for(int i = 0; i < Card.Suit.values().length; ++i) {
            for(int j = 0; j < Card.Rank.values().length; ++j) {
                drawPile.add(new Card(Card.Rank.values()[j],Card.Suit.values()[i]));
            }
        }
    }
    
    public void shuffle() {
        ArrayList<Card> newPile = new ArrayList<>();
        Random randIndex = new Random();
        int index;
        for(int i = drawPile.size(); i > 0; --i) {
            index = randIndex.nextInt(i);
            newPile.add(drawPile.get(index));
            drawPile.remove(index);
        }
        drawPile = newPile;
    }
    
    public void deal() {
        for(int i = 0; i < 5 ; ++i) {
            System.out.println(drawPile.get(i));
        }
    }
}
