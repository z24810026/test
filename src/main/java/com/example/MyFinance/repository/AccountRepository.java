package com.example.MyFinance.repository;

import com.example.MyFinance.model.AccountRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AccountRepository extends JpaRepository<AccountRecord,Long> {
    List<AccountRecord> findByCategory(String category);
    List<AccountRecord> findByDayBetween(LocalDate start,LocalDate end);
    List<AccountRecord> findByType(String type);
    List<AccountRecord> findByNoteContaining(String keyword);
    List<AccountRecord> findAllByOrderByDayDesc();
}
