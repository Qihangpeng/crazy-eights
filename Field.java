package crazy8s;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * An access modifier class; players must be able to view what is on the playing
 * field without being able to modify it. This includes players' own hands.
 * @author brendon-boldt
 */
public class Field {
   protected final Deck deck;
   protected final HashMap<IPlayer, Integer> scores;
   protected final ArrayList<IPlayer> players;
   protected IPlayer currentPlayer;
   
   
   Field(Deck argDeck,
           ArrayList<IPlayer> argPlayers,
           HashMap<IPlayer, Integer> argScores) {
       this.deck = argDeck;
       this.scores = argScores;
       this.players = argPlayers;
   }

   
   public void update(IPlayer argPlayer) {
       this.currentPlayer = argPlayer;
   }

   public List<Card> getHand(IPlayer argPlayer) {
//       System.out.println(hands);
       return Collections.unmodifiableList(deck.hands.get(argPlayer));
   }

       protected String handSizesToString() {
        String string = "";
        string = this.players.stream().map((player) ->
                player + ": " + player.getHand().size() + "  ")
                .reduce(string, String::concat);
        return string;
    }
}
