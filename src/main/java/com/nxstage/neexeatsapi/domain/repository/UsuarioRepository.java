package com.nxstage.neexeatsapi.domain.repository;

import com.nxstage.neexeatsapi.domain.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends CustomJpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String email);
}
