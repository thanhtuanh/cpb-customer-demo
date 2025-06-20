package com.cpb.customerdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    public DataInitializer(UserRepository userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        if (userRepo.findByUsername("admin").isEmpty()) {
            userRepo.save(new User("admin", encoder.encode("admin123"), "ADMIN"));
        }
        if (userRepo.findByUsername("user").isEmpty()) {
            userRepo.save(new User("user", encoder.encode("user123"), "USER"));
        }
    }
}