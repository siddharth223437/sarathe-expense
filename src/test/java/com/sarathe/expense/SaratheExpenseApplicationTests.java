package com.sarathe.expense;

import com.plaid.client.PlaidClient;
import com.plaid.client.request.TransactionsGetRequest;
import com.plaid.client.response.TransactionsGetResponse;
import com.sarathe.expense.domain.Plaid;
import com.sarathe.expense.domain.Users;
import com.sarathe.expense.repository.UsersRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import retrofit2.Response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class SaratheExpenseApplicationTests {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private PlaidClient plaidClient;
	@Autowired
	private UsersRepository usersRepository;

	private String clientId;
	@Value("${plaid.clientId}")

	@Test
	void contextLoads() throws Exception {

	}

}
