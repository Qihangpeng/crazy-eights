package crazy8s.player;


import crazy8s.game.Field;
import crazy8s.deck.Card;
import crazy8s.deck.Card.Suit;
import crazy8s.deck.Deck;
import crazy8s.game.Game;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 * @author brendon
 */
public class Human implements IPlayer {

//    protected List<Card> hand;
    protected final Field field;
    protected final String promptString = ": ~>";
    protected final String name;
    private static Integer constructorCount = 0;
    
//    /**
//     *
//     * @param argField Defines local field instance
//     * @param argHand Gives the player a hand
//     */
//    public Human(Field argField, List<Card> argHand) {
//        this.field = argField;
//        this.hand = Collections.unmodifiableList(argHand);
//        this.name = "Human" + constructorCount++;
//    }
    
    public Human(Field argField) {
        this.field = argField;
        this.name = "Human" + constructorCount++;
    }
    
    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public List<Card> getHand() {
        return field.getHand((IPlayer)this);
    }

    @Override
    public Integer getCommand() {
        
//        List<Card> sortedHand = hand;  
//        sortedHand.sort(Card.RankComparator);
//        sortedHand.sort(Card.SuitComparator);
        
        int command = Game.NO_COMMAND;
        String message = "";

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            while (command == Game.NO_COMMAND) {
                refreshDisplay(message);
                System.out.print(promptString);
                String str = in.readLine().toLowerCase().trim();

                switch (str) {
                    case "q":
                        command = Game.QUIT;
                        break;
                    case "d":
                        if(Deck.hasValidPlay(getHand(),field.deck.discardPile.get(0))) {
                            message = "Valid play present.";
                        }
                        else {
                            command = Game.DRAW;
                        }
                        break;
                    default:
                        for (int i = 0; i < 52; ++i) {
                            if (str.equals(i + "")) {
                                if(i < getHand().size()) {
                                    command = i;
                                    break;
                                }
                                else {
                                    message = "Invalid index.";
                                    break;
                                }
                            }
                        }
                        if (message.isEmpty())
                            message = "Invalid input.";
                        break;
                    case "s":
                        System.out.println("~~~Scores~~~");
                        for(IPlayer player : field.players) {
                            System.out.println(player + ": " + field.scores.get(player));
                        }
                        break;
                }
            }
        } catch (Exception e) {
        }
        return command;
    }

    public void printHand() {
        for (int i = 0; i < getHand().size(); ++i) {
            if (i < 10) {
                System.out.print("   ");
            } else {
                System.out.print("  ");
            }
            System.out.print(i);
        }
        System.out.println();
        getHand().stream().forEach((card) -> {
            System.out.print(" " + card);
        });
        System.out.println();
    }


    
    public void refreshDisplay(String message) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(
                 "Message: "
                + message
                + "\nHand Sizes: "
                + field.handSizesToString()
                + "\n"
                + field.deck 
                + "\n");
        printHand();
    }

    @Override
    public Suit getSuit() {
        Suit suit = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));            
            String input;
            
            do {
                refreshDisplay("Input a suit (c,h,s,d).");
                System.out.print(promptString);
                input = in.readLine();
                switch (input.trim().toLowerCase()) {
                    case("c"): suit = Suit.Clubs; break;
                    case("h"): suit = Suit.Hearts; break;
                    case("s"): suit = Suit.Spades; break;
                    case("d"): suit = Suit.Diamonds; break;
                    default:   suit = null; break;
                }
                        
            } while (suit == null);
            
        }
        catch (Exception e) {
            
        }
        return suit;
    }

}
