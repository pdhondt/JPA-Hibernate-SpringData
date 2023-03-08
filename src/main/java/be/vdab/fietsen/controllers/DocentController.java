package be.vdab.fietsen.controllers;

import be.vdab.fietsen.domain.Docent;
import be.vdab.fietsen.exceptions.DocentNietGevondenException;
import be.vdab.fietsen.services.DocentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("docenten")
class DocentController {
    private final DocentService docentService;

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
}
