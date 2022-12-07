package il.neuralpsy.edustuff.service;

import il.neuralpsy.edustuff.model.User;
import il.neuralpsy.edustuff.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findUserByEmail(usernameOrEmail).get();
        if(user != null){
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                    Collections.singletonList(
                            new SimpleGrantedAuthority(user.getUserType().getName())));
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
