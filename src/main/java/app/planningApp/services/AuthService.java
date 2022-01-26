package app.planningApp.services;

import app.planningApp.dto.UserInfo;
import app.planningApp.entities.user.User;
import app.planningApp.exceptions.UserFriendlyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;

    @Autowired
    public AuthService(UserService userService){
        this.userService = userService;
    }

    public void create(UserInfo userInfo) throws UserFriendlyException {
        userService.create(userInfo);
    }

    public void login(String email, String password) throws UserFriendlyException {
        User user = userService.getUserByEmail(email).orElseThrow(
                () -> new UserFriendlyException("User with this email does not exist!"));
        if(!userService.checkPassword(password, user)) {
            throw new UserFriendlyException("Wrong password!");
        }
    }

}
