package be.vdab.fietsen.services;

import be.vdab.fietsen.domain.Docent;
import be.vdab.fietsen.dto.AantalDocentenPerWedde;
import be.vdab.fietsen.dto.EnkelNaam;
import be.vdab.fietsen.dto.NieuweDocent;
import be.vdab.fietsen.exceptions.CampusInDocentNietGevondenException;
import be.vdab.fietsen.exceptions.DocentBestaatAlException;
import be.vdab.fietsen.exceptions.DocentNietGevondenException;
import be.vdab.fietsen.repositories.CampusRepository;
import be.vdab.fietsen.repositories.DocentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DocentService {
    private final DocentRepository docentRepository;
    private final CampusRepository campusRepository;

    public DocentService(DocentRepository docentRepository, CampusRepository campusRepository) {
        this.docentRepository = docentRepository;
        this.campusRepository = campusRepository;
    }
    public long findAantal() {
        return docentRepository.count();
    }
    public List<Docent> findAll() {
        return docentRepository.findAll(Sort.by("familienaam"));
    }
    public Optional<Docent> findById(long id) {
        return docentRepository.findById(id);
    }
    public boolean existsById(long id) {
        return docentRepository.existsById(id);
    }
    @Transactional
    public long create(NieuweDocent nieuweDocent) {
        try {
            var campus = campusRepository.findById(nieuweDocent.campusId())
                    .orElseThrow(CampusInDocentNietGevondenException::new);
            var docent = new Docent(nieuweDocent.voornaam(), nieuweDocent.familienaam(),
                    nieuweDocent.wedde(), nieuweDocent.emailAdres(), nieuweDocent.geslacht(),
                    campus);
            campus.voegDocentToe(docent);
            docentRepository.save(docent);
            return docent.getId();
        } catch (DataIntegrityViolationException ex) {
            throw new DocentBestaatAlException();
        }
    }
    @Transactional
    public void delete(long id) {
        docentRepository.deleteById(id);
    }
    @Transactional
    public void opslag(long id, BigDecimal bedrag) {
        docentRepository.findAndLockById(id)
                .orElseThrow(DocentNietGevondenException::new)
                .opslag(bedrag);
    }
    public List<Docent> findByWedde(BigDecimal wedde) {
        return docentRepository.findByWeddeOrderByFamilienaamAscVoornaamAsc(wedde);
    }
    public Optional<Docent> findByEmailAdres(String emailAdres) {
        return docentRepository.findByEmailAdres(emailAdres);
    }
    public int findAantalMetWedde(BigDecimal wedde) {
        return docentRepository.countByWedde(wedde);
    }
    public List<Docent> findMetGrootsteWedde() {
        return docentRepository.findMetGrootsteWedde();
    }
    public BigDecimal findGrootsteWedde() {
        return docentRepository.findGrootsteWedde();
    }
    public List<EnkelNaam> findNamen() {
        return docentRepository.findNamen();
    }
    public List<AantalDocentenPerWedde> findAantalDocentenPerWedde() {
        return docentRepository.findAantalDocentenPerWedde();
    }
    @Transactional
    public void algemeneOpslag(BigDecimal bedrag) {
        docentRepository.algemeneOpslag(bedrag);
    }
    @Transactional
    public void voegBijnaamToe(long id, String bijnaam) {
        docentRepository.findById(id)
                .orElseThrow(DocentNietGevondenException::new)
                .voegBijnaamToe(bijnaam);
    }
    @Transactional
    public void verwijderBijnaam(long id, String bijnaam) {
        docentRepository.findById(id)
                .orElseThrow(DocentNietGevondenException::new)
                .verwijderBijnaam(bijnaam);
    }
    public List<Docent> findAllMetBijnamen() {
        return docentRepository.findAllMetBijnamen();
    }
    public List<Docent> findAllMetCampussen() {
        return docentRepository.findAllMetCampussen();
    }
}
