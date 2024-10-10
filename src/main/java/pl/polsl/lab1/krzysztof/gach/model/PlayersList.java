package pl.polsl.lab1.krzysztof.gach.model;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Krzysztof Gach
 */
public class PlayersList {
    private List<Player> players;
    
    public PlayersList(){
        this.players = new ArrayList<>();
    }
    
    public void addPlayer(Player newPlayer){
        this.players.add(newPlayer);
    }
    
    public void removePlayer(Player player) {
        players.remove(player);
    }
    
    public int getPlayerCount(){
        return players.size();
    }
    
    public List<Player> getPlayers() {
        return players;
    }
}
