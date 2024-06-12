package com.wdev.secutity.config;

import com.wdev.secutity.entities.Role;
import com.wdev.secutity.entities.User;
import com.wdev.secutity.repositories.CategoriaRepository;
import com.wdev.secutity.repositories.RoleRepository;
import com.wdev.secutity.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminUserConfig(RoleRepository roleRepository,
                           UserRepository userRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roles = roleRepository.findAll();

        if (roles.isEmpty()) {

            var adminRole = new Role();
            adminRole.setId(1L);
            adminRole.setName("ADMIN");

            var basicRole = new Role();
            basicRole.setId(2L);
            basicRole.setName("BASIC");

            roleRepository.save(adminRole);
            roleRepository.save(basicRole);

        } else {
            System.out.println("Roles já existem");
        }

        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());
        var userAdmin = userRepository.findByUsername("admin");

        if (userAdmin.isEmpty()) {
            var user = new User();
            user.setUsername("admin");
            user.setPassword(bCryptPasswordEncoder.encode("125425"));
            user.setRoles(Set.of(roleAdmin));
            userRepository.save(user);
        } else {
            System.out.println("Admin ja existe");
        }

    }
}
