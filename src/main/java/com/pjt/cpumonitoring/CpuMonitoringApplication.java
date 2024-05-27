package com.pjt.cpumonitoring;

import com.pjt.cpumonitoring.entity.CpuUsage;
import com.pjt.cpumonitoring.entity.ErrorLog;
import com.pjt.cpumonitoring.repository.ErrorLogRepository;
import com.sun.management.OperatingSystemMXBean;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import java.util.Arrays;

@Log4j2
@SpringBootApplication
@EnableScheduling
public class CpuMonitoringApplication {

    private final ErrorLogRepository errorLogRepository;

    public CpuMonitoringApplication(ErrorLogRepository errorLogRepository) {
        this.errorLogRepository = errorLogRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CpuMonitoringApplication.class, args);
    }

    // 분 단위로 cpu 사용률 저장
    @Scheduled(fixedRate = 60000)
    public void monitorCpuUsage() {
        try {
            // CPU 사용률 조회
            OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

            double cpu_load = osBean.getCpuLoad() * 100;

            CpuUsage cpuUsage = new CpuUsage(null, cpu_load, LocalDateTime.now());
            log.info("CpuUsage = {}", cpuUsage.toString());
//                CpuUsage saved = cpuUsageRepository.save(cpuUsage);

        } catch (Exception e) {
            String errorMessage = e.toString() + "\t/\t" + Arrays.toString(e.getStackTrace());
            saveErrorLog(errorMessage);
        }
    }

    private void saveErrorLog(String message) {
        ErrorLog errorLog = new ErrorLog(null, message, LocalDateTime.now());
        errorLogRepository.save(errorLog);
    }


}
