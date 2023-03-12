package be.vdab.fietsen.services;

import be.vdab.fietsen.domain.Campus;
import be.vdab.fietsen.repositories.CampusRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CampusService {
    private final CampusRepository campusRepository;

    public CampusService(CampusRepository campusRepository) {
        this.campusRepository = campusRepository;
    }
    public List<Campus> findWestVlaamse() {
        return campusRepository.findByAdres_PostcodeBetweenOrderByAdres_Gemeente(8000, 8999);
    }
}
