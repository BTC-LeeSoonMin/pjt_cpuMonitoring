package com.pjt.cpumonitoring.repository;

import com.pjt.cpumonitoring.entity.ErrorLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorLogRepository extends CrudRepository<ErrorLog, Long> {
}
