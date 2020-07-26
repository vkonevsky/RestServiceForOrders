package com.vlko.client_vaadin.service.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

@Slf4j
public abstract class ServiceUtils {

    private static final String LOGIN_URL = "http://localhost:8070/api/v1/auth/login";

    private static final CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    public static String token(String username, String password){

        JSONObject json = jsonForAuthentication(username, password);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        try {
            HttpPost request = new HttpPost(LOGIN_URL);

            StringEntity params = new StringEntity(json.toString());
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            JSONObject jsonR = new JSONObject(responseString);

            return jsonR.getString("token");

        } catch (Exception ex) {
            log.error("ERROR ServiceUtils");
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.warn("Invalid username or password ");
        return null;
    }

    private static JSONObject jsonForAuthentication(String username, String password){
        JSONObject json = new JSONObject();
        try {
            json.put("username", username);
            json.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static String getRequest(String token, String url){
        try {
            HttpGet requestUsers = new HttpGet(url);
            requestUsers.addHeader("Authorization", "Bearer_"+token);
            HttpResponse response = httpClient.execute(requestUsers);
            HttpEntity entity = response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            return responseString;
        } catch (Exception ex) {
            // handle exception here
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
