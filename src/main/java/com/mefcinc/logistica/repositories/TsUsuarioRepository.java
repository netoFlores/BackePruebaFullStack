package com.mefcinc.logistica.repositories;

import com.mefcinc.logistica.models.TsUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TsUsuarioRepository extends JpaRepository<TsUsuario, Long> {

    TsUsuario findByEnableAndEmail(Boolean enable, String email);
}
