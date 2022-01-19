package app.planningApp.controllers;

import app.planningApp.dto.UserInfo;
import app.planningApp.exceptions.UserFriendlyException;
import app.planningApp.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserInfo userInfo) throws UserFriendlyException {
        userService.create(userInfo);
        return ResponseEntity.ok(Map.of("msg","Registered successfully"));
    }

    @GetMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam("email") String email,
                                        @RequestParam("password") String password) throws UserFriendlyException {
        userService.login(email, password);
        return ResponseEntity.ok(Map.of("msg","Login successfully"));
    }

}
