package polsl.krzysztof.gach.tictactoe.model;

public class Coordinates {
    private Integer x;
    private Integer y;

    // No-args constructor
    public Coordinates() {
    }

    // All-args constructor
    public Coordinates(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    // Getter for x
    public Integer getX() {
        return x;
    }

    // Setter for x
    public void setX(Integer x) {
        this.x = x;
    }

    // Getter for y
    public Integer getY() {
        return y;
    }

    // Setter for y
    public void setY(Integer y) {
        this.y = y;
    }
}
