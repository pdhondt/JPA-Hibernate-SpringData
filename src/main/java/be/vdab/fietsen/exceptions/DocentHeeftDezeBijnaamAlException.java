package be.vdab.fietsen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DocentHeeftDezeBijnaamAlException extends RuntimeException {
    public DocentHeeftDezeBijnaamAlException() {
        super("Docent heeft deze bijnaam al.");
    }
}
