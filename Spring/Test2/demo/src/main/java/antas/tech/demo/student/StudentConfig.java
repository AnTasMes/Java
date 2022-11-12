package antas.tech.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student andrej = new Student(
                    "Andrej Tasevski",
                    "akitasevski112@gmail.com",
                    LocalDate.of(2002, Month.JANUARY, 15));

            Student marko = new Student(
                    "Marko Markovic",
                    "marko.markovic3@gmail.com",
                    LocalDate.of(2004, Month.MAY, 15));

            repository.saveAll(
                    List.of(andrej, marko));
        };
    }
}
