package crazy8s.player;

import crazy8s.game.Field;
import crazy8s.game.Game;
import crazy8s.deck.Card;
import crazy8s.deck.Card.Suit;
import crazy8s.deck.Deck;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author brendon-boldt at https://github.com/brendon-boldt
 */
public class Computer implements IPlayer {

//    protected final List<Card> hand;
    protected final Field field;
    protected final String name;
    private static Integer constructorCount = 0;

//    /**
//     *
//     * @param argField Defines local field instance
//     * @param argHand Gives the player a hand
//     */
//    public Computer(Field argField, List<Card> argHand) {
//        this.field = argField;
//        this.hand = Collections.unmodifiableList(argHand);
//        this.name = "Computer" + constructorCount++;
//    }
    public Computer(Field argField) {
        this.field = argField;
        this.name = "Computer" + constructorCount++;
    }

    @Override
    public List<Card> getHand() {
        return field.getHand((IPlayer) this);
    }

    @Override
    public Integer getCommand() {
        if (!Deck.hasValidPlay(getHand(), field.deck.discardPile.get(0))) {
            return Game.DRAW;
        } else {
            return findPlay();
        }
    }

    protected Integer findPlay() {
        Integer index = matchSuit(field.deck.discardPile.get(0).getSuit());
        if (!index.equals(-1)) {
            return index;
        }
        index = matchRank(field.deck.discardPile.get(0).getRank());
        if (!index.equals(-1)) {
            return index;
        }
        index = matchRank(8);
        if (!index.equals(-1)) {
            return index;
        }

        return Game.DRAW;
    }

    @Override
    public Suit getSuit() {
        Card.Suit suit = null;
        HashMap<Suit, Integer> suitCounts = new HashMap<>();
        for (Card.Suit s : Card.Suit.values()) {
            suitCounts.put(s, 0);
        }
        this.getHand().stream().forEach((card) -> {
            if (card.getRank() != 8) {
                suitCounts.put(card.getSuit(), suitCounts.get(card.getSuit()) + 1);
            }
        });
        
        Integer max = 0;
        for (Card.Suit s : Card.Suit.values()) {
            if(suitCounts.get(s) > max) {
                max = suitCounts.get(s);
                suit = s;
            }
        }   
        return suit;
    }

    /**
     *
     * @param argSuit
     * @return
     */
    public Integer matchSuit(Suit argSuit) {
//        getHand().sort(Card.RankComparator);
        for (int i = 0; i < getHand().size(); ++i) {
            if (getHand().get(i).getSuit().equals(argSuit)) {
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param argRank
     * @return
     */
    public Integer matchRank(Integer argRank) {
//        getHand().sort(Card.RankComparator);
        for (int i = 0; i < getHand().size(); ++i) {
            if (getHand().get(i).getRank().equals(argRank)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
