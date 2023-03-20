package be.vdab.fietsen.domain;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class DocentTest {
    @Test
    void jeKanMeerdereDocentenToevoegenAanEenCampus() {
        var adres = new Adres("test", "test", 1000, "test");
        var campus = new Campus("test", adres);
        var docent = new Docent("test1", "test1", BigDecimal.TEN,
                "test1.test1@example.org", Geslacht.MAN, campus);
        campus.voegDocentToe(docent);
        var andereDocent = new Docent("test2", "test2", BigDecimal.TEN,
                "test2.test2@example.org", Geslacht.MAN, campus);
        campus.voegDocentToe(andereDocent);
    }
    @Test
    void nadatJeEenDocentOpslaatBehoortHijNogTotZijnCampus() {
        var adres = new Adres("test", "test", 1000, "test");
        var campus = new Campus("test", adres);
        var docent = new Docent("test1", "test1", BigDecimal.TEN,
                "test1.test1@example.org", Geslacht.MAN, campus);
        campus.voegDocentToe(docent);
        ReflectionTestUtils.setField(docent, "id", 1);
        assertThat(campus.getDocenten().contains(docent)).isTrue();
    }
}
