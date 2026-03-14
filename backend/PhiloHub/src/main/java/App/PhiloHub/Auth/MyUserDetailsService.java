package App.PhiloHub.Auth;

import App.PhiloHub.Models.User;
import App.PhiloHub.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =userRepository.findByEmail(username).
                orElseThrow(()->new UsernameNotFoundException("email not found"));
        return new MyUserDetails(user);
    }
}
