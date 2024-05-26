package com.pjt.cpumonitoring.dto;

import lombok.Data;

@Data
public class CpuUsageForMinAvgMax {

    private Double min_cpu_load;
    private Double avg_cpu_load;
    private Double max_cpu_load;

    public CpuUsageForMinAvgMax(Double min_cpu_load, Double avg_cpu_load, Double max_cpu_load) {
        this.min_cpu_load = min_cpu_load;
        this.avg_cpu_load = avg_cpu_load;
        this.max_cpu_load = max_cpu_load;
    }

}
