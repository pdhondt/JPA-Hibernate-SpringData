package be.vdab.fietsen.repositories;

import be.vdab.fietsen.domain.Docent;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DocentRepository extends JpaRepository<Docent, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select d from Docent d where d.id = :id")
    Optional<Docent> findAndLockById(long id);

    List<Docent> findByWeddeOrderByFamilienaamAscVoornaamAsc(BigDecimal wedde);

    Optional<Docent> findByEmailAdres(String emailAdres);

    int countByWedde(BigDecimal wedde);
}
