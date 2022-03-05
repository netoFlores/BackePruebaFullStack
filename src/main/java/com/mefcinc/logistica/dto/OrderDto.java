package com.mefcinc.logistica.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto implements Serializable {

    private Long idTtOrderLogistica;
    private Integer cantidadProducto;
    private String fechaRegistro;
    private String fechaEntrega;
    private BigDecimal precioEnvio;
    private BigDecimal precioDescuento;
    private Integer porcentajeDescuento;
    private String placa;
    private String numeroGuia;
    private Long tsBodega;
    private Long tsUsuario;
    private Long tcTipoProducto;
}
