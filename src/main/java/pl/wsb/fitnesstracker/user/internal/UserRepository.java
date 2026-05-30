package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repozytorium pakietowe odpowiedzialne za bezpośrednie operacje na bazie danych dla encji {@link User}.
 */
interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Filtruje użytkowników po fragmencie adresu e-mail przy użyciu strumieni.
     * Ignoruje wielkość liter (case-insensitive).
     *
     * @param emailFragment fragment adresu e-mail do wyszukania
     * @return lista użytkowników pasujących do kryterium
     */
    default List<User> findByEmailContainingIgnoreCaseStream(String emailFragment) {
        return findAll().stream()
                .filter(user -> user.getEmail() != null &&
                        user.getEmail().toLowerCase().contains(emailFragment.toLowerCase()))
                .toList();
    }

    /**
     * Filtruje użytkowników urodzonych przed wskazaną datą przy użyciu strumieni.
     * Odpowiada to wyszukiwaniu osób starszych niż zdefiniowany punkt w czasie.
     *
     * @param date graniczna data urodzenia
     * @return lista użytkowników urodzonych przed podaną datą
     */
    default List<User> findByBirthdateBeforeStream(LocalDate date) {
        return findAll().stream()
                .filter(user -> user.getBirthdate() != null && user.getBirthdate().isBefore(date))
                .toList();
    }
}