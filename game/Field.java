package crazy8s.game;


import crazy8s.deck.Deck;
import crazy8s.deck.Card;
import crazy8s.player.IPlayer;
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
   public final Deck deck;
   public final HashMap<IPlayer, Integer> scores;
   public final ArrayList<IPlayer> players;
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

       public String handSizesToString() {
        String string = "";
        string = this.players.stream().map((player) ->
                player + ": " + player.getHand().size() + "  ")
                .reduce(string, String::concat);
        return string;
    }
}
