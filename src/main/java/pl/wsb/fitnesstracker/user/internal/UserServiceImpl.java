package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import pl.wsb.fitnesstracker.user.api.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Implementacja serwisu użytkowników ukryta w pakiecie, zarządzająca transakcjami i mapowaniem encji na DTO.
 */
@Service
@Transactional
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserSimpleDto> getAllSimpleUsers() {
        return userRepository.findAll().stream()
                .map(u -> new UserSimpleDto(u.getFirstName(), u.getLastName()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id).map(this::mapToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEmailDto> findUsersByEmailFragment(String email) {
        return userRepository.findByEmailContainingIgnoreCaseStream(email).stream()
                .map(u -> new UserEmailDto(u.getId(), u.getEmail()))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findUsersBornBefore(LocalDate date) {
        return userRepository.findByBirthdateBeforeStream(date).stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public UserDto createUser(UserDto dto) {
        User user = new User(dto.firstName(), dto.lastName(), dto.birthdate(), dto.email());
        User savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(Long id, UserDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));

        if (dto.firstName() != null) setPrivateField(user, "firstName", dto.firstName());
        if (dto.lastName() != null) setPrivateField(user, "lastName", dto.lastName());
        if (dto.birthdate() != null) setPrivateField(user, "birthdate", dto.birthdate());
        if (dto.email() != null) setPrivateField(user, "email", dto.email());

        return mapToDto(userRepository.save(user));
    }

    private void setPrivateField(Object target, String fieldName, Object value) {
        Field field = ReflectionUtils.findField(target.getClass(), fieldName);
        if (field != null) {
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field, target, value);
        }
    }

    private UserDto mapToDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthdate(), user.getEmail());
    }
}