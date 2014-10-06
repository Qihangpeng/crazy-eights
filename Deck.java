/**
 * Deck Class
 * Uses ArrayList class to make a standard 52-card deck.
 * @authors Krisztián Köves, Brendon Boldt
 * @date 9/29/2014
 */

import java.util.ArrayList;
import java.util.Random;

public class Deck {
  ArrayList<Card> cards;
  Random rand;
  
  public Deck() { 
    cards = new ArrayList<Card>();
    rand = new Random(0);
    
    for(Card.Rank r : Card.Rank.values()) {
      for(Card.Suit s : Card.Suit.values())
        cards.add(new Card(r,s));
    }
    
    shuffle();
  }
  
  @Override
  public String toString() {
    String s = "";
    
    for(int i = 0; i < 10; ++i)
      s = s + (i+1) + ". " + cards.get(i) + "\n";
    
    return s;
  }
  
  public void shuffle() {
    for(int index=0; index < cards.size(); index++) {
      Card card1 = cards.get(index);
      int lottery = rand.nextInt(cards.size());
      Card card2 = cards.get(lottery);
      cards.set(index,card2);
      cards.set(lottery,card1);
    }
  }
  
  public static void main(String[] args) { 
    Deck d = new Deck();
    System.out.println(d);
  }
}