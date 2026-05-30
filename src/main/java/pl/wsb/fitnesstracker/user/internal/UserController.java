package pl.wsb.fitnesstracker.user.internal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserService;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

import java.time.LocalDate;
import java.util.List;

/**
        * Kontroler sieciowy REST wystawiający operacje CRUD oraz zaawansowane wyszukiwanie dla zasobu User.
        */
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Pobiera pełną listę wszystkich użytkowników w systemie.
     * HTTP: GET /v1/users
     */
    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Pobiera uproszczoną listę użytkowników (tylko imię i nazwisko).
     * HTTP: GET /v1/users/simple
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userService.getAllSimpleUsers();
    }

    /**
     * Pobiera szczegółowe dane konkretnego użytkownika na podstawie identyfikatora ID.
     * HTTP: GET /v1/users/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Wyszukuje użytkowników na podstawie fragmentu adresu e-mail.
     * HTTP: GET /v1/users/email?email=...
     */
    @GetMapping("/email")
    public List<UserEmailDto> getUserByEmail(@RequestParam String email) {
        return userService.findUsersByEmailFragment(email);
    }

    /**
     * Wyszukuje użytkowników urodzonych przed wskazaną datą (starszych niż podana data).
     * HTTP: GET /v1/users/older/{time}
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate time) {
        return userService.findUsersBornBefore(time);
    }

    /**
     * Rejestruje nowego użytkownika w systemie.
     * HTTP: POST /v1/users
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    /**
     * Usuwa użytkownika o podanym identyfikatorze z systemu.
     * HTTP: DELETE /v1/users/{userId}
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }

    /**
     * Aktualizuje dowolnie wybrane atrybuty istniejącego użytkownika.
     * HTTP: PUT /v1/users/{userId}
     */
    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestBody UserDto userDto) {
        return userService.updateUser(userId, userDto);
    }
}