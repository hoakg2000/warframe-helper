package com.jacky.marketservice.repository;

import com.jacky.marketservice.model.TrackingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackingItemRepository extends JpaRepository<TrackingItem, String> {
//    List<TrackingItem>
}
