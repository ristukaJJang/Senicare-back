package com.korit.senicare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.senicare.entity.CareRecordEntity;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public interface CareRecordRepository extends JpaRepository<CareRecordEntity, Integer>{
    @Transactional
    void deleteByCustomerNumber(Integer customerNumber);
    List<CareRecordEntity> findByCustomerNumberOrderByRecordNumberDesc(Integer customerNumber);
}
