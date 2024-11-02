package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.SignInRequest;
import org.example.dto.Users;
import org.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/api/v1")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/save")
    public Users saveUser(@RequestBody Users user) {
        return userService.saveUser(user);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Users> signIn(@RequestBody SignInRequest signInRequest) {
        try{
            Users authenticateUser = userService.authenticateUser(signInRequest);
            return new ResponseEntity<>(authenticateUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
