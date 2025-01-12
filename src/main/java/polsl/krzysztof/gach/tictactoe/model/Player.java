package polsl.krzysztof.gach.tictactoe.model;

public class Player {
    private String login;

    // No-args constructor
    public Player() {
    }

    // All-args constructor
    public Player(String login) {
        this.login = login;
    }

    // Getter for login
    public String getLogin() {
        return login;
    }

    // Setter for login
    public void setLogin(String login) {
        this.login = login;
    }
}
