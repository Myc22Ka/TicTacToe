package pl.polsl.lab1.krzysztof.gach.controller;

/**
 *
 * @author kris
 */
public class Game implements GameInstance{
    private int score;
   
    @Override
    public void startGame() {
       System.out.print("Game start");
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }
    
}
