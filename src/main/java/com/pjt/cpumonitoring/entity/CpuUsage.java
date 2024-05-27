package com.pjt.cpumonitoring.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class CpuUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
    private Long id;

    @Column
    private double cpu_load;
    @Column
    private LocalDateTime timestamp;

    public CpuUsage() {
        // 기본 생성자
    }

    public CpuUsage(Long id, double cpu_load, LocalDateTime timestamp) {
        this.id = id;
        this.cpu_load = cpu_load;
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return "CpuUsage{" +
                "id=" + id +
                ", cpu_load=" + cpu_load +
                ", timestamp=" + timestamp +
                '}';
    }

}
