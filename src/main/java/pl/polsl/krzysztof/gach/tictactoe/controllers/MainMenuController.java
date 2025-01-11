package pl.polsl.krzysztof.gach.tictactoe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainMenuController {

    @GetMapping("/")
    public String mainMenu(Model model) {
        return "main-menu";
    }
}
