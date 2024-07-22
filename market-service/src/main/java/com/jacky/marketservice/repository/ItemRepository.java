package com.jacky.marketservice.repository;

import com.jacky.marketservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    List<Item> findByItemNameIgnoreCaseContaining(String name);

}
