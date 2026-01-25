package com.ipnet.FinanceApp.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ipnet.FinanceApp.security.model.History;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findAllByOrderByDateHistoryDesc();
}
