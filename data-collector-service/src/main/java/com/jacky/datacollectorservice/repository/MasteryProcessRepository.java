package com.jacky.datacollectorservice.repository;

import com.jacky.datacollectorservice.dto.masteyprocessDto.MasteryProcessDto;
import com.jacky.datacollectorservice.model.MasteryProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasteryProcessRepository extends JpaRepository<MasteryProcess, Long> {
    List<MasteryProcess> findAllByAccountId(String accountid);

    void deleteByAccountId(String accountId);
}
