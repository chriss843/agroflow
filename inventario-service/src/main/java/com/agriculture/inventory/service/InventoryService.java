package com.agriculture.inventory.service;

import com.agriculture.inventory.dto.InventoryAdjustmentDTO;
import com.agriculture.inventory.dto.InventoryItemDTO;
import com.agriculture.inventory.model.InventoryItem;
import com.agriculture.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public InventoryAdjustmentDTO ajustarStock(String nombreInsumo, int cantidad) {
        int filasAfectadas = inventoryRepository.reducirStock(nombreInsumo, cantidad);

        if (filasAfectadas == 0) {
            throw new RuntimeException("No hay suficiente stock o el insumo no existe");
        }

        InventoryItem item = inventoryRepository.findByNombre(nombreInsumo)
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado después de actualizar"));

        InventoryAdjustmentDTO ajuste = new InventoryAdjustmentDTO();
        ajuste.setInsumoId(item.getId());
        ajuste.setNombreInsumo(item.getNombre());
        ajuste.setCantidadAjustada(cantidad);
        ajuste.setNuevoStock(item.getStock());

        return ajuste;
    }

    public List<InventoryItemDTO> obtenerTodosLosInsumos() {
        return inventoryRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public InventoryItemDTO obtenerInsumoPorId(String id) {
        InventoryItem item = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));
        return convertirADTO(item);
    }

    public InventoryItemDTO crearInsumo(InventoryItemDTO dto) {
        InventoryItem item = new InventoryItem();
        item.setId(dto.getId());
        item.setNombre(dto.getNombre());
        item.setStock(dto.getStock());
        item.setUnidadMedida(dto.getUnidadMedida());
        item.setUltimaActualizacion(LocalDateTime.now());

        InventoryItem guardado = inventoryRepository.save(item);
        return convertirADTO(guardado);
    }

    private InventoryItemDTO convertirADTO(InventoryItem item) {
        InventoryItemDTO dto = new InventoryItemDTO();
        dto.setId(item.getId());
        dto.setNombre(item.getNombre());
        dto.setStock(item.getStock());
        dto.setUnidadMedida(item.getUnidadMedida());
        return dto;
    }
}