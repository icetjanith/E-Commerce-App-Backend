package org.example.service;

import org.example.dto.SignInRequest;
import org.example.dto.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Users saveUser(Users user);
    Users authenticateUser(SignInRequest signInRequest) throws Exception;
}
