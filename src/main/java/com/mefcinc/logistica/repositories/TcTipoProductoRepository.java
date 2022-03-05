package com.mefcinc.logistica.repositories;

import com.mefcinc.logistica.models.TcTipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TcTipoProductoRepository extends JpaRepository<TcTipoProducto, Long> {

    List<TcTipoProducto> findAllByEnableOrderByDescripcionAsc(Boolean enable);
}
