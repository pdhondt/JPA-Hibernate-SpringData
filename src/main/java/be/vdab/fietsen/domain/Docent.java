package be.vdab.fietsen.domain;

import be.vdab.fietsen.exceptions.DocentHeeftDezeBijnaamAlException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "docenten")
public class Docent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String voornaam;
    private String familienaam;
    private BigDecimal wedde;

    private String emailAdres;
    @Enumerated(EnumType.STRING)
    private Geslacht geslacht;
    @Version
    private long versie;
    @ElementCollection
    @CollectionTable(name = "bijnamen",
    joinColumns = @JoinColumn(name = "docentId"))
    @Column(name = "bijnaam")
    private Set<String> bijnamen;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "campusId")
    private Campus campus;

    public Docent(String voornaam, String familienaam, BigDecimal wedde,
                  String emailAdres, Geslacht geslacht, Campus campus) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.wedde = wedde;
        this.emailAdres = emailAdres;
        this.geslacht = geslacht;
        this.bijnamen = new LinkedHashSet<>();
        this.campus = campus;
    }

    protected Docent() {

    }

    public long getId() {
        return id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public BigDecimal getWedde() {
        return wedde;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }

    public long getVersie() {
        return versie;
    }

    public Campus getCampus() {
        return campus;
    }

    public void opslag(BigDecimal bedrag) {
        wedde = wedde.add(bedrag);
    }
    public void voegBijnaamToe(String bijnaam) {
        if (!bijnamen.add(bijnaam)) {
            throw new DocentHeeftDezeBijnaamAlException();
        }
    }
    public void verwijderBijnaam(String bijnaam) {
        bijnamen.remove(bijnaam);
    }

    public Set<String> getBijnamen() {
        return Collections.unmodifiableSet(bijnamen);
    }
}
