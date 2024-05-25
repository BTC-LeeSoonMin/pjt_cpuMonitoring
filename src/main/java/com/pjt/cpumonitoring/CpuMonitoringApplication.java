package com.pjt.cpumonitoring;

import com.pjt.cpumonitoring.repository.CpuUsageRepository;
import com.pjt.cpumonitoring.entity.CpuUsage;
import com.sun.management.OperatingSystemMXBean;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;

@Log4j2
@SpringBootApplication
@EnableScheduling
public class CpuMonitoringApplication {

    @Autowired
    private CpuUsageRepository cpuUsageRepository;


    public static void main(String[] args) {
        SpringApplication.run(CpuMonitoringApplication.class, args);
    }

    @Scheduled(fixedRate = 1000) // 60 seconds
    public void monitorCpuUsage() {
        try {
            // CPU 사용률 조회
            // CPU 사용률 0일 때는 DB에 저장되지 않음.
            OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

            double cpuLoad = osBean.getCpuLoad() * 100;
            if (cpuLoad > 0) {
                CpuUsage cpuUsage = new CpuUsage(null, cpuLoad, LocalDateTime.now());
                CpuUsage saved = cpuUsageRepository.save(cpuUsage);

//                log.info("tp ==> {}",saved);
            } else {
                log.info("cpu load is 0");
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


}
