package be.vdab.fietsen.controllers;

import be.vdab.fietsen.domain.Campus;
import be.vdab.fietsen.services.CampusService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("campussen")
class CampusController {
    private final CampusService campusService;

    CampusController(CampusService campusService) {
        this.campusService = campusService;
    }
    @GetMapping("westvlaams")
    List<Campus> findWestVlaamse() {
        return campusService.findWestVlaamse();
    }
}
