package com.agriculture.inventory.repository;

import com.agriculture.inventory.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryItem, String> {

    Optional<InventoryItem> findByNombre(String nombre);

    @Modifying
    @Transactional
    @Query("UPDATE InventoryItem i SET i.stock = i.stock - :cantidad WHERE i.nombre = :nombreInsumo AND i.stock >= :cantidad")
    int reducirStock(String nombreInsumo, int cantidad);
}