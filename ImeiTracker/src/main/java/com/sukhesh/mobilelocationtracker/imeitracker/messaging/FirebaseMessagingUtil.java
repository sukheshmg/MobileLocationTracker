package com.sukhesh.mobilelocationtracker.imeitracker.messaging;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sukhesh.mobilelocationtracker.imeitracker.exception.MessageSendFailedException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by sukhesh on 30/09/16.
 */
public class FirebaseMessagingUtil {
    public static void sendMessage(Message message) throws MessageSendFailedException{
        String url = "https://fcm.googleapis.com/fcm/send";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);

        request.addHeader("Authorization", "key=AIzaSyCnCgzEi2xENIk73lTobDyYp_Cp4OsRhXg");
        request.addHeader("Content-Type", "application/json");

        Gson gson = new Gson();
        String body = gson.toJson(message);

        HttpEntity entity = new ByteArrayEntity(body.getBytes());

        request.setEntity(entity);

        HttpResponse response = null;
        try {
            response = httpClient.execute(request);
        } catch (IOException e) {
            throw new MessageSendFailedException(e);
        }

        HttpEntity responseEntity = response.getEntity();

        InputStream is = null;
        try {
            is = responseEntity.getContent();
        } catch (IOException e) {
            throw new MessageSendFailedException(e);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new MessageSendFailedException(e);
        }
    }

    public static void main(String[] args) throws MessageSendFailedException {
        Message message = new Message("123", "testmessage", "f71svfztGFw:APA91bFDsk2frTBv117vx2vYTCKnMZHsWICHyGXFff__3SViModj2uvtjc8cbc206Q83U_bqC1-fG__4FKkainows9qVsCsQsfrUN1mXVGObbD09DUm9U8i_cYXn8Uv4ARyKXkslxURs");
        FirebaseMessagingUtil.sendMessage(message);
    }
}
