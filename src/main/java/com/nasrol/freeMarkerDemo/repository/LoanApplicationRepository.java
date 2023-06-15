package com.nasrol.freeMarkerDemo.repository;

import com.nasrol.freeMarkerDemo.domain.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository extends JpaRepository<LoanApplication, Long> {
}