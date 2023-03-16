package be.vdab.fietsen.dto;

import be.vdab.fietsen.domain.Adres;
import be.vdab.fietsen.domain.Campus;

public record CampusBeknopt(long id, String naam, Adres adres) {
    public CampusBeknopt(Campus campus) {
        this(campus.getId(), campus.getNaam(), campus.getAdres());
    }
}
