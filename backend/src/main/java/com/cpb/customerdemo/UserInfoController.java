package com.cpb.customerdemo;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userinfo")
public class UserInfoController {
    private final UserRepository userRepo;

    public UserInfoController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public UserInfo getUserInfo(Authentication auth) {
        var user = userRepo.findByUsername(auth.getName()).orElseThrow();
        // Entferne "ROLE_" Prefix, falls Spring das anhängt
        String role = user.getRole().replace("ROLE_", "");
        return new UserInfo(user.getUsername(), role);
    }

    record UserInfo(String username, String role) {}
}