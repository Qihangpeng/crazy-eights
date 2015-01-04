package crazy8s;

/**
 * @author brendon-boldt
 */
import crazy8s.Deck.Play;
import static crazy8s.Game.deck;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Handles all cards on the field; deck is the only class that can actually make
 * changes to the location/state of the cards. Note that the 0 index represents
 * the top of a pile.
 *
 * @author brendon-boldt
 */
public class Deck {

    protected ArrayList<Card> drawPile;
    protected ArrayList<Card> discardPile;
    protected HashMap<IPlayer, List<Card>> hands;
    public final Integer handSize;
    public Random rand;

    public enum Play {

        Valid, Invalid, Win, OutOfCards, Eight;
    }

    public Deck() {
        rand = new Random(1); // WILL NEED TO BE CHANGED
        this.handSize = 1;
        this.discardPile = new ArrayList<>();

        this.drawPile = new ArrayList<>();
        for (int i = 0; i < Card.Suit.values().length; ++i) {
            for (int j = 1; j <= 13; ++j) {
                drawPile.add(new Card(j, Card.Suit.values()[i]));
            }
        }
        this.discardPile.add(0, drawPile.remove(0));
    }

    public void resetDeck() {
        this.hands = new HashMap<>();
        this.discardPile = new ArrayList<>();

        this.drawPile = new ArrayList<>();
        for (int i = 0; i < Card.Suit.values().length; ++i) {
            for (int j = 1; j <= 13; ++j) {
                drawPile.add(new Card(j, Card.Suit.values()[i]));
            }
        }
        this.discardPile.add(0, drawPile.remove(0));
        shuffle(deck.drawPile);
    }

    @Override
    public String toString() {
        return "Draw Pile\t"
                + drawPile.size()
                + " card(s)\nDiscard Pile\t"
                + discardPile.size()
                + " card(s)\t"
                + discardPile.get(0);
    }

    public void shuffle(ArrayList<Card> pile) {
        for (int i = 0; i < pile.size(); ++i) {
            swap(i, rand.nextInt(pile.size()));
        }
    }

    protected void swap(int pos1, int pos2) {
        if ((pos1 >= 0 && pos1 < drawPile.size()) && (pos2 >= 0 && pos2 < drawPile.size())) {
            Card temp = drawPile.get(pos2);
            drawPile.set(pos2, drawPile.get(pos1));
            drawPile.set(pos1, temp);
        }
    }

    public void dealHand(IPlayer argPlayer) {
        hands.put(argPlayer, new ArrayList<>());
        for (int i = 0; i < handSize; ++i) {
            hands.get(argPlayer).add(drawPile.remove(0));
        }
    }

    public Play drawCard(IPlayer argPlayer) {
        if (hasValidPlay(hands.get(argPlayer))) {
            return Play.Invalid;
        }

        if (!drawPile.isEmpty()) {
            hands.get(argPlayer).add(drawPile.remove(drawPile.size() - 1));
        } else if (discardPile.size() > 1) {
            while (discardPile.size() > 1) {
                drawPile.add(discardPile.remove(1));
            }
            this.shuffle(drawPile);
            hands.get(argPlayer).add(drawPile.remove(0));
        } else {
            return Play.OutOfCards;
        }
        return Play.Valid;
    }

    public boolean hasValidPlay(List<Card> cards) {
        Boolean flag = false;
        for (Card card : cards) {
            flag |= isValidPlay(card);
        }
        return flag;
    }

    /**
     * This function is useful for IPlayer classes; thus it is static.
     *
     * @param cards
     * @param discard
     * @return
     */
    public static boolean hasValidPlay(List<Card> cards, Card discard) {
        Boolean flag = false;
        for (Card card : cards) {
            flag |= isValidPlay(card, discard);
        }
        return flag;
    }

    public boolean isValidPlay(Card card) {
        return card.getSuit() == discardPile.get(0).getSuit()
                || Objects.equals(card.getRank(), discardPile.get(0).getRank())
                || Objects.equals(card.getRank(), 8);
    }

    /**
     * Static for convenience
     *
     * @param card
     * @param discard
     * @return
     */
    public static boolean isValidPlay(Card card, Card discard) {
        return card.getSuit() == discard.getSuit()
                || Objects.equals(card.getRank(), discard.getRank())
                || Objects.equals(card.getRank(), 8);
    }

    public Play playCard(List<Card> hand, int index) {
        Card played = null;
        if (index >= 0 && index < hand.size()) {
            if (this.isValidPlay(hand.get(index))) {
//                System.out.println("Here!");
                played = hand.get(index);
                discardPile.add(0, hand.remove(index));
            } else {
                return Play.Invalid;
            }
        }

        if (hand.size() == 0) {
            return Play.Win;
        } else if (played.getRank() == 8) {
            return Play.Eight;
        } else {
            return Play.Valid;
        }
    }

    public Play outOfCards() {
        //System.out.println("Deck has run out of cards.");
        return Play.OutOfCards;
    }

}
