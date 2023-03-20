package be.vdab.fietsen.dto;

import be.vdab.fietsen.domain.Docent;

public record DocentBeknopt(long id, String voornaam, String familienaam) {
    public DocentBeknopt(Docent docent) {
        this(docent.getId(), docent.getVoornaam(), docent.getFamilienaam());
    }
}
