package be.vdab.fietsen.controllers;

import be.vdab.fietsen.domain.Docent;
import be.vdab.fietsen.dto.AantalDocentenPerWedde;
import be.vdab.fietsen.dto.EnkelNaam;
import be.vdab.fietsen.dto.NieuweDocent;
import be.vdab.fietsen.exceptions.DocentNietGevondenException;
import be.vdab.fietsen.exceptions.EenAndereGebruikerWijzigdeDeDocentException;
import be.vdab.fietsen.services.DocentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("docenten")
class DocentController {
    private final DocentService docentService;
    private record WeddeVerhoging(@NotNull @Positive BigDecimal bedrag){}

    DocentController(DocentService docentService) {
        this.docentService = docentService;
    }

    @GetMapping("aantal")
    long findAantal() {
        return docentService.findAantal();
    }

    @GetMapping
    List<Docent> findAll() {
        return docentService.findAll();
    }

    @GetMapping("{id}")
    Docent findById(@PathVariable long id) {
        return docentService.findById(id)
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
    List<Docent> findByWedde(BigDecimal wedde) {
        return docentService.findByWedde(wedde);
    }
    @GetMapping(params = "emailAdres")
    Docent findByEmailAdres(String emailAdres) {
        return docentService.findByEmailAdres(emailAdres)
                .orElseThrow(DocentNietGevondenException::new);
    }
    @GetMapping(value = "aantal", params = "wedde")
    int findAantalMetWedde(BigDecimal wedde) {
        return docentService.findAantalMetWedde(wedde);
    }
    @GetMapping("metGrootsteWedde")
    List<Docent> findMetGrootsteWedde() {
        return docentService.findMetGrootsteWedde();
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
    void algemeneOpslag(@RequestBody @Positive BigDecimal bedrag) {
        docentService.algemeneOpslag(bedrag);
    }
}
