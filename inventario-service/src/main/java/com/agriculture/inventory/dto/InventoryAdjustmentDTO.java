package com.agriculture.inventory.dto;

public class InventoryAdjustmentDTO {

    private String insumoId;
    private String nombreInsumo;
    private int cantidadAjustada;
    private int nuevoStock;

    // Getters y Setters
    public String getInsumoId() {
        return insumoId;
    }

    public void setInsumoId(String insumoId) {
        this.insumoId = insumoId;
    }

    public String getNombreInsumo() {
        return nombreInsumo;
    }

    public void setNombreInsumo(String nombreInsumo) {
        this.nombreInsumo = nombreInsumo;
    }

    public int getCantidadAjustada() {
        return cantidadAjustada;
    }

    public void setCantidadAjustada(int cantidadAjustada) {
        this.cantidadAjustada = cantidadAjustada;
    }

    public int getNuevoStock() {
        return nuevoStock;
    }

    public void setNuevoStock(int nuevoStock) {
        this.nuevoStock = nuevoStock;
    }
}