package crazy.eights;

public class Card implements ICard {
  Rank rank;
  Suit suit;
  

  
  public static void main(String[] args) { 
    System.out.println(new Card(Card.Rank.Ace,Card.Suit.Spades));
    System.out.println(new Card(Card.Rank.Jack,Card.Suit.Diamonds));
    System.out.println(new Card(Card.Rank.Two,Card.Suit.Clubs));
  }
  
  public Card(Rank rank, Suit suit) {
    this.rank = rank;
    this.suit = suit;
  }
  
  public Rank getRank() {
    return this.rank;
  }
  
  public Suit getSuit() {
    return this.suit;
  }
       
  @Override
  public String toString() {
    return rank + "\tof\t" + suit;
  } 
}