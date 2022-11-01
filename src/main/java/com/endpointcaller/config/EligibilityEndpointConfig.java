package com.endpointcaller.config;

public class EligibilityEndpointConfig {


    public EligibilityEndpointConfig(String url, String key) {
        this.eligibilityServiceURL = url;
        this.eligibilityServiceAPIKey = key;
    }
    
    //@Value("${loan.eligibilityservice.uri.target}")
    private String eligibilityServiceTarget;
    
    //@Value("${loan.eligibilityservice.uri.path}")
    private String eligibilityServicePath;

    //@Value("${loan.eligibilityservice.uri.version}")
    private String eligibilityServiceVersion;

    private String eligibilityServiceURL;

    private String eligibilityServiceAPIKey;

    public String getEligibilityServiceTarget() {
        return eligibilityServiceTarget;
    }

    public String getEligibilityServicePath() {
        return eligibilityServicePath;
    }

    public String getEligibilityServiceVersion() {
        return eligibilityServiceVersion;
    }

    public String getEligibilityServiceURL() {
        return eligibilityServiceURL;
    }

    public String getEligibilityServiceAPIKey() {
        return eligibilityServiceAPIKey;
    }

    public void setEligibilityServiceURL(String eligibilityServiceURL) {
        this.eligibilityServiceURL = eligibilityServiceURL;
    }

    public void setEligibilityServiceAPIKey(String eligibilityServiceAPIKey) {
        this.eligibilityServiceAPIKey = eligibilityServiceAPIKey;
    }
}