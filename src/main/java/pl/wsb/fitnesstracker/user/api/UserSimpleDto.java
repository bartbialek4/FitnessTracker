package pl.wsb.fitnesstracker.user.api;

/**
 * DTO zawierające uproszczone informacje (tylko imię i nazwisko).
 * Wykorzystywane przez endpoint '/v1/users/simple'.
 */
public record UserSimpleDto(String firstName, String lastName) {}