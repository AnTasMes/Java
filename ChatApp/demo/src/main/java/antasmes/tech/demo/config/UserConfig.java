package antasmes.tech.demo.config;

import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import antasmes.tech.demo.models.User;
import antasmes.tech.demo.repositories.UserRepository;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner useLineRunner(UserRepository userRepository, SSHConnect connect) {
        System.out.println(connect.getSession());
        if (connect.getSession() != null)
            return args -> {
                User u = new User("AnTasMes", "1231", "mail", LocalTime.now(), LocalTime.now());
                userRepository.insert(u);
            };
        return null;
    }
}
