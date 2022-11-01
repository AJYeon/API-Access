
package com.endpointCaller.impl;


@FixMethodOrder
public class EligibilityServiceTest {

    private EligibilityService eligService;

    EligibilityEndpointConfig config;

    public EligibilityServiceTest(EligibilityEndpointConfig eligibilityEndpointConfig) {
        this.config = eligibilityEndpointConfig;
    }

    private Optional<Throwable> apiKeyVerified;

    /* 
    @Test
    public void apiKeyVerificationTest() throws{
        try {
            eligService.getCommandLineEndpointResponse(String targetURL, String apiKey, String postBody)
        } catch (Throwable t) {
            apiKeyVerified = Optional.of(t);
            throw t;
        }
        // API Key 
        apiKeyVerified = Optional.empty();
    }

    @Test
    public void processAcceptRequestTest() throws JsonParseException, JsonMappingException, IO Exception {
        try{
            if (apiKeyVerified == null) {
                apiKeyVerificationTest()
            } else if (apiKeyVerified.isPresent()) {
                throw apiKeyVerified.get();
            }
        }catch (Throwable thrown) {
            throw new AssumptionViolatedException("Valid API Key required to execute test. Skipping test...", thrown);
        }
        eligService.getCommandLineEndpointResponse(String targetURL, String apiKey, String postBody)


        assertEquals();
    }
    */
    @Test
    public void processDenyRequestTest() throws JsonParseException, JsonMappingException, IOException {
        // conversion from Path to String again to Path can be optimized? 
        Path currentPath =  Paths.get(System.getProperty("user.dir"));
        Path requestFilePath = Paths.get(currentPath.toString(), "src", "test","resources","requestBodyDeny.json");
        postBody = new String(Files.readAllBytes(requestFilePath), StandardCharsets.UTF_8);
        jsonResponse = eligService.getCommandLineEndpointResponse(config.getEligibilityServiceURL, 
                                                                  config.getEligibilityServiceAPIKey,
                                                                  postBody);

        // endpoint response matches the model for deny responses 
        Path responseFilePath = Paths.get(currentPath.toString(), "src", "test","resources","sampleResponseDeny.json");
        responseDenyModel = new String(Files.readAllBytes(requestFilePath), StandardCharsets.UTF_8);
        assertEquals(responseDenyModel, jsonResponse);
    }
    /* 
    @Test
    public void processMalformedRequestTest() throws JsonParseException, JsonMappingException, IO Exception {
        
        jsonResponse = eligService.getCommandLineEndpointResponse(config.getEligibilityServiceURL, 
                                                                  config.getEligibilityServiceAPIKey,
                                                                  String postBody)
    }
    */
}