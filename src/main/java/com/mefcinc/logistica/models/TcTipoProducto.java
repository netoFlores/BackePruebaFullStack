package com.mefcinc.logistica.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tc_tipo_producto")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TcTipoProducto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tc_tipo_producto")
    private Long idTcTipoProducto;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "enable")
    private Boolean enable;
}
