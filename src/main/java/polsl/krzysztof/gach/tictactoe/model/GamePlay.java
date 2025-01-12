package polsl.krzysztof.gach.tictactoe.model;

public class GamePlay {

    private TicToe type;
    private Coordinates coordinates;
    private String gameId;

    // No-args constructor
    public GamePlay() {
    }

    // All-args constructor
    public GamePlay(TicToe type, Coordinates coordinates, String gameId) {
        this.type = type;
        this.coordinates = coordinates;
        this.gameId = gameId;
    }

    // Getter for type
    public TicToe getType() {
        return type;
    }

    // Setter for type
    public void setType(TicToe type) {
        this.type = type;
    }

    // Getter for coordinates
    public Coordinates getCoordinates() {
        return coordinates;
    }

    // Setter for coordinates
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    // Getter for gameId
    public String getGameId() {
        return gameId;
    }

    // Setter for gameId
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

}
