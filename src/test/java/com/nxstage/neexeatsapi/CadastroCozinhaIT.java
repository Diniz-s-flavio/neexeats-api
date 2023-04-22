package com.nxstage.neexeatsapi;

import com.nxstage.neexeatsapi.domain.model.Kitchen;
import com.nxstage.neexeatsapi.domain.repository.KitchenRepository;
import com.nxstage.neexeatsapi.util.DatabaseCleaner;
import com.nxstage.neexeatsapi.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {
	public static final int COZINHA_INEXISTENTE_ID = 2000;

	public int cozinhasEmBanco;

	public String jsonCorretoCozinhaChinesa;

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private KitchenRepository kitchenRepository;

	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath="/cozinhas";

		jsonCorretoCozinhaChinesa = ResourceUtils
				.getContentFromResource("/json/correto/cozinha-chineza.json");

		databaseCleaner.clearTables();
		prepareData();
	}

	Kitchen cozinhaMexicana = new Kitchen("Mexicana");
	private void prepareData() {
		Kitchen k1 = new Kitchen("Japonesa");
		kitchenRepository.save(k1);

		kitchenRepository.save(this.cozinhaMexicana);

		cozinhasEmBanco = (int) kitchenRepository.count();
	}



	//Testes

	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinha(){
		RestAssured
			.given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value());
	}
	@Test
	public void shouldConterTodasCozinhasSalvas_whenConsultarCozinha(){
		RestAssured
			.given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.body("", Matchers.hasSize(cozinhasEmBanco));
	}

	@Test
	public void shouldRetornarStatus201_whenCadastrarCozinhaComSucesso(){
		RestAssured
			.given()
				.body(jsonCorretoCozinhaChinesa)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
			.when()
				.post()
			.then()
				.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void shouldRetornarRespostasEStatusCorretos_whenConsultarCozinhaExistente() {
		RestAssured
			.given()
				.pathParam("kitchenId", cozinhaMexicana.getId())
				.accept(ContentType.JSON)
			.when()
				.get("/{kitchenId}")
			.then()
				.statusCode(HttpStatus.OK.value())
				.body("nome",equalTo(cozinhaMexicana.getNome()));
	}

	@Test
	public void shouldRetornarStatus404_whenConsultarCozinhaInexistente() {
		RestAssured
				.given()
				.pathParam("kitchenId",COZINHA_INEXISTENTE_ID)
				.accept(ContentType.JSON)
				.when()
				.get("/{kitchenId}")
				.then()
				.statusCode(HttpStatus.NOT_FOUND.value());
	}

}
