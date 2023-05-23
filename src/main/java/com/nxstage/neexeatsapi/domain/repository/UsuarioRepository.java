package com.nxstage.neexeatsapi.domain.repository;

import com.nxstage.neexeatsapi.domain.model.Grupo;
import com.nxstage.neexeatsapi.domain.model.Restaurante;
import com.nxstage.neexeatsapi.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CustomJpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByRestaurantes(Restaurante restaurante);
}
