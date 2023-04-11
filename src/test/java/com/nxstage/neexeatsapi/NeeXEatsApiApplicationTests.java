package com.nxstage.neexeatsapi;

import com.nxstage.neexeatsapi.domain.exception.CozinhaNaoEncontradaException;
import com.nxstage.neexeatsapi.domain.exception.EntidadeEmUsoException;
import com.nxstage.neexeatsapi.domain.model.Kitchen;
import com.nxstage.neexeatsapi.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintViolationException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CadastroCozinhaIT {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Test
	public void whenCadastroCozinhaComDadosCorretos_ThenDeveAtribuirId(){
		//cenário
		Kitchen kitchen =  new Kitchen();
		kitchen.setNome("Chinesa");

		//ação
		kitchen = cadastroCozinha.salvar(kitchen);

		//validação
		assertThat(kitchen).isNotNull();
		assertThat(kitchen.getId()).isNotNull();
	}

	@Test
	public void shouldFalhar_whenCozinhaSemNome(){
		Kitchen kitchen =  new Kitchen();
		kitchen.setNome(null);
		ConstraintViolationException erroEsperado =
				Assertions.assertThrows(ConstraintViolationException.class, () -> {
					cadastroCozinha.salvar(kitchen);
				});

	}

	@Test
	public void shouldFalhar_whenDeletarCozinhaInexistente(){
		Long id = 999L;
		CozinhaNaoEncontradaException erroEspesrado =
				Assertions.assertThrows(CozinhaNaoEncontradaException.class, ()->{
					cadastroCozinha.excluir(id);
				});
	}

	@Test
	public void ShouFalhar_WhenDeletarCozinhaEmUso(){
		Long id = 1L;
		EntidadeEmUsoException erroEsperado =
				Assertions.assertThrows(EntidadeEmUsoException.class, ()->
						cadastroCozinha.excluir(id));
	}

}
