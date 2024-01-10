package com.pncbank.registration;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Registration microservice REST API Documentation",
				description = "PNC Bank Registration microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Prashantraj Hongal",
						email = "prashantraj.hongal@ibm.com",
						url = "https://www.contacttesturl.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.licensetesturl.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "PNC Bank Registration microservice REST API Documentation",
				url = "https://www.externaldocstesturl.com"
		)
)
public class RegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationApplication.class, args);
	}

}
