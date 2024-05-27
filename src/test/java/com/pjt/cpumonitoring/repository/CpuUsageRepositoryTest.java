package com.pjt.cpumonitoring.repository;

import com.pjt.cpumonitoring.dto.CpuUsageForMinAvgMax;
import com.pjt.cpumonitoring.entity.CpuUsage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CpuUsageRepositoryTest {

    @Autowired
    private CpuUsageRepository cpuUsageRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        // 테스트 데이터를 설정합니다.
        entityManager.persist(new CpuUsage(null, 10.0, LocalDateTime.of(2024, 5, 26, 1, 0)));
        entityManager.persist(new CpuUsage(null, 20.0, LocalDateTime.of(2024, 5, 26, 1, 1)));
        entityManager.persist(new CpuUsage(null, 30.0, LocalDateTime.of(2024, 5, 26, 1, 2)));
        entityManager.persist(new CpuUsage(null, 40.0, LocalDateTime.of(2024, 5, 26, 1, 3)));
        entityManager.persist(new CpuUsage(null, 50.0, LocalDateTime.of(2024, 5, 26, 1, 4)));
        entityManager.persist(new CpuUsage(null, 60.0, LocalDateTime.of(2024, 5, 26, 1, 5)));
    }

    @Test
    @DisplayName("분 단위 조회")
    void findByTimestampBetween() {
        // Given : 시나리오 진행에 필요한 값을 설정한다.
        LocalDateTime startTime = LocalDateTime.of(2024, 5, 26, 1, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2024, 5, 26, 2, 0, 0);

        // When : 테스트하고자 하는 행동을 명시한다.
        List<CpuUsage> cpuUsageList = cpuUsageRepository.findByTimestampBetween(startTime, endTime);

        // Then : 테스트를 통해 도출된 결과를 검증한다.
        // Given에서 주어진 startTime부터 endTime 범위 내에 적합한지 확인
        boolean allWithinRange = cpuUsageList.stream()
                .allMatch(cpuUsage ->
                        !cpuUsage.getTimestamp().isBefore(startTime) &&
                        !cpuUsage.getTimestamp().isAfter(endTime));
        assertTrue(allWithinRange);

    }

    @Test
    @DisplayName("시 단위 조회")
    void findCpuUsageStatsPerHour() {
        // Given : 시나리오 진행에 필요한 값을 설정한다.
        LocalDateTime startDate = LocalDateTime.of(2024, 3, 26, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 26, 23, 59, 59);

        // When : 테스트하고자 하는 행동을 명시한다.
        List<CpuUsageForMinAvgMax> cpuUsageList = cpuUsageRepository.findCpuUsageStatsPerHour(startDate, endDate);

        // Then : 테스트를 통해 도출된 결과를 검증한다.
        CpuUsageForMinAvgMax dayStats = cpuUsageList.get(0);
        assertEquals(10.0, dayStats.getMin_cpu_load());
        assertEquals(35.0, dayStats.getAvg_cpu_load());
        assertEquals(60.0, dayStats.getMax_cpu_load());

    }

    @Test
    @DisplayName("일 단위 조회")
    void findCpuUsageStatsPerDay() {
        // Given : 시나리오 진행에 필요한 값을 설정한다.
        LocalDateTime startDate = LocalDateTime.of(2024, 3, 26, 0, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 5, 26, 23, 59, 59);

        // When : 테스트하고자 하는 행동을 명시한다.
        List<CpuUsageForMinAvgMax> cpuUsageList = cpuUsageRepository.findCpuUsageStatsPerDay(startDate, endDate);

        // Then : 테스트를 통해 도출된 결과를 검증한다.
        CpuUsageForMinAvgMax dayStats = cpuUsageList.get(0);
        assertEquals(10.0, dayStats.getMin_cpu_load());
        assertEquals(35.0, dayStats.getAvg_cpu_load());
        assertEquals(60.0, dayStats.getMax_cpu_load());

    }

}
