package com.wdev.secutity.controllers;

import com.wdev.secutity.dtos.CreateUserDTO;
import com.wdev.secutity.dtos.UserDTO;
import com.wdev.secutity.entities.User;
import com.wdev.secutity.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @PostMapping("/register")
    public ResponseEntity<Void> NewUser(@RequestBody CreateUserDTO dto) {
        return userService.NewUser(dto);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listarUsers() throws Exception {
        return userService.listarUsers();
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> pegaUsuario(JwtAuthenticationToken token) throws Exception {
       return userService.pegaUsuario(token);
    }
}
