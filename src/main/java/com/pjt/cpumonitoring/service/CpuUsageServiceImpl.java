package com.pjt.cpumonitoring.service;

import com.pjt.cpumonitoring.dto.CpuUsageForMinAvgMax;
import com.pjt.cpumonitoring.entity.CpuUsage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CpuUsageServiceImpl {

    List<CpuUsage>  getMinuteData(LocalDateTime startDate, LocalDateTime endDate);
    List<CpuUsageForMinAvgMax> getHourData(LocalDate startDate, LocalDate endDate);
    List<CpuUsageForMinAvgMax> getDayData(LocalDate startDate, LocalDate endDate);

}
