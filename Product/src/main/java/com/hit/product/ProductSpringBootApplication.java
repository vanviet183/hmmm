package com.hit.product;

import com.hit.product.applications.commons.ERole;
import com.hit.product.applications.repositories.RoleRepository;
import com.hit.product.applications.repositories.UserRepository;
import com.hit.product.domains.entities.Role;
import com.hit.product.domains.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication()
@EnableScheduling
public class ProductSpringBootApplication {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public ProductSpringBootApplication(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductSpringBootApplication.class, args);
    }

    @Bean
    CommandLineRunner init() {
        return args -> {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            if(roleRepository.count() == 0) {
                roleRepository.save(new Role(ERole.ROLE_ADMIN, null));
                roleRepository.save(new Role(ERole.ROLE_MANAGER, null));
                roleRepository.save(new Role(ERole.ROLE_USER, null));
            }

            if (userRepository.count() == 0) {
                User user = new User("admin", passwordEncoder.encode("admin"), "admin@gmail.com", "0983467852", "Hanoi", roleRepository.findAll(), true);
                userRepository.save(user);
            }
        };
    }
}
