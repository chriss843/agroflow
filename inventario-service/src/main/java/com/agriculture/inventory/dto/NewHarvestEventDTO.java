package com.agriculture.inventory.dto;

import java.util.List;

public class NewHarvestEventDTO {

    private String eventId;
    private String eventType;
    private String timestamp;
    private Payload payload;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public static class Payload {
        private String cosechaId;
        private String producto;
        private double toneladas;
        private List<String> requiereInsumos;

        public String getCosechaId() {
            return cosechaId;
        }

        public void setCosechaId(String cosechaId) {
            this.cosechaId = cosechaId;
        }

        public String getProducto() {
            return producto;
        }

        public void setProducto(String producto) {
            this.producto = producto;
        }

        public double getToneladas() {
            return toneladas;
        }

        public void setToneladas(double toneladas) {
            this.toneladas = toneladas;
        }

        public List<String> getRequiereInsumos() {
            return requiereInsumos;
        }

        public void setRequiereInsumos(List<String> requiereInsumos) {
            this.requiereInsumos = requiereInsumos;
        }
    }
}