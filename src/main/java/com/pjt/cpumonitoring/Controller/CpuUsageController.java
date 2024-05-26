package com.pjt.cpumonitoring.Controller;

import com.pjt.cpumonitoring.Service.CpuUsageService;
import com.pjt.cpumonitoring.dto.CpuUsageForMinAvgMax;
import com.pjt.cpumonitoring.entity.CpuUsage;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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

    @GetMapping("/getMinuteData")
    @ResponseBody
    public ResponseEntity<?> getMinuteData(@RequestParam("startDate") LocalDateTime startDate,
                                           @RequestParam("endDate") LocalDateTime endDate) {
        log.info("getMinuteData");

        List<CpuUsage> getMinuteDataResult = cpuUsageService.getMinuteData(startDate, endDate);
        return ResponseEntity.ok(getMinuteDataResult.toString());
    }

    @GetMapping("/getHourData")
    @ResponseBody
    public ResponseEntity<?> getHourData(@RequestParam("startDate") LocalDate startDate,
                                         @RequestParam("endDate") LocalDate endDate) {

        List<CpuUsageForMinAvgMax> getHourDataResult = cpuUsageService.getHourData(startDate, endDate);
        return ResponseEntity.ok(getHourDataResult.toString());
    }

    @GetMapping("/getDayData")
    @ResponseBody
    public ResponseEntity<?> getDayData(@RequestParam("startDate") LocalDate startDate,
                                        @RequestParam("endDate") LocalDate endDate) {
        log.info("getDayData");

        List<CpuUsageForMinAvgMax> getDayDataResult = cpuUsageService.getDayData(startDate, endDate);
        return ResponseEntity.ok(getDayDataResult.toString());
    }
}

