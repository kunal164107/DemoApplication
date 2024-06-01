package com.example.demo.service;

import com.example.demo.apimodels.LogMessage;
import com.example.demo.enums.LogLevel;
import org.springframework.stereotype.Service;

@Service
public interface Sink {

    void log(LogMessage message);

    LogLevel getLogLevel();
}
