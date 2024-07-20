package com.jacky.data_collector_service.repository;

import com.jacky.data_collector_service.dto.masteyprocessDto.MasteryProcessDto;
import com.jacky.data_collector_service.model.MasteryProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasteryProcessRepository extends JpaRepository<MasteryProcess, Long> {
    List<MasteryProcess> findAllByAccountId(String accountid);

    void deleteByAccountId(String accountId);
}
