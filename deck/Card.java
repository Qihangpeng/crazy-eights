package crazy8s.deck;

import java.util.Comparator;

public class Card {

    /**
     * Specifies the suit of a card
     */
    public enum Suit {

        Clubs, Hearts, Spades, Diamonds
    }
    public static final Integer KING = 13;
    public static final Integer QUEEN = 12;
    public static final Integer JACK = 11;
    public static final Integer ACE = 1;

    private Integer rank;
    private Suit suit;

    public static void main(String[] args) {
        System.out.println(new Card(Card.ACE, Card.Suit.Spades));
    }

    public Card(Integer rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Integer getRank() {
        return this.rank;
    }

    public Suit getSuit() {
        return this.suit;
    }

    /**
     * Called when any player plays an eight. Since eights are wild; their suits
     * can be changed at the will of the player.
     * @param argSuit 
     */
    public void setSuit(Suit argSuit) {
        this.suit = argSuit;
    }
    
    @Override
    public String toString() {
        //return rank + " of " + suit;
        String rankString;
        String suitString;

        switch (this.rank) {
            case 13:
                rankString = " K";
                break;
            case 12:
                rankString = " Q";
                break;
            case 11:
                rankString = " J";
                break;
            case 10:
                rankString = "10";
                break;
            case 1:
                rankString = " A";
                break;
            default:
                rankString = " " + this.rank;
                break;
        }

    // Please replace these with the actual suit symbols.
        switch (this.suit) {
            case Clubs:
                suitString = "\u2663";
                break;
            case Hearts:
                suitString = "\u2665";
                break;
            case Spades:
                suitString = "\u2660";
                break;
            case Diamonds:
                suitString = "\u2666";
                break;
            default:
                suitString = null;
                break;
        }
        return rankString + suitString;
    }

    /**
     * Compares cards based on suit
     */
    public static Comparator<Card> SuitComparator = (Card o1, Card o2) -> o1.suit.compareTo(o2.suit);

    /**
     * Compares cards based on rank
     */
    public static Comparator<Card> RankComparator = (Card o1, Card o2) -> o1.rank.compareTo(o2.rank);
    
}
