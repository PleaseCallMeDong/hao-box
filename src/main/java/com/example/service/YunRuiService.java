package com.example.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: dj
 * @create: 2021-09-23 15:10
 * @description:
 **/
@Slf4j
@Service
public class YunRuiService {

    public static String token(String clientId, String clientSecret) {
        var url = "https://www.cloud-dahua.com/gateway/auth/oauth/token";
        Map<String, Object> map = new HashMap<>();
        map.put("grant_type", "client_credentials");
        map.put("scope", "server");
        map.put("client_id", clientId);
        map.put("client_secret", clientSecret);
        var data = HttpUtil.post(url, map);
        log.info("data:{}", data);
        return new JSONObject(data).getStr("access_token");
    }

    public static void messageInfo(String authorization, String messageId) {
        var url = "https://www.cloud-dahua.com/gateway/messagecenter/api/messageInfo";
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        String body = new JSONObject(map).toString();
        var data = HttpRequest.post(url).header("Content-Type","application/json")
                .header("Authorization", "Bearer 810af1ed-d7a0-4743-a24a-0b3b8ccce1dc").body(body).execute().body();
        log.info("data:{}", data);
    }

    public static void main(String[] args) {
        var clientId = "re8f385338a55d4588ae741a8c36c6c875";
        var clientSecret = "e50bc3d2ce1e9ac0d8567b8db68d180f";
        var messageId = "cb382da8-f507-4965-81c6-c18f086af95d";
//        var token = token(clientId, clientSecret);
        messageInfo(null, messageId);
    }

}
