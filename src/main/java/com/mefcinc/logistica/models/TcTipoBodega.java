package com.mefcinc.logistica.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tc_tipo_bodega")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TcTipoBodega  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tc_tipo_bodega")
    private Long idTcTipoBodega;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "enable", length = 1)
    private Boolean enable;
}
