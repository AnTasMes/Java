package antas.tech.demo.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import antas.tech.demo.model.User;
import antas.tech.demo.repo.UserRepository;

@Configuration("User")
public class UserConfig {
    // @Bean
    // CommandLineRunner UserlineRunner(UserRepository userRepository) {
    // return args -> {
    // User u1 = new User(
    // "AnTasMes",
    // "4434");
    // User u2 = new User("Gandi", "5545");

    // userRepository.saveAll(List.of(u1, u2));
    // };
    // }
}
