package com.mefcinc.logistica.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ts_bodega")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TsBodega  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ts_bodega")
    private Long idTsBodega;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "enable")
    private Boolean enable;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_tc_tipo_bodega")
    private TcTipoBodega tcTipoBodega;
    @ManyToOne(cascade = CascadeType.ALL, fetch =  FetchType.EAGER)
    @JoinColumn(name = "id_tc_pais")
    private TcPais tcPais;

}
