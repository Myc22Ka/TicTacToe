package pl.project.tictactoe.api.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String password;
}
