package com.sarathe.expense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class SaratheExpenseApplication  {

	public static void main(String[] args) {
		SpringApplication.run(SaratheExpenseApplication.class, args);
	}

}
