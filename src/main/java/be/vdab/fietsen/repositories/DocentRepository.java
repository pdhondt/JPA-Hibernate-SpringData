package be.vdab.fietsen.repositories;

import be.vdab.fietsen.domain.Docent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocentRepository extends JpaRepository<Docent, Long> {
}
