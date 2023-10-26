package net.binarypaper.dockercomposebluegreen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@PropertySource("classpath:META-INF/build-info.properties")
@OpenAPIDefinition(
	info = @Info(
		title = "${build.name}", 
		description = "${build.description}", 
		version = "${build.version}",
		contact = @Contact(
			name = "${build.developer.name}", 
			email = "${build.developer.email}"
		), 
		license = @License(
			name = "${build.license.name}", 
			url = "${build.license.url}"
		)
	),
	servers = {
		@Server(url = "http://localhost:8080", description = "Localhost"),
		@Server(url = "http://docker.localhost", description = "Active service when running via Traefik router"),
		@Server(url = "http://blue.localhost", description = "Blue service when running via Traefik router"),
		@Server(url = "http://green.localhost", description = "Green service when running via Traefik router")
	}
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}