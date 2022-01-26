package app.planningApp.security;

import app.planningApp.entities.user.User;
import app.planningApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService){
        this.userService = userService;
    }

    /**
     * @param username user email
     * @return CustomUserDetails instance
     * @throws UsernameNotFoundException if user with this email does not exist
     */
    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User with this email does not exist"));
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }
}
