<<<<<<< HEAD
package crazyeights;

/**
 * @author brendon-boldt
=======
/**
 * Deck Class
 * Uses ArrayList class to make a standard 52-card deck.
 * @authors Krisztián Köves, Brendon Boldt
 * @date 9/29/2014
>>>>>>> c5f04169f0340815a272431ed3037852adbf71b2
 */

import java.util.ArrayList;

public class Deck {
<<<<<<< HEAD
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
=======
  ArrayList<Card> cards;
  
  public Deck() { 
    cards = new ArrayList<Card>();
    
    for(Card.Rank r : Card.Rank.values()) {
      for(Card.Suit s : Card.Suit.values())
        cards.add(new Card(r,s));
    }
  }
  
  @Override
  public String toString() {
    String s = "";
    
    for(int i = 0; i < 10; ++i)
      s = s + (i+1) + ". " + cards.get(i) + "\n";
    
    return s;
  }
  
  public static void main(String[] args) { 
    Deck d = new Deck();
    System.out.println(d);
  }
}
>>>>>>> c5f04169f0340815a272431ed3037852adbf71b2
