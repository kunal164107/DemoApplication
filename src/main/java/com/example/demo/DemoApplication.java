package com.example.demo;

import com.example.demo.config.LoggerConfig;
import com.example.demo.enums.LogLevel;
import com.example.demo.service.Logger;
import com.example.demo.service.Sink;
import com.example.demo.service.StdoutSink;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DemoApplication {

	public static void main(String[] args) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");

		// Create syncLogger
		Sink syncStdoutSink = new StdoutSink(LogLevel.INFO);
		LoggerConfig syncConfig = new LoggerConfig(formatter, LogLevel.INFO, false, 25, Arrays.asList(syncStdoutSink));
		Logger syncLogger = new Logger(syncConfig);

		// Create asyncLogger
		Sink asyncStdoutSink = new StdoutSink(LogLevel.ERROR);
		LoggerConfig asyncConfig = new LoggerConfig(formatter, LogLevel.ERROR, true, 25, Arrays.asList(asyncStdoutSink));
		Logger asyncLogger = new Logger(asyncConfig);

		// sync logging test
		System.out.println("sync logging test:");
		syncLogger.log(LogLevel.INFO, "Sync Info message");
		syncLogger.log(LogLevel.WARN, "Sync Warn message");
		syncLogger.log(LogLevel.DEBUG, "Sync Debug message");
		syncLogger.log(LogLevel.ERROR, "Sync Error message");

		// async logging test
		System.out.println("async logging test:");
		asyncLogger.log(LogLevel.INFO, "Async Info message");
		asyncLogger.log(LogLevel.WARN, "Async Warn message");
		asyncLogger.log(LogLevel.DEBUG, "Async Debug message");
		asyncLogger.log(LogLevel.ERROR, "Async Error message");

		// concurrent logging test
		System.out.println("concurrent logging test:");
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			int finalI = i;
			executor.submit(() -> asyncLogger.log(LogLevel.INFO, "Concurrent Info message " + finalI));
			executor.submit(() -> asyncLogger.log(LogLevel.WARN, "Concurrent Warn message " + finalI));
			executor.submit(() -> asyncLogger.log(LogLevel.ERROR, "Concurrent Error message " + finalI));
		}

		// Shutdown executors
		executor.shutdown();
		try {
			if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
				executor.shutdownNow();
			}
		} catch (InterruptedException e) {
			executor.shutdownNow();
		}
		syncLogger.shutdown();
		asyncLogger.shutdown();
	}
}
