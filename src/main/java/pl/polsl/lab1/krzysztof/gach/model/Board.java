package pl.polsl.lab1.krzysztof.gach.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Krzysztof Gach
 */
public class Board {
    private List<Cell> board;
    
    public Board(){
        this.board = new ArrayList<>();
    }
    
    public int getSize(){
        return board.size();
    }
    
    public void setNewSize(int newSize){
        int currentSize = this.getSize();
        
        if(currentSize == newSize) return;
        
        if (newSize > currentSize) {
            for (int i = currentSize; i < newSize; i++) board.add(new Cell(i));
        } 
        else if (newSize < currentSize) {
            for (int i = currentSize - 1; i >= newSize; i--) board.remove(i);
        }
    }
    
    public List<Cell> getBoard(){
        return board;
    }
    
    public void updateBoard(int position, Cell newCell){
        
    }
}
