
package com.endpointcaller;


import com.endpointcaller.config.EligibilityEndpointConfig;
import com.endpointcaller.impl.EligibilityService;

import java.io.*;
import java.nio.file.*;
import java.util.Properties;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


@FixMethodOrder
public class EligibilityServiceTest {

    private EligibilityService eligService;

    private EligibilityEndpointConfig config;

    private Path currentPath;

    private Properties prop;


    @Before
    public void setUp() throws Exception {
        this.prop = new Properties();
        this.currentPath = Paths.get(System.getProperty("user.dir"));
        Path propertiesFilePath = Paths.get(currentPath.toString(), "src", "main","resources","application.properties");
        try (FileInputStream input = new FileInputStream(propertiesFilePath.toString())) {
            this.prop.load(input);
        } catch (IOException io) {
            io.printStackTrace();
        }

        this.config = new EligibilityEndpointConfig(this.prop.getProperty("loan.eligibilityservice.uri.target_url"),
                                                    this.prop.getProperty("loan.eligibilityservice.uri.api_key"));
        this.eligService = new EligibilityService();
    }
    
    
    @Test
    public void processAcceptRequestTest() throws UnsupportedEncodingException, IOException {
        String postBody = null;
        String responseAcceptModel = null;
        // conversion from Path to String again to Path can be optimized? 
        Path requestFilePath = Paths.get(currentPath.toString(), "src", "test","resources","requestBodyAccept.json");
        postBody = new String(Files.readAllBytes(requestFilePath), "utf-8");
        String jsonResponse = eligService.getCommandLineEndpointResponse(config.getEligibilityServiceURL(), 
                                                                         config.getEligibilityServiceAPIKey(),
                                                                         postBody);
        // removes referenceid from equality check and strip all whitespace                                                     
        String cleanResponse = jsonResponse.replaceAll("\"offerRefID(.*)interest", "\"interest")
                                .replaceAll("\"reference_id(.*)code", "\"code")
                                .replaceAll("\r*\n*\\s*", "")
                                .replaceAll("\"url(.*)", "");

        // endpoint response matches the model for deny responses 
        Path responseFilePath = Paths.get(currentPath.toString(), "src", "test","resources","sampleResponseAccept.json");
        responseAcceptModel = new String(Files.readAllBytes(responseFilePath), "utf-8").replaceAll("\r*\n*\\s*", "")
                                                                                .replaceAll("\"offerRefID(.*)interest", "\"interest")
                                                                                .replaceAll("\"reference_id(.*)code", "\"code")
                                                                                .replaceAll("\"url(.*)", "");
        assertEquals(responseAcceptModel, cleanResponse);
    }

    @Test
    public void processDenyRequestTest() throws UnsupportedEncodingException, IOException {
        String postBody = null;
        String responseDenyModel = null;
        // conversion from Path to String again to Path can be optimized? 
        Path requestFilePath = Paths.get(currentPath.toString(), "src", "test","resources","requestBodyDeny.json");
        postBody = new String(Files.readAllBytes(requestFilePath), "utf-8");
        String jsonResponse = eligService.getCommandLineEndpointResponse(config.getEligibilityServiceURL(), 
                                                                         config.getEligibilityServiceAPIKey(),
                                                                         postBody);
        // removes referenceid from equality check and strip all whitespace                                                     
        String cleanResponse = jsonResponse.replaceAll("\"reference_id(.*)code", "\"code")
                                .replaceAll("\r*\n*\\s*", "");

        // endpoint response matches the model for deny responses 
        Path responseFilePath = Paths.get(currentPath.toString(), "src", "test","resources","sampleResponseDeny.json");
        responseDenyModel = new String(Files.readAllBytes(responseFilePath), "utf-8").replaceAll("\r*\n*\\s*", "")
                                                                                .replaceAll("\"reference_id(.*)code", "\"code");
        assertEquals(responseDenyModel, cleanResponse);
    }
    //request has both grossMonthlyIncome and stateCode removed from the body
    @Test
    public void processMissingRequiredRequestTest() throws UnsupportedEncodingException, IOException {
        String postBody = null;
        String responseMissingModel = null;
        // conversion from Path to String again to Path can be optimized? 
        Path requestFilePath = Paths.get(currentPath.toString(), "src", "test","resources","requestBodyMissing.json");
        postBody = new String(Files.readAllBytes(requestFilePath), "utf-8");
        String jsonResponse = eligService.getCommandLineEndpointResponse(config.getEligibilityServiceURL(), 
                                                                         config.getEligibilityServiceAPIKey(),
                                                                         postBody);
        // removes referenceid from equality check and strip all whitespace                                                     
        String cleanResponse = jsonResponse.replaceAll("\"reference_id(.*)code", "\"code")
                                .replaceAll("\r*\n*\\s*", "");

        // endpoint response matches the model for deny responses 
        Path responseFilePath = Paths.get(currentPath.toString(), "src", "test","resources","sampleResponseMissDeny.json");
        responseMissingModel = new String(Files.readAllBytes(responseFilePath), "utf-8").replaceAll("\r*\n*\\s*", "")
                                                                                .replaceAll("\"reference_id(.*)code", "\"code");
        assertEquals(responseMissingModel, cleanResponse);
    }

    // The API Key is either incorrect or there is a type, handles 403 error
    @Test
    public void processMalformedKeyRequestTest() throws UnsupportedEncodingException, IOException {
        String postBody = null;
        // conversion from Path to String again to Path can be optimized? 
        Path requestFilePath = Paths.get(currentPath.toString(), "src", "test","resources","requestBodyAccept.json");
        postBody = new String(Files.readAllBytes(requestFilePath), "utf-8");
        String incorrectAPIKey = "test";
        try {
            String jsonResponse = eligService.getCommandLineEndpointResponse(config.getEligibilityServiceURL(), 
                                                                         incorrectAPIKey,
                                                                         postBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}