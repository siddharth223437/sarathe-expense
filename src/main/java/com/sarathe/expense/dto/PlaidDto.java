package com.sarathe.expense.dto;

import java.util.ArrayList;
import java.util.List;

public class PlaidDto {

    private String appName;
    private List<String> products = new ArrayList<>();
    private Credentials credentials;
    private Institution institution;
    private List<Account> accounts;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public static class Credentials {
        private String clientId;
        private String secret;
        private String accessToken;
        private String publicLinkToken;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getPublicLinkToken() {
            return publicLinkToken;
        }

        public void setPublicLinkToken(String publicLinkToken) {
            this.publicLinkToken = publicLinkToken;
        }
    }

    public static class Institution {
        private String instId;
        private String plaidInstitutionId;
        private String institutionName;

        public String getInstId() {
            return instId;
        }

        public void setInstId(String instId) {
            this.instId = instId;
        }

        public String getInstitutionName() {
            return institutionName;
        }

        public void setInstitutionName(String institutionName) {
            this.institutionName = institutionName;
        }

        public String getPlaidInstitutionId() {
            return plaidInstitutionId;
        }

        public void setPlaidInstitutionId(String plaidInstitutionId) {
            this.plaidInstitutionId = plaidInstitutionId;
        }
    }

    public static class Account {
        private String name;
        private String type;
        private String subtype;
        private String plaidAccountId;
        private String officialName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getPlaidAccountId() {
            return plaidAccountId;
        }

        public void setPlaidAccountId(String plaidAccountId) {
            this.plaidAccountId = plaidAccountId;
        }

        public String getOfficialName() {
            return officialName;
        }

        public void setOfficialName(String officialName) {
            this.officialName = officialName;
        }
    }
}
