package be.vdab.fietsen.dto;

import be.vdab.fietsen.domain.Taak;

public record TaakBeknopt(long id, String naam) {
    public TaakBeknopt(Taak taak) {
        this(taak.getId(), taak.getNaam());
    }
}
