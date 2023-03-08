package be.vdab.fietsen.services;

import be.vdab.fietsen.domain.Docent;
import be.vdab.fietsen.repositories.DocentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DocentService {
    private final DocentRepository docentRepository;

    public DocentService(DocentRepository docentRepository) {
        this.docentRepository = docentRepository;
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
}
