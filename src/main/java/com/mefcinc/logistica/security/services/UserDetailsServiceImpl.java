package com.mefcinc.logistica.security.services;

import com.mefcinc.logistica.models.TsUsuario;
import com.mefcinc.logistica.repositories.TsUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private TsUsuarioRepository tsUsuarioRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TsUsuario user = tsUsuarioRepository.findByEnableAndEmail(true, username);
        return UserDetailsImpl.build(user);
    }
}
