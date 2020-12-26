package com.sarathe.expense.config;

import com.plaid.client.PlaidClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PlaidConfig {

    @Value("${plaid_clientid}")
    private String clientId;
    @Value("${plaid_secret}")
    private String secret;
    @Autowired
    private Environment environment;

    @Bean
    public PlaidClient plaidClient(){
        String activeProfile = environment.getActiveProfiles()[0];
        if(activeProfile.equals("dev")) {
            return PlaidClient.newBuilder()
                    .clientIdAndSecret(clientId, secret)
                    .sandboxBaseUrl() // or equivalent, depending on which environment you're calling into
                    .build();
        }
        if(activeProfile.equals("prod")){
            return PlaidClient.newBuilder()
                    .clientIdAndSecret(clientId, secret)
                    .developmentBaseUrl() // or equivalent, depending on which environment you're calling into
                    .build();
        }
        return PlaidClient.newBuilder()
                .clientIdAndSecret(clientId, secret)
                .sandboxBaseUrl() // or equivalent, depending on which environment you're calling into
                .build();
    }
}
