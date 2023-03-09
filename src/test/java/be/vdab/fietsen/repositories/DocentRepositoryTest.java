package be.vdab.fietsen.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class DocentRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String DOCENTEN = "docenten";
    private final DocentRepository docentRepository;

    DocentRepositoryTest(DocentRepository docentRepository) {
        this.docentRepository = docentRepository;
    }
    @Test
    void count() {
        assertThat(docentRepository.count())
                .isEqualTo(countRowsInTable(DOCENTEN));
    }
}
