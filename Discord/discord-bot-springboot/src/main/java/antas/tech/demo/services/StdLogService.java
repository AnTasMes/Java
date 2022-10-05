package antas.tech.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antas.tech.demo.models.StdLog;
import antas.tech.demo.repositories.StdLogRepository;

@Service
public class StdLogService {

    StdLogRepository logRepository;

    @Autowired
    public StdLogService(StdLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void log(StdLog log) {
        logRepository.save(log);
    }
}
