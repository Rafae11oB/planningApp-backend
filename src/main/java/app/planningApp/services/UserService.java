package app.planningApp.services;

import app.planningApp.dto.UserInfo;
import app.planningApp.entities.user.User;
import app.planningApp.exceptions.UserFriendlyException;
import app.planningApp.repositories.UserRepository;
import app.planningApp.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Boolean isEmailExist(String email){
        return userRepository.existsByEmail(email);
    }

    public boolean checkPassword(String password, User user){
        return PasswordEncoder.bCryptPasswordEncoder().matches(password, user.getPassword());
    }

    public void create(UserInfo userInfo) throws UserFriendlyException {
        if(isEmailExist(userInfo.getEmail())){
            throw new UserFriendlyException("User with this email already exist!");
        }
        User user = new User(userInfo);
        userRepository.save(user);
    }
}
