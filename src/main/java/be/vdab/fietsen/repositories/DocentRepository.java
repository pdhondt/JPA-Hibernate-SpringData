package be.vdab.fietsen.repositories;

import be.vdab.fietsen.domain.Docent;
import be.vdab.fietsen.dto.AantalDocentenPerWedde;
import be.vdab.fietsen.dto.EnkelNaam;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("""
            select d
            from Docent d
            where d.wedde = (select max(dd.wedde) from Docent dd)
            """)
    List<Docent> findMetGrootsteWedde();

    @Query("""
            select max(d.wedde)
            from Docent d
            """)
    BigDecimal findGrootsteWedde();

    @Query("""
            select d.voornaam as voornaam, d.familienaam as familienaam
            from Docent d
            order by d.voornaam, d.familienaam
            """)
    List<EnkelNaam> findNamen();

    @Query("""
            select d.wedde as wedde, count(d) as aantal
            from Docent d
            group by d.wedde
            """)
    List<AantalDocentenPerWedde> findAantalDocentenPerWedde();

    @Modifying
    @Query("""
            update Docent d
            set d.wedde = d.wedde + :bedrag
            """)
    void algemeneOpslag(BigDecimal bedrag);
}
