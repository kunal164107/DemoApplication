package com.example.demo.apimodels;

import com.example.demo.enums.LogLevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class LogMessage {

    private LocalDateTime timestamp;
    private LogLevel logLevel;
    private String content;
    private DateTimeFormatter dateTimeFormatter;

    @Override
    public String toString() {
        return timestamp.format(dateTimeFormatter) + " [" + logLevel + "] " + content;
    }
}
