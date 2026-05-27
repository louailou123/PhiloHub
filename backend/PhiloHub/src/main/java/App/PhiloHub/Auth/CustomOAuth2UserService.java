package App.PhiloHub.Auth;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import App.PhiloHub.Models.Enum.AuthProvider;
import App.PhiloHub.Models.User;
import App.PhiloHub.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        AuthProvider authProvider = AuthProvider.valueOf(registrationId.toUpperCase());
        String providerId = oAuth2User.getName(); // Depending on provider, usually 'sub' or 'id'
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        Optional<User> userOptional = userRepository.findByEmail(email);

        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            if (!user.getAuthProvider().equals(authProvider)) {
                // Link account if email matches but provider differs, or throw exception. We'll update the provider for simplicity.
                user.setAuthProvider(authProvider);
                user.setProviderId(providerId);
                user = userRepository.save(user);
            }
        } else {
            user = new User();
            user.setEmail(email);
        if (email != null && !email.isEmpty()) {
            user.setUsername(email.split("@")[0]); // default username
        } else {
            user.setUsername(providerId); // fallback
        }
            
            if (name != null) {
                String[] names = name.split(" ", 2);
                user.setFirstName(names[0]);
                user.setLastName(names.length > 1 ? names[1] : "");
            } else {
                user.setFirstName("OAuth");
                user.setLastName("User");
            }
            
            user.setAuthProvider(authProvider);
            user.setProviderId(providerId);
            user.setCreatedAt(LocalDate.now());
            user = userRepository.save(user);
        }

        return new MyOAuth2User(oAuth2User, user);
    }
}
