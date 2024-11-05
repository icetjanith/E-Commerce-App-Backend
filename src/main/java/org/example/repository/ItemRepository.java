package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity,Integer> {
    List<ItemEntity> findByIsDisabledFalse();
    ItemEntity findByItemCode(String itemCode);
}
