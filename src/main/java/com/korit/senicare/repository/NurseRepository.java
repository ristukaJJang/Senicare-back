package com.korit.senicare.repository;

// db 테이블에 실제 값이 존재하는지 등 수행을 하는 메소드를 선언하는 곳

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.korit.senicare.entity.NurseEntity;

@Repository
public interface NurseRepository extends JpaRepository<NurseEntity, String>{
    
    boolean existsByUserId(String userId);

    boolean existsByTelNumber(String telNumber);

    NurseEntity findByUserId(String userId);
}
