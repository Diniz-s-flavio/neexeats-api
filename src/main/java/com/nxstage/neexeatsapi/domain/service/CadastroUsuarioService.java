package com.nxstage.neexeatsapi.domain.service;

import com.nxstage.neexeatsapi.domain.exception.NegocioException;
import com.nxstage.neexeatsapi.domain.exception.UsuarioNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.model.Grupo;
import com.nxstage.neexeatsapi.domain.model.Restaurante;
import com.nxstage.neexeatsapi.domain.model.Usuario;
import com.nxstage.neexeatsapi.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CadastroGrupoService cadastroGrupo;

    public List<Usuario> findByRestaurantes(Restaurante restaurante){
        return usuarioRepository.findByRestaurantes(restaurante);
    }

    public Usuario buscarOuFalhar(Long id){
        return usuarioRepository.findById(id).orElseThrow(()->
                new UsuarioNaoEncontradaException(id));
    }

    @Transactional
    public Usuario salvar(Usuario usuario){
        Optional<Usuario> usuarioExistente = usuarioRepository
                .findByEmail(usuario.getEmail());
        usuarioRepository.detach(usuario);
        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)){
            throw new NegocioException(
                    String.format("Já existe um usuário cadastrado com o email: %s",usuario.getEmail())
            );
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void alterarSenha(Long id, String senhaAtual, String novaSenha){
        Usuario usuario =  buscarOuFalhar(id);

        if (!usuario.getSenha().equals(senhaAtual)){
            throw new NegocioException("A senha Atual informada não  confere com a senha do usuario");
        }
        usuario.setSenha(novaSenha);
    }


    @Transactional
    public void linkGrupo(Long usuarioId, Long grupoId){
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

        usuario.linkGrupo(grupo);
    }
    @Transactional
    public void unlinkGrupo(Long usuarioId, Long grupoId){
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

        usuario.unlinkGrupo(grupo);
    }
}
