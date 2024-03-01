package ra.springsecurityjwt.security.principle;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.springsecurityjwt.entity.User;
import ra.springsecurityjwt.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceCustom implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return UserDetailsCustom.build(user);
    }
}
