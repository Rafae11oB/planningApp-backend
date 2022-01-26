package app.planningApp.controllers;

import app.planningApp.dto.UserInfo;
import app.planningApp.exceptions.UserFriendlyException;
import app.planningApp.security.jwt.JwtProvider;
import app.planningApp.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(AuthService authService, JwtProvider jwtProvider){
        this.authService = authService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody UserInfo userInfo) throws UserFriendlyException {
        authService.create(userInfo);
        return ResponseEntity.ok(Map.of("msg","Registered successfully"));
    }

    @GetMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam("email") String email,
                                        @RequestParam("password") String password) throws UserFriendlyException {
        authService.login(email, password);
        String token = jwtProvider.generateToken(email);
        return ResponseEntity.ok(Map.of("msg","Login successfully",
                                        "token", token));
    }

}
