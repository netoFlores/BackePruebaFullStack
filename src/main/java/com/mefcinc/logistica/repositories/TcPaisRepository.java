package com.mefcinc.logistica.repositories;

import com.mefcinc.logistica.models.TcPais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TcPaisRepository extends JpaRepository<TcPais, Long> {

    List<TcPais> findAllByEnableOrderByDescripcionAsc(Boolean enable);
}
