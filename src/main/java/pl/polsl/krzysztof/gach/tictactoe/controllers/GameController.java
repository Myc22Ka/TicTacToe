package pl.polsl.krzysztof.gach.tictactoe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {

    @GetMapping("/new-game")
    public String newGame() {
        return "new-game"; // Create a corresponding HTML file in templates
    }

    @GetMapping("/continue-game")
    public String continueGame() {
        return "continue-game"; // Create a corresponding HTML file in templates
    }

    @GetMapping("/options")
    public String options() {
        return "options"; // Create a corresponding HTML file in templates
    }

    @GetMapping("/exit")
    public String exitGame() {
        // You can add logic to close the browser or redirect
        return "exit-game"; // Create a corresponding HTML file in templates
    }

    @GetMapping("/leaderboard")
    public String leaderboard() {
        return "leaderboard"; // Create a corresponding HTML file in templates
    }
}

