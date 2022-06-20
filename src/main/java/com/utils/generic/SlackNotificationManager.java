package com.utils.generic;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.core.util.FileUtils;
import org.json.JSONObject;

import java.io.File;

public class SlackNotificationManager {

    public final String POST_MESSAGE = "https://slack.com/api/chat.postMessage";
    public final String FILE_UPLOAD = "https://slack.com/api/files.upload";

    public String TOKEN ;

    public SlackNotificationManager(String token){

        TOKEN = token;
    }

    public JSONObject postMessageToSlack(String channelName, String message){

        Response response = RestAssured.given()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("token", TOKEN)
                .formParam("channel",channelName)
                .formParam("text",message)
                .post(POST_MESSAGE);

        JSONObject result = new JSONObject();
        result.put("statusCode", response.statusCode());
        result.put("responseMessage", response.asString());
        return result;
    }

    public void uploadFileToSlack(String filePath, String message, String channelId){

        Response response = RestAssured.given()
                .header("Authorization", "Bearer "+TOKEN)
                .multiPart("file",new File(filePath))
                .formParam("filename","ExtentReport")
                .formParam("channels",channelId)
                .formParam("initial_comment", message)
                .post(FILE_UPLOAD);
        System.out.println(response.asString());
    }

    public static void main(String args[]){

        SlackNotificationManager slackNotificationManager = new SlackNotificationManager("xoxb-3715326654864-3691539602147-7b2xTpQpUuAotlWNID2tVQ1P");
        slackNotificationManager.postMessageToSlack("team-a","Test");

        String filePath = System.getProperty("user.dir")+File.separator+"ExtentReport.html";
        slackNotificationManager.uploadFileToSlack(filePath,"This is an image of bike","C03LBS6JRGB");
    }
}
