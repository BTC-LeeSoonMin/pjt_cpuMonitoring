package com.pjt.cpumonitoring.repository;

import com.pjt.cpumonitoring.entity.CpuUsage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuUsageRepository extends CrudRepository<CpuUsage, Long> {
}
