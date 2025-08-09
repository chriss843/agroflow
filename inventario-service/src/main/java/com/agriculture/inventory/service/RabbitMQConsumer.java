package com.agriculture.inventory.service;

import com.agriculture.inventory.config.RabbitMQConfig;
import com.agriculture.inventory.dto.NewHarvestEventDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private final InventoryService inventoryService;
    private final ObjectMapper objectMapper;

    @Autowired
    public RabbitMQConsumer(InventoryService inventoryService, ObjectMapper objectMapper) {
        this.inventoryService = inventoryService;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_INVENTARIO)
    public void recibirMensajeCosecha(String mensaje) {
        try {
            NewHarvestEventDTO evento = objectMapper.readValue(mensaje, NewHarvestEventDTO.class);

            if ("nueva_cosecha".equals(evento.getEventType())) {
                procesarNuevaCosecha(evento);
            }
        } catch (Exception e) {
            System.err.println("Error procesando mensaje de cosecha: " + e.getMessage());
        }
    }

    private void procesarNuevaCosecha(NewHarvestEventDTO evento) {
        double toneladas = evento.getPayload().getToneladas();

        // Calcular insumos necesarios según las fórmulas del documento
        if (evento.getPayload().getRequiereInsumos().contains("Semilla Arroz L-23")) {
            double semillaNecesaria = toneladas * 5; // 5kg por tonelada
            inventoryService.ajustarStock("Semilla Arroz L-23", (int) semillaNecesaria);
        }

        if (evento.getPayload().getRequiereInsumos().contains("Fertilizante N-PK")) {
            double fertilizanteNecesario = toneladas * 2; // 2kg por tonelada
            inventoryService.ajustarStock("Fertilizante N-PK", (int) fertilizanteNecesario);
        }

        // Aquí deberías publicar el evento "inventario_ajustado"
        System.out.println("Inventario ajustado para cosecha: " + evento.getPayload().getCosechaId());
    }
}