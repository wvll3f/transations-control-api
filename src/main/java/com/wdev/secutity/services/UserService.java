package com.wdev.secutity.services;

import com.wdev.secutity.dtos.CreateUserDTO;
import com.wdev.secutity.dtos.UserDTO;
import com.wdev.secutity.entities.Role;
import com.wdev.secutity.entities.User;
import com.wdev.secutity.repositories.RoleRepository;
import com.wdev.secutity.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public ResponseEntity<Void> NewUser(@RequestBody CreateUserDTO dto) {

        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());
        var userDB = userRepository.findByUsername(dto.username());

        if (userDB.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        var user = new User();
        user.setName(dto.name());
        user.setUsername(dto.username());
        user.setPassword(bCryptPasswordEncoder.encode(dto.password()));
        user.setRoles(Set.of(basicRole));

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listarUsers() throws Exception {
        var users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<UserDTO> pegaUsuario(JwtAuthenticationToken token) throws Exception {

        var existenceUser = userRepository.findById(UUID.fromString(token.getName()));
        var user = new UserDTO();

        user.setUsername(existenceUser.get().getUsername());
        user.setRoles(existenceUser.get()
                .getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" ")).toString());

        return ResponseEntity.ok(user);
    }


}
