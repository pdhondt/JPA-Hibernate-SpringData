package be.vdab.fietsen.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.time.LocalDate;

@Embeddable
@Access(AccessType.FIELD)
public class Huurprijs {
    private LocalDate vanaf;
    private BigDecimal prijs;

    public LocalDate getVanaf() {
        return vanaf;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }
    @Override
    public boolean equals(Object object) {
        return object instanceof Huurprijs huurprijs &&
                vanaf.equals(huurprijs.vanaf);
    }
    @Override
    public int hashCode() {
        return vanaf.hashCode();
    }
}
