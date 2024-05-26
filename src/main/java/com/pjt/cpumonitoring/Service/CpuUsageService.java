package com.pjt.cpumonitoring.Service;


import com.pjt.cpumonitoring.dto.CpuUsageForMinAvgMax;
import com.pjt.cpumonitoring.entity.CpuUsage;
import com.pjt.cpumonitoring.repository.CpuUsageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Service
public class CpuUsageService {

    private final CpuUsageRepository cpuUsageRepository;

    public CpuUsageService(CpuUsageRepository cpuUsageRepository) {
        this.cpuUsageRepository = cpuUsageRepository;
    }

    public List<CpuUsage> getMinuteData(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("getMinuteData");

        // 현재 날짜를 가져옴
        LocalDate currentDate = LocalDate.now();
        LocalDate oneWeekAgo = currentDate.minusWeeks(1);

        if (startDate.isBefore(oneWeekAgo.atStartOfDay())) {
            String errorMessage = "최근 일주일까지 조회 가능합니다.";
            throw new IllegalArgumentException(errorMessage);
        }

        return cpuUsageRepository.findByTimestampBetween(startDate, endDate);
    }

    public List<CpuUsageForMinAvgMax> getHourData(LocalDate startDate, LocalDate endDate) {
        log.info("getHourData");

        // 현재 날짜를 가져옴
        LocalDate currentDate = LocalDate.now();
        LocalDate threeMonthsAgo = currentDate.minusMonths(3);

        if (startDate.isBefore(threeMonthsAgo)) {
            String errorMessage = "최근 3달 데이터까지 조회 가능합니다.";
            throw new IllegalArgumentException(errorMessage);
        }

        // LocalDate -> LocalDateTime 변환 (시작일 => 00:00:00, 마지막날 => 23:59:59)
        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate.atTime(23, 59, 59);

        return cpuUsageRepository.findCpuUsageStatsPerHour(startOfDay, endOfDay);
    }

    public List<CpuUsageForMinAvgMax> getDayData(LocalDate startDate, LocalDate endDate) {
        log.info("getDayData");

        // 현재 날짜를 가져옴
        LocalDate currentDate = LocalDate.now();
        LocalDate oneYearAgo = currentDate.minusYears(1);

        if (startDate.isBefore(oneYearAgo)) {
            String errorMessage = "최근 1년 데이터까지 조회 가능합니다.";
            throw new IllegalArgumentException(errorMessage);
        }

        // LocalDate -> LocalDateTime 변환 (시작일 => 00:00:00, 마지막날 => 23:59:59)
        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate.atTime(23, 59, 59);

        return cpuUsageRepository.findCpuUsageStatsPerDay(startOfDay, endOfDay);
    }

}
