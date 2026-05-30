package pl.wsb.fitnesstracker.user.api;


/**
 * DTO zawierające jedynie identyfikator oraz adres e-mail.
 * Wykorzystywane przy wyszukiwaniu po fragmencie e-maila.
 */
public record UserEmailDto(Long id, String email) {}