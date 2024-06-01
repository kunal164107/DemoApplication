package com.example.demo.enums;

public enum LogLevel {
    DEBUG,
    INFO,
    WARN,
    ERROR,
    FATAL;

    public boolean isHigherOrEqual(LogLevel logLevel) {
        return this.ordinal() >= logLevel.ordinal();
    }
}
