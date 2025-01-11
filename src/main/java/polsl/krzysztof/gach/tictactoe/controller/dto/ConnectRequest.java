package polsl.krzysztof.gach.tictactoe.controller.dto;

import polsl.krzysztof.gach.tictactoe.model.Player;

public class ConnectRequest {
    private Player player;
    private String gameId;

    public Player getPlayer() {
        return player;
    }

    public String getGameId(){
        return gameId;
    }
}
