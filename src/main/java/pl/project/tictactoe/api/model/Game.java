package pl.project.tictactoe.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Use a numeric ID for the database record

    @Column(name = "game_id", unique = true, nullable = false)
    private String gameId;

    @ManyToOne
    @JoinColumn(name = "player1")
    private User player1;

    @ManyToOne
    @JoinColumn(name = "player2")
    private User player2;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    @Convert(converter = BoardConverter.class)
    private int[][] board;

    @Enumerated(EnumType.STRING)
    private TicToe winner;

    @PrePersist
    public void prePersist() {
        if (gameId == null) {
            gameId = UUID.randomUUID().toString(); // Manually generate the UUID before persisting
        }
    }
}