package com.example.service;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author dj
 * @date 2021-11-12 09:39
 * @description
 **/
@Slf4j
@Service
public class NlaTestService {

    private static String BASE_URL = "127.0.0.1:18891/api";

    private static void login() {
        var url = "/sys/sysLogin";
        var form = new JSONObject();
        form.set("phone", "15057571881");
        form.set("password", "15057571881");
        var data = HttpUtil.post(BASE_URL + url, form.toString());
        log.info("返回内容:{}", data);
    }

    public static void main(String[] args) {
        login();
    }

}
