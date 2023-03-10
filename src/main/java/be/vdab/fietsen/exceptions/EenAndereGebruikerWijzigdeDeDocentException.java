package be.vdab.fietsen.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EenAndereGebruikerWijzigdeDeDocentException extends RuntimeException {
    public EenAndereGebruikerWijzigdeDeDocentException() {
        super("Een andere gebruiker wijzigde de docent.");
    }
}
