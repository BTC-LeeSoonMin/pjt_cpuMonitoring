package com.pjt.cpumonitoring.repository;

import com.pjt.cpumonitoring.entity.CpuUsage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CpuUsageRepository extends CrudRepository<CpuUsage, Long> {
    List<CpuUsage> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);
}
