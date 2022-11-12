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
                // User AnTasMes = userRepository.findByUsername("AnTasMes").get();
                // System.out.println(AnTasMes);
                // List<User> friends = new ArrayList<>();
                // friends.add(userRepository.findByUsername("Mark").get());
                // AnTasMes.setFriends(friends);
                // userRepository.save(AnTasMes);
            };
        return null;
    }

}
