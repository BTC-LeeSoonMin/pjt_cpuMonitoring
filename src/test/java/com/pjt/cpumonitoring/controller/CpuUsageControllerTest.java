package com.pjt.cpumonitoring.controller;

import com.pjt.cpumonitoring.dto.CpuUsageForMinAvgMax;
import com.pjt.cpumonitoring.entity.CpuUsage;
import com.pjt.cpumonitoring.service.CpuUsageService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CpuUsageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CpuUsageService cpuUsageService;

    @Test
    @DisplayName("분 단위 조회")
    void GetMinuteData() throws Exception {
        // Given
        LocalDateTime startDate = LocalDateTime.now().minusDays(7);
        LocalDateTime endDate = LocalDateTime.now();
        List<CpuUsage> cpuUsages = Arrays.asList(new CpuUsage(), new CpuUsage());
        when(cpuUsageService.getMinuteData(startDate, endDate)).thenReturn(cpuUsages);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/getMinuteData")
                        .param("startDate", startDate.toString())
                        .param("endDate", endDate.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    @DisplayName("시 단위 조회")
    void GetHourData() throws Exception {
        // Given
        LocalDate startDate = LocalDate.now().minusMonths(3);
        LocalDate endDate = LocalDate.now();
        List<CpuUsageForMinAvgMax> cpuUsageForMinAvgMaxList = Arrays.asList(new CpuUsageForMinAvgMax(), new CpuUsageForMinAvgMax());
        when(cpuUsageService.getHourData(startDate, endDate)).thenReturn(cpuUsageForMinAvgMaxList);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/getHourData")
                        .param("startDate", startDate.toString())
                        .param("endDate", endDate.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    @DisplayName("일 단위 조회")
    void GetDayData() throws Exception {
        // Given
        LocalDate startDate = LocalDate.now().minusYears(1);
        LocalDate endDate = LocalDate.now();
        List<CpuUsageForMinAvgMax> cpuUsageForMinAvgMaxList = Arrays.asList(new CpuUsageForMinAvgMax(), new CpuUsageForMinAvgMax());
        when(cpuUsageService.getDayData(startDate, endDate)).thenReturn(cpuUsageForMinAvgMaxList);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/getDayData")
                        .param("startDate", startDate.toString())
                        .param("endDate", endDate.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }
}
