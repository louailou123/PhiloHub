package App.PhiloHub.Services;

import App.PhiloHub.Auth.JwtUtil;
import App.PhiloHub.Auth.MyUserDetails;
import App.PhiloHub.Auth.dto.AuthResponse;
import App.PhiloHub.Auth.dto.LoginRequest;
import App.PhiloHub.Auth.dto.RefreshTokenRequest;
import App.PhiloHub.Auth.dto.RegisterRequest;
import App.PhiloHub.Auth.dto.UserProfileDto;
import App.PhiloHub.Models.Country;
import App.PhiloHub.Models.Enum.AuthProvider;
import App.PhiloHub.Models.RefreshToken;
import App.PhiloHub.Models.User;
import App.PhiloHub.Repositories.CountryRepository;
import App.PhiloHub.Repositories.RefreshTokenRepository;
import App.PhiloHub.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already in use");
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username is already in use");
        }

        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country not found"));

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setBithdate(request.getBithdate());
        user.setCountry(country);
        user.setAuthProvider(AuthProvider.LOCAL);
        user.setCreatedAt(LocalDate.now());
        
        user = userRepository.save(user);
        
        MyUserDetails userDetails = new MyUserDetails(user);
        String accessToken = jwtUtil.generateAccessToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);
        
        saveRefreshToken(user, refreshToken);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .user(UserProfileDto.fromUser(user))
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();

        String accessToken = jwtUtil.generateAccessToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);

        saveRefreshToken(user, refreshToken);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .user(UserProfileDto.fromUser(user))
                .build();
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String token = request.getRefreshToken();
        
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));

        if (refreshToken.isRevoked() || refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token was expired or revoked. Please make a new signin request");
        }

        User user = refreshToken.getUser();
        MyUserDetails userDetails = new MyUserDetails(user);

        String accessToken = jwtUtil.generateAccessToken(userDetails);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(token)
                .tokenType("Bearer")
                .user(UserProfileDto.fromUser(user))
                .build();
    }
    
    @Transactional
    public void logout(String refreshTokenStr) {
        Optional<RefreshToken> token = refreshTokenRepository.findByToken(refreshTokenStr);
        token.ifPresent(req -> {
            req.setRevoked(true);
            refreshTokenRepository.save(req);
        });
    }

    private void saveRefreshToken(User user, String token) {
        // Optionally delete old tokens if we want single-device login
        refreshTokenRepository.findByUserId(user.getId()).ifPresent(refreshTokenRepository::delete);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(token);
        // Expiry matches the JWT refresh expiration (e.g. 7 days. Need a cleaner way but for now adding 7 days)
        refreshToken.setExpiryDate(Instant.now().plusMillis(604800000L)); // 7 days in ms
        refreshTokenRepository.save(refreshToken);
    }
}
