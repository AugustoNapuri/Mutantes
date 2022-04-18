package com.meli.Mutantes;

import com.meli.Mutantes.entities.AdnCase;
import com.meli.Mutantes.service.AdnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MutantesApplication {

	@Autowired
	private static AdnService service = new AdnService();
	public static void main(String[] args) {

		SpringApplication.run(MutantesApplication.class, args);
		System.out.println(service.checkAdn(new AdnCase(new String[]{}, false)));
	}

}
