package com.pjt.cpumonitoring.controller;

import com.pjt.cpumonitoring.service.CpuUsageService;
import com.pjt.cpumonitoring.dto.CpuUsageForMinAvgMax;
import com.pjt.cpumonitoring.entity.CpuUsage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Controller
@RequestMapping("/api")
public class CpuUsageController {

    private final CpuUsageService cpuUsageService;

    public CpuUsageController(CpuUsageService cpuUsageService) {
        this.cpuUsageService = cpuUsageService;
    }

    @Tag(name = "분 단위 조회")
    @Operation(description = "지정한 시간 구간의 분 단위 CPU 사용률을 조회합니다.(최근 1주 데이터 제공)")
    @GetMapping("/getMinuteData")
    @ResponseBody
    public ResponseEntity<List<CpuUsage>> getMinuteData(@RequestParam(value = "startDate") LocalDateTime startDate,
                                           @RequestParam(value = "endDate") LocalDateTime endDate) {
        log.info("getMinuteData");

        List<CpuUsage> getMinuteDataResult = cpuUsageService.getMinuteData(startDate, endDate);
        return ResponseEntity.ok(getMinuteDataResult);
    }

    @Tag(name = "시 단위 조회")
    @Operation(description = "지정한 날짜의 시 단위 CPU 최소/최대/평균 사용률을 조회합니다.(최근 3달 데이터 제공)")
    @GetMapping("/getHourData")
    @ResponseBody
    public ResponseEntity<List<CpuUsageForMinAvgMax>> getHourData(@RequestParam(value = "startDate")  LocalDate startDate,
                                         @RequestParam(value = "endDate") LocalDate endDate) {
        log.info("getHourData");

        List<CpuUsageForMinAvgMax> getHourDataResult = cpuUsageService.getHourData(startDate, endDate);
        return ResponseEntity.ok(getHourDataResult);
    }

    @Tag(name = "일 단위 조회")
    @Operation(description = "지정한 날짜 구간의 일 단위 CPU 최소/최대/평균 사용률을 조회합니다.(최근 1년 데이터 제공)")
    @GetMapping("/getDayData")
    @ResponseBody
    public ResponseEntity<List<CpuUsageForMinAvgMax>> getDayData(@RequestParam("startDate") LocalDate startDate,
                                        @RequestParam("endDate") LocalDate endDate) {
        log.info("getDayData");

        List<CpuUsageForMinAvgMax> getDayDataResult = cpuUsageService.getDayData(startDate, endDate);
        return ResponseEntity.ok(getDayDataResult);
    }
}

