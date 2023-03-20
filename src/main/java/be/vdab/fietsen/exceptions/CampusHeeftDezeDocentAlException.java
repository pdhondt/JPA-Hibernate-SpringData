package be.vdab.fietsen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CampusHeeftDezeDocentAlException extends RuntimeException {
    public CampusHeeftDezeDocentAlException() {
        super("Campus heeft deze docent al.");
    }
}
