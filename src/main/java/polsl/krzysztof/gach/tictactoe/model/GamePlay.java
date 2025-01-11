package polsl.krzysztof.gach.tictactoe.model;

import lombok.Data;

@Data
public class GamePlay {

    private TicToe type;
    private Coordinates coordinates;
    private String gameId;

}
