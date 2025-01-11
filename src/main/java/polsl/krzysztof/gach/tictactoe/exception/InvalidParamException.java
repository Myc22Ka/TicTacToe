package polsl.krzysztof.gach.tictactoe.exception;

import lombok.Getter;

@Getter
public class InvalidParamException extends Exception{

    public InvalidParamException(String message){
        super(message);
    }

}
