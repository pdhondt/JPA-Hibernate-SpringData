package be.vdab.fietsen.controllers;

import be.vdab.fietsen.domain.Cursus;
import be.vdab.fietsen.domain.GroepsCursus;
import be.vdab.fietsen.domain.IndividueleCursus;
import be.vdab.fietsen.services.CursusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cursussen")
class CursusController {
    private final CursusService cursusService;

    CursusController(CursusService cursusService) {
        this.cursusService = cursusService;
    }
    @GetMapping
    List<Cursus> findAll() {
        return cursusService.findAll();
    }
    @GetMapping("groep")
    List<GroepsCursus> findGroepsCursussen() {
        return cursusService.findGroepsCursussen();
    }
    @GetMapping("individueel")
    List<IndividueleCursus> findIndividueleCursussen() {
        return cursusService.findIndividueleCursussen();
    }
}
