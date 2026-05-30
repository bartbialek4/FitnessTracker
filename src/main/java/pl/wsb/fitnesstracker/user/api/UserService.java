package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interfejs serwisu definiujący operacje biznesowe na użytkownikach systemu FitnessTracker.
 */
public interface UserService {

    /** Pobiera wszystkich użytkowników z pełnymi danymi. */
    List<UserDto> getAllUsers();

    /** Pobiera listę użytkowników zawierającą tylko imiona i nazwiska. */
    List<UserSimpleDto> getAllSimpleUsers();

    /** Pobiera szczegóły użytkownika na podstawie jego unikalnego identyfikatora ID. */
    Optional<UserDto> getUserById(Long id);

    /** Wyszukuje użytkowników po fragmencie adresu e-mail. */
    List<UserEmailDto> findUsersByEmailFragment(String email);

    /** Wyszukuje użytkowników urodzonych przed określoną datą. */
    List<UserDto> findUsersBornBefore(LocalDate date);

    /** Tworzy i zapisuje nowego użytkownika w systemie. */
    UserDto createUser(UserDto userDto);

    /** Usuwa użytkownika z systemu na podstawie ID. */
    void deleteUser(Long id);

    /** Aktualizuje opcjonalne (wybrane) atrybuty użytkownika. */
    UserDto updateUser(Long id, UserDto userDto);
}