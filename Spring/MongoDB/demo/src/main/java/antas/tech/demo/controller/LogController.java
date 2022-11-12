package antas.tech.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import antas.tech.demo.model.Record;
import antas.tech.demo.service.LogService;

@RestController
@RequestMapping(path = "/log")
public class LogController {

    private LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public List<Record> getAllRecords() {
        return logService.getAllRecorsd();
    }

}
