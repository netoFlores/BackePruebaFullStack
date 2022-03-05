package com.mefcinc.logistica.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tt_order_logistica")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TtOrderLogistica  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tt_order_logistica")
    private Long idTtOrderLogistica;
    @Column(name = "cantidad_producto")
    private Integer cantidadProducto;
    @Column(name = "fecha_registro")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaRegistro;
    @Column(name = "fecha_entrega")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaEntrega;
    @Column(name = "precio_envio")
    private BigDecimal precioEnvio;
    @Column(name = "precio_descuento")
    private BigDecimal precioDescuento;
    @Column(name = "porcentaje_descuento")
    private Integer porcentajeDescuento;
    @Column(name = "placa")
    private String placa;
    @Column(name = "numero_guia")
    private String numeroGuia;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ts_bodega")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TsBodega tsBodega;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ts_usuario")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TsUsuario tsUsuario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tc_tipo_producto")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TcTipoProducto tcTipoProducto;

}
