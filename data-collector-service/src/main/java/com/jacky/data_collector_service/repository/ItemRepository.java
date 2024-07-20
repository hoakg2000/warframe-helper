package com.jacky.data_collector_service.repository;

import com.jacky.data_collector_service.model.Item;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.Collection;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
    List<Item> findByCategory(String category);

    List<Item> findByCategoryIn(List<String> categories);

    List<Item> findByCategoryInAndTypeNotIn(List<String> categories, List<String> types);

    List<Item> findByNameLike(String name, PageRequest pageable);
}
