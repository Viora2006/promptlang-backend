package app.promptlang.service;

import app.promptlang.dto.AuthResponse;
import app.promptlang.model.User;
import app.promptlang.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtService = jwtService;
    }

    public void register(String username, String password) {

        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }

        String hashedPassword = passwordEncoder.encode(password);

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(hashedPassword);

        userRepository.save(user);
    }

    public AuthResponse login(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean passwordMatches = passwordEncoder.matches(
                password,
                user.getPasswordHash()
        );

        if (!passwordMatches) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtService.generateToken(
        user.getUsername());

        return new AuthResponse("Login successful", token);
    }
}