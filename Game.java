package crazy8s;

/**
 *
 * @author brendon-boldt
 */
import crazy8s.Deck.Play;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Game {

    public static final Integer NO_COMMAND = -1;
    public static final Integer QUIT = -2;
    public static final Integer DRAW = QUIT - 1;
    public static final Integer SHOW = QUIT - 2;

    protected static ArrayList<IPlayer> players = new ArrayList<>();
    protected static Deck deck = new Deck();
    protected static Field field;
    private static HashMap<IPlayer, Integer> scores = new HashMap<>();

    //private static HashMap<IPlayer, List<Card>> hands;

    /**
     * [Javadoc comment missing]
     * @param args
     */
    public static void main(String[] args) {
        field = new Field(deck, players, scores);
        deck.shuffle(deck.drawPile);
//        players.add(new Human(field, deck.dealHand()));
//        players.add(new Computer(field, deck.dealHand()));
        players.add(new Human(field));
        players.add(new Computer(field));
        dealHands();
        gameLoop();
    }

    private static void dealHands() {
        deck.hands = new HashMap<>();
        players.stream().forEach((player) -> {
            deck.dealHand(player);
        });
    }

    /**
     * Main game loop
     */
    protected static void gameLoop() {
        Integer command;
        Deck.Play status = Deck.Play.Valid;
        while (status != Deck.Play.Win || status != Deck.Play.OutOfCards) {
            for(IPlayer player : players) {
               status = getCommand(player);
               if ((status == Deck.Play.Win || status == Deck.Play.OutOfCards))
                   break;
           }
        }
    }

    /**
     * Gets and interprets command from specified player
     *
     * @param player Player to get command from
     * @return Status after play
     */
    protected static Play getCommand(IPlayer player) {
        Integer command;
        Play status = Play.Invalid;
        while (status != Deck.Play.Win || status != Deck.Play.OutOfCards) {
            command = player.getCommand();

            if (Objects.equals(command, DRAW)) {
                status = deck.drawCard(player);
            } else if (command >= 0) {
                status = deck.playCard(deck.hands.get(player), command);
                if (status == Deck.Play.Valid) {
                    break;
                } else {
                    continue;
                }
            } else if (Objects.equals(command, QUIT)) {
                System.exit(0);
            }
        }
        return status;
    }

    /**
     * Unused as of now as everything is static
     */
    protected Game() {
        players = new ArrayList<>();
        deck = new Deck();
        field = new Field(deck, players, scores);
    }
}
