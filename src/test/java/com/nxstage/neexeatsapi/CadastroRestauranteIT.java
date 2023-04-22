package com.nxstage.neexeatsapi;

import com.nxstage.neexeatsapi.domain.model.Kitchen;
import com.nxstage.neexeatsapi.domain.model.Restaurante;
import com.nxstage.neexeatsapi.domain.repository.KitchenRepository;
import com.nxstage.neexeatsapi.domain.repository.RestauranteRepository;
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

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroRestauranteIT {

    Restaurante restauranteJapones = new Restaurante();

    @LocalServerPort
    public int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private KitchenRepository kitchenRepository;

    public static final long RESTAURANTE_INEXISTENTE_ID = 9999L;
    public int quantidadeRestaurantesSalvos;
    public String jsonRestauranteCorreto;
    public String jsonRestauranteSemTaxaFrete;
    public String jsonRestauranteSemCozinha;
    public String jsonRestauranteCozinhaInexistente;

    @BeforeEach
    public void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurantes";

        jsonRestauranteCorreto = ResourceUtils
                .getContentFromResource("/json/correto/restaurante-sushibom-correto.json");
        jsonRestauranteSemTaxaFrete = ResourceUtils
                .getContentFromResource("/json/incorreto/restaurante-sushibom-sem-taxa-frete.json");
        jsonRestauranteSemCozinha =  ResourceUtils
                .getContentFromResource("/json/incorreto/restaurante-sushibom-sem-cozinha.json");
        jsonRestauranteCozinhaInexistente = ResourceUtils
                .getContentFromResource("/json/incorreto/restaurante-sushibom-cozinha-inexistente.json");

        databaseCleaner.clearTables();
        prepareData();
    }

    public void prepareData(){
        Kitchen japonesa = new Kitchen("Japonesa");
        japonesa = kitchenRepository.save(japonesa);

        Kitchen americana = new Kitchen("Americana");
        americana = kitchenRepository.save(americana);

        restauranteJapones = new Restaurante("Kami no Homura",new BigDecimal(10.50),japonesa);
        restauranteRepository.save(restauranteJapones);

        Restaurante bigBurger = new Restaurante("BigBurger", new BigDecimal(5), americana);
        restauranteRepository.save(bigBurger);

        quantidadeRestaurantesSalvos = (int) restauranteRepository.count();
    }

    //-------------------------------Testes------------------------------------//

    @Test
    public void shouldConterTodosRestaurantesEStatus200_whenConsultarRestaurantes(){
        RestAssured
            .given()
                .contentType(ContentType.JSON)
            .when()
                .get()
            .then()
                .body("", Matchers.hasSize(quantidadeRestaurantesSalvos))
                .statusCode(HttpStatus.OK.value());
    }
    @Test
    public void shouldRetornarStatus200_whenConsultarRestauranteExistente(){
        RestAssured
            .given()
                .pathParam("restauranteId",restauranteJapones.getId())
                .contentType(ContentType.JSON)
            .when()
                .get("/{restauranteId}")
            .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", Matchers.equalTo(restauranteJapones.getNome()));
    }

    @Test
    public void shouldRetornarStatus201_whenCadastrarRestauranteComSucesso(){

        RestAssured
            .given()
                .body(jsonRestauranteCorreto)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldRetornarStatus400_whenCadastrarRestauranteSemTaxaFrete(){
        RestAssured
                .given()
                .body(jsonRestauranteSemTaxaFrete)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldRetornarStatus400_whenCadastroRestauranteSemCozinha(){
        RestAssured
                .given()
                .body(jsonRestauranteSemCozinha)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldRetornarStatus400_whenCadastroRestauranteCozinhaInexistente(){
        RestAssured
                .given()
                .body(jsonRestauranteCozinhaInexistente)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
    @Test
    public void shouldRetornarStatusERespostaCorretos_QuandoConsultarRestauranteExistente(){
        RestAssured
                .given()
                .pathParam("restauranteId",restauranteJapones.getId())
                .contentType(ContentType.JSON)
                .when()
                .get("/{restauranteId}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", Matchers.equalTo(restauranteJapones.getNome()));
    }
    @Test
    public void shouldRetornarStatus404_QuandoConsultarRestauranteInexistente(){
        RestAssured
                .given()
                .pathParam("restauranteId",RESTAURANTE_INEXISTENTE_ID)
                .contentType(ContentType.JSON)
                .when()
                .get("/{restauranteId}")
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
