package com.mefcinc.logistica.repositories;

import com.mefcinc.logistica.models.TcTipoBodega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TcTipoBodegaRepository extends JpaRepository<TcTipoBodega, Long> {

    List<TcTipoBodega> findAllByEnableOrderByDescripcionAsc(Boolean enable);
}
