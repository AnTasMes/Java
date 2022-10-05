package antas.tech.demo.services;

import org.springframework.stereotype.Service;

import antas.tech.demo.models.ErrorLog;
import antas.tech.demo.repositories.ErrLogRepository;

@Service
public class ErrLogService {

    ErrLogRepository logRepository;

    public ErrLogService(ErrLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void log(ErrorLog log) {
        logRepository.insert(log);
    }
}
