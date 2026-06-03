package app.promptlang.controller;
import app.promptlang.service.AuthService;
import app.promptlang.dto.RegisterRequest;
import app.promptlang.dto.LoginRequest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import app.promptlang.dto.AuthResponse;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        authService.register(
            request.getUsername(),
            request.getPassword()
        );

        return new AuthResponse("User Created");
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(
            request.getUsername(),
            request.getPassword()
        );
    }
}