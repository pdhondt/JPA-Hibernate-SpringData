package be.vdab.fietsen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CampusInDocentNietGevondenException extends RuntimeException {
    public CampusInDocentNietGevondenException() {
        super("Campus niet gevonden.");
    }
}
