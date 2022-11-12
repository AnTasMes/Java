package antas.tech.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import antas.tech.demo.model.Record;
import antas.tech.demo.repo.LogRepository;

@Service
public class LogService {

    private LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<Record> getRecordsByType(String type) {
        return logRepository.findLogsByType(type);
    }

    public List<Record> getAllRecorsd() {
        return logRepository.findAll();
    }
}
