package polsl.krzysztof.gach.tictactoe.model;

public enum TicToe {
    X(1), O(2);

    private final Integer value;

    // Constructor
    TicToe(Integer value) {
        this.value = value;
    }

    // Getter for value
    public Integer getValue() {
        return value;
    }
}
