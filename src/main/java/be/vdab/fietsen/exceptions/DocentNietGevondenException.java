package be.vdab.fietsen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DocentNietGevondenException extends RuntimeException {
    public DocentNietGevondenException() {
        super("Docent niet gevonden.");
    }
}
