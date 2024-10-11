package pl.polsl.lab1.krzysztof.gach.model;

/**
 *
 * @author Krzysztof Gach
 */
public class Cell {
    private String value;

    public Cell() {
        this.value = "□";  // Default empty cell
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
