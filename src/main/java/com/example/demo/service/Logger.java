package com.example.demo.service;

import com.example.demo.apimodels.LogMessage;
import com.example.demo.config.LoggerConfig;
import com.example.demo.enums.LogLevel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.*;

@Service
public class Logger {

    private LoggerConfig config;
    private BlockingQueue<LogMessage> messageQueue;
    private ExecutorService executorService;

    public Logger(LoggerConfig config) {
        this.config = config;
        this.messageQueue = new LinkedBlockingQueue<>(config.getBufferSize());
        this.executorService = new ThreadPoolExecutor(20, 30, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        if (config.isAsync()) {
            startAsyncLogging();
        }
    }

    private void startAsyncLogging() {
        executorService.submit(() -> {
            while (true) {
                try {
                    LogMessage message = messageQueue.take();
                    logToSinks(message);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
    }

    public void log(LogLevel level, String content) {
        if (level.isHigherOrEqual(config.getLogLevel())) {
            LogMessage message = new LogMessage(LocalDateTime.now(), level, content, config.getDateTimeFormatter());
            if (config.isAsync()) {
                try {
                    messageQueue.put(message);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                logToSinks(message);
            }
        }
    }

    private void logToSinks(LogMessage message) {
        for (Sink sink : config.getSinks()) {
            sink.log(message);
        }
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
