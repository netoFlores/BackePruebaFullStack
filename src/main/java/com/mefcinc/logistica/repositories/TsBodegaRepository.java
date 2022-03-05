package com.mefcinc.logistica.repositories;

import com.mefcinc.logistica.models.TcPais;
import com.mefcinc.logistica.models.TcTipoBodega;
import com.mefcinc.logistica.models.TsBodega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TsBodegaRepository extends JpaRepository<TsBodega, Long> {

    List findAllByEnableAndTcPaisAndTcTipoBodegaOrderByDescripcionAsc(Boolean enable, TcPais tcPais , TcTipoBodega tcTipoBodega);
}
