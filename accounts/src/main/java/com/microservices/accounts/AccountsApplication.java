package com.microservices.accounts;

import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.service.AccountsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class AccountsApplication {

	public static void main(String[] args) {
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
