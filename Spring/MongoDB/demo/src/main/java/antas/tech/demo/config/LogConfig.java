package antas.tech.demo.config;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import antas.tech.demo.model.ErrorLogRecord;
import antas.tech.demo.model.LogRecord;
import antas.tech.demo.repo.LogRepository;

@Configuration()
public class LogConfig {

    @Bean
    CommandLineRunner LoglineRunner(LogRepository logRepository) {
        Map<String, String> c = new HashMap<String, String>();

        c.put("STATUS", "ONLINE");
        c.put("COMMAND", "TURN_ON");

        return args -> {
            ErrorLogRecord err = new ErrorLogRecord("Geska");
            LogRecord rec = new LogRecord("Poruka log record", LocalTime.now(), c);
            LogRecord rec2 = new LogRecord("Log bez mape", LocalTime.now());

            logRepository.saveAll(List.of(err, rec, rec2));
        };
    }
}
