package App.PhiloHub.Controllers;

import App.PhiloHub.Api.ApiResponse;
import App.PhiloHub.Auth.dto.AuthResponse;
import App.PhiloHub.Auth.dto.LoginRequest;
import App.PhiloHub.Auth.dto.RefreshTokenRequest;
import App.PhiloHub.Auth.dto.RegisterRequest;
import App.PhiloHub.Services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse authResponse = authService.register(request);
        return ResponseEntity.ok(new ApiResponse<>(
                true, "Registration successful", authResponse, null, LocalDate.now()
        ));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse authResponse = authService.login(request);
        return ResponseEntity.ok(new ApiResponse<>(
                true, "Login successful", authResponse, null, LocalDate.now()
        ));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<AuthResponse>> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        AuthResponse authResponse = authService.refreshToken(request);
        return ResponseEntity.ok(new ApiResponse<>(
                true, "Token refreshed successfully", authResponse, null, LocalDate.now()
        ));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@Valid @RequestBody RefreshTokenRequest request) {
        authService.logout(request.getRefreshToken());
        return ResponseEntity.ok(new ApiResponse<>(
                true, "Logout successful", null, null, LocalDate.now()
        ));
    }
}
