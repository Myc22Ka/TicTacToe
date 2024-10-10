package pl.polsl.lab1.krzysztof.gach.model;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Krzysztof Gach
 */
public class PlayersList {
    private List<Player> players;
    private int currentPlayerIndex;

    public PlayersList() {
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
}
