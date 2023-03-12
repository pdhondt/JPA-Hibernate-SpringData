package be.vdab.fietsen.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "campussen")
public class Campus {
    @Id
    private long id;
    private String name;
    @Embedded
    private Adres adres;

    public Campus(long id, String name, Adres adres) {
        this.id = id;
        this.name = name;
        this.adres = adres;
    }
    protected Campus() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Adres getAdres() {
        return adres;
    }
}
