package crazy8s;

/**
 *
 * @author brendon-boldt
 */
import crazy8s.Deck.Play;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     *
     * @param args
     */
    public static void main(String[] args) {
        field = new Field(deck, players, scores);
        players.add(new Human(field));
        players.add(new Computer(field));
        players.stream().forEach((player) -> scores.put(player, 0));
        while (true) {
            newGame();
            dealHands();
            gameLoop();
        }
    }

    public static void newGame() {
        deck.resetDeck();
        deck.shuffle(deck.drawPile);
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
        while (status != Deck.Play.Win && status != Deck.Play.OutOfCards) {
            for (IPlayer player : players) {
                status = getCommand(player);
                if ((status == Deck.Play.Win || status == Deck.Play.OutOfCards)) {
                    endGame(player);
                    break;
                }
            }
        }
    }

    protected static void endGame(IPlayer argWinner) {
        for (IPlayer player : players) {
            for (Card card : player.getHand()) {
                if (card.getRank() == 8) {
                    scores.put(player, scores.get(player) + 50);
                } else if (card.getRank() >= 10) {
                    scores.put(player, scores.get(player) + 10);
                } else {
                    scores.put(player, scores.get(player) + card.getRank());
                }
            }
        }

        System.out.println("\n\n\n\n\n\n\n\n\n\n\nWinner: "
                + argWinner
                + "\n~~~Scores~~~");
        for (IPlayer player : field.players) {
            System.out.println(player + ": " + field.scores.get(player));
        }
        System.out.println("Press Enter to continue.");

        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException ex) {
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
        while (status != Deck.Play.Win && status != Deck.Play.OutOfCards) {
            command = player.getCommand();

            if (Objects.equals(command, DRAW)) {
                status = deck.drawCard(player);
            } else if (command >= 0) {
                status = deck.playCard(deck.hands.get(player), command);
                if (status == Deck.Play.Valid) {
                    break;
                } else if (status == Deck.Play.Eight) {

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
