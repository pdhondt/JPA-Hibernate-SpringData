package be.vdab.fietsen.controllers;

import be.vdab.fietsen.domain.Docent;
import be.vdab.fietsen.dto.*;
import be.vdab.fietsen.exceptions.DocentNietGevondenException;
import be.vdab.fietsen.exceptions.EenAndereGebruikerWijzigdeDeDocentException;
import be.vdab.fietsen.services.DocentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@RestController
@RequestMapping("docenten")
class DocentController {
    private final DocentService docentService;
    private record WeddeVerhoging(@NotNull @Positive BigDecimal bedrag){}
    private record Opslag(@NotNull @Positive BigDecimal bedrag) {}
    private record NieuweBijnaam(@NotBlank String bijnaam) {}

    private record DocentBeknoptMetBijnamen(long id, String voornaam,
                                            String familienaam, Set<String> bijnamen) {
        DocentBeknoptMetBijnamen(Docent docent) {
            this(docent.getId(), docent.getVoornaam(), docent.getFamilienaam(),
                    docent.getBijnamen());
        }
    }
    private record DocentBeknoptMetCampus(long id, String voornaam, String familienaam,
                                          long campusId, String campusNaam) {
        DocentBeknoptMetCampus(Docent docent) {
            this(docent.getId(), docent.getVoornaam(), docent.getFamilienaam(),
                    docent.getCampus().getId(), docent.getCampus().getNaam());
        }
    }

    DocentController(DocentService docentService) {
        this.docentService = docentService;
    }

    @GetMapping("aantal")
    long findAantal() {
        return docentService.findAantal();
    }

    @GetMapping
    Stream<DocentBeknopt> findAll() {
        return docentService.findAll()
                .stream()
                .map(DocentBeknopt::new);
    }

    @GetMapping("{id}")
    DocentBeknoptMetBijnamen findById(@PathVariable long id) {
        return docentService.findById(id)
                .map(docent -> new DocentBeknoptMetBijnamen(docent))
                .orElseThrow(DocentNietGevondenException::new);
    }

    @GetMapping("{id}/bestaat")
    boolean bestaatById(@PathVariable long id) {
        return docentService.existsById(id);
    }

    @PostMapping
    long create(@RequestBody @Valid NieuweDocent nieuweDocent) {
        return docentService.create(nieuweDocent);
    }

    @DeleteMapping("{id}")
    void delete(@PathVariable long id) {
        try {
            docentService.delete(id);
        } catch (EmptyResultDataAccessException ignored) {
        }
    }
    @PostMapping("{id}/weddeverhogingen")
    void opslag(@PathVariable long id, @RequestBody @Valid WeddeVerhoging verhoging) {
        try{
            docentService.opslag(id, verhoging.bedrag());
        } catch (ObjectOptimisticLockingFailureException ex) {
            throw new EenAndereGebruikerWijzigdeDeDocentException();
        }
    }
    @GetMapping(params = "wedde")
    Stream<DocentBeknoptMetCampus> findByWedde(BigDecimal wedde) {
        return docentService.findByWedde(wedde)
                .stream()
                .map(DocentBeknoptMetCampus::new);
    }
    @GetMapping(params = "emailAdres")
    DocentBeknoptMetBijnamen findByEmailAdres(String emailAdres) {
        return docentService.findByEmailAdres(emailAdres)
                .map(docent -> new DocentBeknoptMetBijnamen(docent))
                .orElseThrow(DocentNietGevondenException::new);
    }
    @GetMapping(value = "aantal", params = "wedde")
    int findAantalMetWedde(BigDecimal wedde) {
        return docentService.findAantalMetWedde(wedde);
    }
    @GetMapping("metGrootsteWedde")
    Stream<DocentBeknopt> findMetGrootsteWedde() {
        return docentService.findMetGrootsteWedde()
                .stream()
                .map(DocentBeknopt::new);
    }
    @GetMapping("weddes/grootste")
    BigDecimal findGrootsteWedde() {
        return docentService.findGrootsteWedde();
    }
    @GetMapping("namen")
    List<EnkelNaam> findNamen() {
        return docentService.findNamen();
    }
    @GetMapping("aantalPerWedde")
    List<AantalDocentenPerWedde> findAantalDocentenPerWedde() {
        return docentService.findAantalDocentenPerWedde();
    }
    @PostMapping("weddeverhogingen")
    void algemeneOpslag(@RequestBody @Valid Opslag opslag) {
        docentService.algemeneOpslag(opslag.bedrag());
    }
    @PostMapping("{id}/bijnamen")
    void voegBijnaamToe(@PathVariable long id,
                        @RequestBody @Valid NieuweBijnaam nieuweBijnaam) {
        docentService.voegBijnaamToe(id, nieuweBijnaam.bijnaam());
    }
    @DeleteMapping("{id}/bijnamen/{bijnaam}")
    void verwijderBijnaam(@PathVariable long id,
                          @PathVariable String bijnaam) {
        docentService.verwijderBijnaam(id, bijnaam);
    }
    @GetMapping("{id}/emailAdres")
    String findEmailAdresById(@PathVariable long id) {
        return docentService.findById(id)
                .orElseThrow(DocentNietGevondenException::new)
                .getEmailAdres();
    }
    @GetMapping("metBijnamen")
    Stream<DocentBeknoptMetBijnamen> findAllMetBijnamen() {
        return docentService.findAllMetBijnamen()
                .stream()
                .map(DocentBeknoptMetBijnamen::new);
    }
    @GetMapping("{id}/campus")
    CampusBeknopt findCampusVan(@PathVariable long id) {
        return docentService.findById(id)
                .map(docent -> new CampusBeknopt(docent.getCampus()))
                .orElseThrow(DocentNietGevondenException::new);
    }
    @GetMapping("metCampussen")
    Stream<DocentBeknoptMetCampus> findAllMetCampussen() {
        return docentService.findAllMetCampussen()
                .stream()
                .map(docent -> new DocentBeknoptMetCampus(docent));
    }
    @GetMapping("{id}/taken")
    Stream<TaakBeknopt> findTaken(@PathVariable long id) {
        return docentService.findById(id)
                .orElseThrow(DocentNietGevondenException::new)
                .getTaken()
                .stream()
                .map(taak -> new TaakBeknopt(taak));
    }
}
