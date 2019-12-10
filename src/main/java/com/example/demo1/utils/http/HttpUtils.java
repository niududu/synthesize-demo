package com.example.demo1.utils.http;

import lombok.extern.log4j.Log4j2;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: HTTP请求工具类
 * @Author: LiuZW
 * @CreateDate: 2019/12/4/004 10:12
 * @Version: 1.0
 */
@Log4j2
public class HttpUtils {

    private static final String DEFAULT_CHARSET = "UTF-8";

    private static final int CONNECT_TIME_OUT = 5000; //链接超时时间3秒

    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom().setConnectTimeout(CONNECT_TIME_OUT).build();

    public static void main(String[] args) {
        /*String s = doGet("https://api.ariesex.com/api/evs/balance/" + "4359984D5527E999FD0A5DD89DC2C2D3");
        log.info(s);*/
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name","123456");
        hashMap.put("age","66");
        String s = doPostForForm("www.baidu.com/", hashMap);
        log.info(s);
    }


    /**
     * @description 功能描述: get 请求
     * @param url 请求地址
     * @param params 参数
     * @param headers headers参数
     * @return 请求失败返回null
     */
    public static String doGet(String url, Map<String, String> params, Map<String, String> headers) {

        CloseableHttpClient httpClient = null;
        if (params != null && !params.isEmpty()) {
            StringBuffer param = new StringBuffer();
            boolean flag = true; // 是否开始
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (flag) {
                    param.append("?");
                    flag = false;
                } else {
                    param.append("&");
                }
                param.append(entry.getKey()).append("=");
                try {
                    param.append(URLEncoder.encode(entry.getValue(), DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    //编码失败
                    log.info("编码失败!");
                }
            }
            url += param.toString();
        }
        String body = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.custom().setDefaultRequestConfig(REQUEST_CONFIG).build();
            HttpGet httpGet = new HttpGet(url);
            if (headers != null && !headers.isEmpty()) {
                headers.forEach((k,v)->{
                    log.info("key:value->{{}}:{{}}",k,v);
                    httpGet.addHeader(k,v);
                });
            }
            response = httpClient.execute(httpGet);
            body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return body;
    }

    /**
     * @description 功能描述: get 请求
     * @param url 请求地址
     * @return 请求失败返回null
     */
    public static String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * @description 功能描述: get 请求
     * @param url 请求地址
     * @param params 参数
     * @return 请求失败返回null
     */
    public static String doGet(String url, Map<String, String> params) {
        return doGet(url, params, null);
    }

    /**
     * @description 功能描述: post 请求
     * @param url 请求地址
     * @param params 参数
     * @return 请求失败返回null
     */
    public static String doPost(String url, Map<String, String> params) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        String body = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.custom().setDefaultRequestConfig(REQUEST_CONFIG).build();
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, DEFAULT_CHARSET));
            response = httpClient.execute(httpPost);
            body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return body;
    }

    /**
     * @description 功能描述: POST请求（表单请求）
     * @param url 请求地址
     * @param params 参数
     * @return 请求失败返回null
     */
    public static String doPostForForm(String url, Map<String, String> params) {

        CloseableHttpClient httpClient = null;
        if (params != null && !params.isEmpty()) {
            StringBuffer param = new StringBuffer();
            boolean flag = true; // 是否开始
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (flag) {
                    param.append("?");
                    flag = false;
                } else {
                    param.append("&");
                }
                param.append(entry.getKey()).append("=");
                try {
                    param.append(URLEncoder.encode(entry.getValue(), DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    //编码失败
                    log.info("编码失败!");
                }
            }
            url += param.toString();
        }
        String body = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.custom().setDefaultRequestConfig(REQUEST_CONFIG).build();
            log.info("表单请求的url为：{{}}",url);
            HttpPost httpPost = new HttpPost(url);
            response = httpClient.execute(httpPost);
            body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return body;
    }

    /**
     * @description 功能描述: post 请求
     * @param url 请求地址
     * @param xml 参数xml（可通过XML解析工具类解析得）
     * @return 请求失败返回null
     */
    public static String doPostForXml(String url, String xml) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = new HttpPost(url);
        String body = null;
        CloseableHttpResponse response = null;
        try {
            httpClient = HttpClients.custom().setDefaultRequestConfig(REQUEST_CONFIG).build();
            httpPost.setEntity(new StringEntity(xml, DEFAULT_CHARSET));
            response = httpClient.execute(httpPost);
            body = EntityUtils.toString(response.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return body;
    }



}
