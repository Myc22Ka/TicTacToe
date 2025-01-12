package polsl.krzysztof.gach.tictactoe.model;

public class Cell {
    int value;

    public Cell() {
        this.value = 0;
    }

    // All-args constructor
    public Cell(int value) {
        this.value = value;
    }

    // Getter for value
    public int getValue() {
        return value;
    }

    // Setter for value
    public void setValue(int value) {
        this.value = value;
    }
}
