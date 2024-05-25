package com.pjt.cpumonitoring.Controller;

import com.pjt.cpumonitoring.Service.CpuUsageService;
import com.pjt.cpumonitoring.entity.CpuUsage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CpuUsageController(CpuUsageService cpuUsageService) {
        this.cpuUsageService = cpuUsageService;
    }

    @GetMapping("/getMinuteData")
    @ResponseBody
    public String getMinuteData(@RequestParam LocalDateTime startDate,
                                @RequestParam LocalDateTime endDate) {
        log.info("getMinuteData");

        return cpuUsageService.getMinuteData(startDate, endDate).toString();
    }


}
