package be.vdab.fietsen.dto;

import be.vdab.fietsen.domain.Geslacht;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record NieuweDocent(@NotBlank String voornaam,
                           @NotBlank String familienaam, @NotNull @PositiveOrZero BigDecimal wedde,
                           @NotNull @Email String emailAdres, @NotNull Geslacht geslacht,
                           @JsonProperty(required = true) @Positive long campusId) {
}
