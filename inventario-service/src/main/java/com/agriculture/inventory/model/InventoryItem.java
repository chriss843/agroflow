package com.agriculture.inventory.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "insumos")
public class InventoryItem {

    @Id
    @Column(name = "insumo_id")
    private String id;

    @Column(name = "nombre_insumo", nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private int stock;

    @Column(name = "unidad_medida")
    private String unidadMedida;

    @Column(name = "ultima_actualizacion")
    private LocalDateTime ultimaActualizacion;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public LocalDateTime getUltimaActualizacion() {
        return ultimaActualizacion;
    }

    public void setUltimaActualizacion(LocalDateTime ultimaActualizacion) {
        this.ultimaActualizacion = ultimaActualizacion;
    }
}