package polsl.krzysztof.gach.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class Coordinates {
    private Integer x;
    private Integer y;
}
