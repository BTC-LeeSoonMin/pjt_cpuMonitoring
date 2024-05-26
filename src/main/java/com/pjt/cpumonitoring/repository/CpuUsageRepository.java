package com.pjt.cpumonitoring.repository;

import com.pjt.cpumonitoring.dto.CpuUsageForMinAvgMax;
import com.pjt.cpumonitoring.entity.CpuUsage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CpuUsageRepository extends CrudRepository<CpuUsage, Long> {
    List<CpuUsage> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);


    @Query("SELECT NEW com.pjt.cpumonitoring.dto.CpuUsageForMinAvgMax(MIN(c.cpuLoad), AVG(c.cpuLoad), MAX(c.cpuLoad)) " +
            "FROM CpuUsage c " +
            "WHERE c.timestamp BETWEEN :startDate AND :endDate " +
            "GROUP BY HOUR(c.timestamp)")
    List<CpuUsageForMinAvgMax> findCpuUsageStatsPerHour(@Param("startDate") LocalDateTime startOfDay,
                                                        @Param("endDate") LocalDateTime endOfDay);


    @Query("SELECT NEW com.pjt.cpumonitoring.dto.CpuUsageForMinAvgMax(MIN(c.cpuLoad), AVG(c.cpuLoad), MAX(c.cpuLoad)) " +
            "FROM CpuUsage c " +
            "WHERE c.timestamp BETWEEN :startDate AND :endDate " +
            "GROUP BY DAY(c.timestamp)")
    List<CpuUsageForMinAvgMax> findCpuUsageStatsPerDay(@Param("startDate") LocalDateTime startOfDay,
                                                       @Param("endDate") LocalDateTime endOfDay);
}
