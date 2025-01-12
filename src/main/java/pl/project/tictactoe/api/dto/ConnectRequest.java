package pl.project.tictactoe.api.dto;

import lombok.Data;
import pl.project.tictactoe.api.model.User;

@Data
public class ConnectRequest {
    private User player;
    private String gameId;
}
