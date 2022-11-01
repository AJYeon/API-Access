package com.endpointcaller.impl;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.SSLHandshakeException;

public class EligibilityService {

    // method for executing POST request using command line arguments as opposed to config 
    public String getCommandLineEndpointResponse(String targetURL, String apiKey, String postBody) throws UnsupportedEncodingException, IOException, SSLHandshakeException {
        //set up query params
        URL url = new URL(targetURL);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("x-api-key", apiKey);
        conn.setDoOutput(true);

        // convert request body into JSON format
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = postBody.getBytes("utf-8");
            os.write(input, 0, input.length);			
        }

        StringBuilder response = new StringBuilder();
        try(BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine);
                }
        }
        return response.toString();
    }

    // method for executing POST request using config as opposed to command line arguments
    public String getConfigEndpointResponse(String postBody) {
        return "";
    }
}
