package com.nxstage.neexeatsapi;

import com.nxstage.neexeatsapi.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class NeeXEatsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeeXEatsApiApplication.class, args);
	}

}
