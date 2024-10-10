package pl.polsl.lab1.krzysztof.gach.model;

/**
 *
 * @author Krzysztof Gach
 */
public class Cell {
    private int id;
    private int value;
    
    public Cell(int id, int value){
        this.id = id;
        this.value = value;
    }
    
    public Cell(int id){
        this.id = id;
        this.value = -1;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setValue(int newValue){
        this.value = newValue;
    }    
    
    public int getValue(){
        return this.value;
    }
}
