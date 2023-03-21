package be.vdab.fietsen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DocentHeeftDezeTaakAlException extends RuntimeException {
    public DocentHeeftDezeTaakAlException() {
        super("Docent heeft deze taak al");
    }
}
