package polsl.krzysztof.gach.tictactoe.model;

import lombok.Data;

import java.util.List;

@Data
public class Game {

    private String gameId;
    private GameStatus status;
    List<Player> playersList;
    private Board board;
    private TicToe winner;
}
