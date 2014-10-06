package crazy.eights;

/**
 * @author brendon-boldt
 */

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    Pile<Card> drawPile;
    Pile<Card> discardPile;
    ArrayList<Pile<Card>> hands;
    public final int handSize = 8;
    
    
    public enum Play {
        Valid, Invalid, Win;
    }
    
    public Deck() {
        this.hands = new Pile<>();
        this.discardPile = new Pile<>();
        
        this.drawPile = new Pile<>();
        for(int i = 0; i < Card.Suit.values().length; ++i) {
            for(int j = 0; j < Card.Rank.values().length; ++j) {
                drawPile.add(new Card(Card.Rank.values()[j],Card.Suit.values()[i]));
            }
        }
        this.discardPile.add(drawPile.remove(drawPile.size()-1));
    }
    
    @Override
    public String toString() {
        
        return "Draw Pile\t"+drawPile.size()+" card(s)\nDiscard Pile\t"+discardPile.size()+" card(s)\t"+discardPile.top();
    }
    
    public void shuffle(ArrayList<Card> pile) {
        Random rand = new Random(0); // WILL NEED TO BE CHANGED
        for(int i = 0; i < pile.size(); ++i) {
            swap(i,rand.nextInt(pile.size()));
        }
    }

    public void swap(int pos1, int pos2) {
        if ((pos1 >= 0 && pos1 < drawPile.size()) && (pos2 >= 0 && pos2 < drawPile.size())) {
            Card temp = drawPile.get(pos2);
            drawPile.set(pos2, drawPile.get(pos1));
            drawPile.set(pos1, temp);
        }
    }
    
    public Pile<Card> dealHand() {
        Pile<Card> hand = new Pile<>();
        for(int i = 0; i < handSize; ++i) {
            hand.add(drawPile.remove(0));
        }
        this.hands.add(hand);
        return hands.get(hands.size()-1);
    }
    
    public void drawCard(Pile<Card> hand) {
        if(!drawPile.isEmpty()) {
            hand.add(drawPile.remove(drawPile.size()-1));
        }
        else if(discardPile.size() > 1) {
            while(discardPile.size() > 1) {
                drawPile.add(discardPile.remove(0));
            }
            this.shuffle(drawPile);
            hand.add(drawPile.remove(0));
        }
        else {
            this.outOfCards();
        }
    }
    
    private boolean isValidPlay(Card card) {
        return card.suit == discardPile.top().suit || card.rank == discardPile.top().rank;
    }
    
    public Play playCard(Pile<Card> hand, int index) {
//        if(index >= 0 && index < hand.size()) {
        if (this.isValidPlay(hand.get(index))) {
            discardPile.add(hand.remove(index));
        }
//        }
        else {
            return Play.Invalid;
        }
        
        if(hand.isEmpty()) {
            return Play.Win;
        }
        else {
            return Play.Valid;
        }
    }
    
    public void outOfCards() {
        System.out.println("Deck has run out of cards.");
    }
}