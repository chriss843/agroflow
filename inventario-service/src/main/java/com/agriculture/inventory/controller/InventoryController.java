package com.agriculture.inventory.controller;

import com.agriculture.inventory.dto.InventoryItemDTO;
import com.agriculture.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<InventoryItemDTO>> obtenerTodosLosInsumos() {
        return ResponseEntity.ok(inventoryService.obtenerTodosLosInsumos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItemDTO> obtenerInsumoPorId(@PathVariable String id) {
        return ResponseEntity.ok(inventoryService.obtenerInsumoPorId(id));
    }

    @PostMapping
    public ResponseEntity<InventoryItemDTO> crearInsumo(@RequestBody InventoryItemDTO dto) {
        return ResponseEntity.ok(inventoryService.crearInsumo(dto));
    }
}