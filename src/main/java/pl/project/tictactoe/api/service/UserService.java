package pl.project.tictactoe.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.project.tictactoe.api.model.Game;
import pl.project.tictactoe.api.model.User;
import pl.project.tictactoe.api.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User registerUser(String login, String password) {
        // Create user
        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));

        // Save user to database
        return userRepository.save(user);
    }

    public Optional<User> findUserByLogin(String login) {
        return Optional.ofNullable(userRepository.findByLogin(login));
    }

    public User authenticate(String login, String password) {
        User user = userRepository.findByLogin(login);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid login or password");
        }

        return user;
    }
}
