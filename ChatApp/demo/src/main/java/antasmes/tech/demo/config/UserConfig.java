package antasmes.tech.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import antasmes.tech.demo.repositories.UserRepository;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner useLineRunner(UserRepository userRepository, SSHTunnel connect) {
        System.out.println(connect.getSession());
        if (connect.getSession() != null)
            return args -> {
                // User u = new User("AnTasMes", "1231", "mail");
                // userRepository.insert(u);
            };
        return null;
    }
}
