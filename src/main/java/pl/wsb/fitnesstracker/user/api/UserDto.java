package pl.wsb.fitnesstracker.user.api;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

/**
 * DTO zawierające pełne szczegóły użytkownika.
 * Wykorzystywane przy pobieraniu szczegółów, tworzeniu i aktualizacji.
 */
public record UserDto(Long id, String firstName, String lastName, LocalDate birthdate, String email) {}

