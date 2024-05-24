package com.pjt.cpumonitoring.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class CpuUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
    private Long id;

    @Column
    private double cpuLoad;
    @Column
    private LocalDateTime timestamp;

    public CpuUsage() {
        // 기본 생성자
    }

    public CpuUsage(Long id, double cpuLoad, LocalDateTime timestamp) {
        this.id = id;
        this.cpuLoad = cpuLoad;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CpuUsage{" +
                "id=" + id +
                ", cpuLoad=" + cpuLoad +
                ", timestamp=" + timestamp +
                '}';
    }


}
