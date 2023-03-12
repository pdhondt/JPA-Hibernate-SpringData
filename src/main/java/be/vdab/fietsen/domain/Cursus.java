package be.vdab.fietsen.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)
//@Table(name = "cursussen")
//@DiscriminatorColumn(name = "soort")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Cursus {
    @Id
    @Column(columnDefinition = "binary(16)")
    private UUID id;
//    private long id;
    private String naam;

    public UUID getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
