package com.nxstage.neexeatsapi.domain.service;

import com.nxstage.neexeatsapi.domain.exception.NegocioException;
import com.nxstage.neexeatsapi.domain.exception.UsuarioNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.model.Usuario;
import com.nxstage.neexeatsapi.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarOuFalhar(Long id){
        return usuarioRepository.findById(id).orElseThrow(()->
                new UsuarioNaoEncontradaException(id));
    }

    @Transactional
    public Usuario salvar(Usuario usuario){
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
}
