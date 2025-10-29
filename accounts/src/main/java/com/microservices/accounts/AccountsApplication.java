package com.microservices.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@OpenAPIDefinition(info = @Info(title = "Accounts API", version = "1.0", description = "Accounts API"))
public class AccountsApplication {

    static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

//	@Bean
//	public CommandLineRunner commandLineRunner(AccountsService accountsService) {
//		return args -> {
//			accountsService.createAccount(CustomerDto.builder()
//					.name("Pavel")
//					.mobileNumber("Bukharov")
//					.email("pbukharov@gmail.com")
//					.build());
//		};
//	}
}
