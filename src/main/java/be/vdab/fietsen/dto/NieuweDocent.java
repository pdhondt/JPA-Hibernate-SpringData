package be.vdab.fietsen.dto;

import be.vdab.fietsen.domain.Geslacht;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record NieuweDocent(@NotBlank String voornaam,
                           @NotBlank String familienaam, @NotNull @PositiveOrZero BigDecimal wedde,
                           @NotNull @Email String emailAdres, @NotNull Geslacht geslacht) {

}
