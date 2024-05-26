package com.pjt.cpumonitoring.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ErrorLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
    private Long id;

    @Column
    private String message;
    @Column
    private LocalDateTime timestamp;

    public ErrorLog() {
        // 기본 생성자
    }

    public ErrorLog(Long id, String message, LocalDateTime timestamp) {
        this.id = id;
        this.message = message;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ErrorLog{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
