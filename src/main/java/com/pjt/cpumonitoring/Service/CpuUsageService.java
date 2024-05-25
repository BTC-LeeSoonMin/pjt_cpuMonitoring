package com.pjt.cpumonitoring.Service;


import com.pjt.cpumonitoring.entity.CpuUsage;
import com.pjt.cpumonitoring.repository.CpuUsageRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Service
public class CpuUsageService {

    @Autowired
    private CpuUsageRepository cpuUsageRepository;

//    public List<CpuUsage> getMinuteData(LocalDateTime startDate, LocalDateTime endDate) {
    public Object getMinuteData(LocalDateTime startDate, LocalDateTime endDate) {
        log.info("getMinuteData");

        // endDate와 startDate의 차이 계산
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        log.info("daysBetween: {}", daysBetween);

        // endDate가 startDate보다 7일 이상인 경우 에러 메시지 반환
        if (daysBetween > 7) {
            log.info("최근 일주일까지 조회 가능합니다.");
            return "최근 일주일까지 조회 가능합니다.";
        }

        return cpuUsageRepository.findByTimestampBetween(startDate, endDate);
    }
}
