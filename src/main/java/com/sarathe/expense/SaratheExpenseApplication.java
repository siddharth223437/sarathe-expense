package com.sarathe.expense;

import com.sarathe.expense.domain.Roles;
import com.sarathe.expense.domain.Users;
import com.sarathe.expense.repository.RolesRepository;
import com.sarathe.expense.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@SpringBootApplication
public class SaratheExpenseApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private RolesRepository rolesRepository;


	public static void main(String[] args) {
		SpringApplication.run(SaratheExpenseApplication.class, args);
	}

	@Override
//	@Transactional
	public void run(String... args) throws Exception {
//
//		Users users = usersRepository.findUsersByUsername("sid223437");
//		users.setEnabled(true);

//		usersRepository.save(users);

//		Roles admin = rolesRepository.findRolesByRoleName("ADMIN");
//		Users users = new Users();
//		users.setEmail("sarathe@mail.usf.edu");
//		users.setFirstName("Siddharth");
//		users.setLastName("Sarathe");
//		users.setPassword(passwordEncoder.encode("Razi_2095"));
//		users.setImageUrl("some url");
//		users.setPhoneNum("8134811497");
//		users.setUsername("sid223437");
//		users.addRoles(admin);

//		usersRepository.save(users);
	}
}
