package be.vdab.fietsen.controllers;

import be.vdab.fietsen.dto.CampusBeknopt;
import be.vdab.fietsen.dto.DocentBeknopt;
import be.vdab.fietsen.exceptions.CampusNietGevondenException;
import be.vdab.fietsen.services.CampusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping("campussen")
class CampusController {
    private final CampusService campusService;

    CampusController(CampusService campusService) {
        this.campusService = campusService;
    }
    @GetMapping("westvlaams")
    Stream<CampusBeknopt> findWestVlaamse() {
        return campusService.findWestVlaamse()
                .stream()
                .map(CampusBeknopt::new);
    }
    @GetMapping("{id}/docenten")
    Stream<DocentBeknopt> findDocentenVan(@PathVariable long id) {
        return campusService.findById(id)
                .orElseThrow(CampusNietGevondenException::new)
                .getDocenten()
                .stream()
                .map(docent -> new DocentBeknopt(docent));
    }
}
