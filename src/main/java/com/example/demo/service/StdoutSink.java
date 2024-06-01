package com.example.demo.service;

import com.example.demo.apimodels.LogMessage;
import com.example.demo.enums.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class StdoutSink implements Sink {

    private final LogLevel logLevel;

    public StdoutSink(LogLevel logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public void log(LogMessage message) {
        if (message.getLogLevel().isHigherOrEqual(logLevel)) {
            System.out.println(message);
        }
    }

    @Override
    public LogLevel getLogLevel() {
        return logLevel;
    }
}
