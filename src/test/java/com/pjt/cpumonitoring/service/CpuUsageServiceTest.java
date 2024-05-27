package com.pjt.cpumonitoring.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CpuUsageServiceTest {

    @Test
    @DisplayName("분 단위 조회")
    void getMinuteData() {
        // Given : 시나리오 진행에 필요한 값을 설정한다.
        LocalDateTime startDate = LocalDateTime.now().minusDays(6);

        // When : 테스트하고자 하는 행동을 명시한다.
        // 최근 1주 데이터만 제공
        LocalDate currentDate = LocalDate.now();
        LocalDate oneWeekAgo = currentDate.minusWeeks(1);

        // Then : 테스트를 통해 도출된 결과를 검증한다.
        assertFalse(startDate.isBefore(oneWeekAgo.atStartOfDay()));

    }

    @Test
    @DisplayName("시 단위 조회")
    void getHourData() {
        // Given : 시나리오 진행에 필요한 값을 설정한다.
        LocalDate startDate = LocalDate.now().minusDays(6);
        LocalDate endDate = LocalDate.now().minusDays(5);

        // When : 테스트하고자 하는 행동을 명시한다.
        // 최근 3달 데이터만 제공
        LocalDate currentDate = LocalDate.now();
        LocalDate threeMonthsAgo = currentDate.minusMonths(3);

        // LocalDate -> LocalDateTime 변환 (시작일 => 00:00:00, 마지막날 => 23:59:59)
        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate.atTime(23, 59, 59);

        // Then : 테스트를 통해 도출된 결과를 검증한다.
        assertFalse(startDate.isBefore(threeMonthsAgo));
        assertEquals(0, startOfDay.getHour());
        assertEquals(0, startOfDay.getMinute());
        assertEquals(0, startOfDay.getSecond());
        assertEquals(23, endOfDay.getHour());
        assertEquals(59, endOfDay.getMinute());
        assertEquals(59, endOfDay.getSecond());

    }

    @Test
    @DisplayName("일 단위 조회")
    void getDayData() {
        // Given : 시나리오 진행에 필요한 값을 설정한다.
        LocalDate startDate = LocalDate.now().minusDays(6);
        LocalDate endDate = LocalDate.now().minusDays(5);

        // When : 테스트하고자 하는 행동을 명시한다.
        // 최근 1년 데이터만 제공
        LocalDate currentDate = LocalDate.now();
        LocalDate oneYearAgo = currentDate.minusYears(1);

        // LocalDate -> LocalDateTime 변환 (시작일 => 00:00:00, 마지막날 => 23:59:59)
        LocalDateTime startOfDay = startDate.atStartOfDay();
        LocalDateTime endOfDay = endDate.atTime(23, 59, 59);

        // Then : 테스트를 통해 도출된 결과를 검증한다.
        assertFalse(startDate.isBefore(oneYearAgo));
        assertEquals(0, startOfDay.getHour());
        assertEquals(0, startOfDay.getMinute());
        assertEquals(0, startOfDay.getSecond());
        assertEquals(23, endOfDay.getHour());
        assertEquals(59, endOfDay.getMinute());
        assertEquals(59, endOfDay.getSecond());

    }

}


