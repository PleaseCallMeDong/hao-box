package com.example.service;

import java.io.*;
import java.net.HttpURLConnection;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * @author dj
 * @date 2021-12-04 10:29
 * @description
 **/
@Slf4j
@Service
public class IpService {

    @SneakyThrows
    public static void send() {

        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("106.15.137.58", 8089));
        HttpURLConnection connection = (HttpURLConnection) new URL("https://www.ip.cn/api/index?ip=&type=0").openConnection(proxy);
        connection.setConnectTimeout(6000);
        connection.setReadTimeout(6000);
        connection.setUseCaches(false);
        if (connection.getResponseCode() == 200) {
            //调用getInputStream()函数时才把准备好的http请求正式发送到服务器，getInputStream的返回值是InputStream
            InputStream inputStream = connection.getInputStream();
            //返回的输入流用于读取服务器对此次http请求返回的信息
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String s = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((s = bufferedReader.readLine()) != null) {
                stringBuilder.append(s);
            }
            bufferedReader.close();
            System.out.println("使用代理IP连接网络成功:" + stringBuilder);
        }
    }

    public static void main(String[] args) {
        send();
    }

}
