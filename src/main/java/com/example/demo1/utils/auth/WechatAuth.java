package com.example.demo1.utils.auth;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo1.utils.StringUtils;
import com.example.demo1.utils.http.HttpUtils;
import com.example.demo1.utils.secret.AESUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 微信授权工具类
 * @Author: LiuZW
 * @CreateDate: 2019/10/24/024 9:40
 * @Version: 1.0
 */
@Log4j2
public class WechatAuth {
    //"https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    private static String wechatAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
    //"https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
    private static String WechatUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo";
    //https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
    private static String AppletAccessTokenUrl = "https://api.weixin.qq.com/sns/jscode2session";
    // 应用标识(APPID)
    private static String APPID_Vendor = "wxc4b32c11eb8b3ddf";// vendor：商户端
    private static String APPID_User = "wxe640ee975c2ca7c1";// user：用户端
    private static String APPID_Applet = "wx62c9d762ff83c31a"; // user-applet：小程序
    private static String APPID_Platform = "wxf545b71735665418"; // user-platform：公众号
    // 应用密匙(AppSecret)
    private static String APPSecret_Vendor = "844c322e1f05b082feee7081ebdd1a1f"; // vendor：商户端
    private static String APPSecret_User = "618310a8978a44e752b803a1881370b0"; // user：用户端
    private static String APPSecret_Applet = "08f9e6f7406ca4c1eed8e7715b8ab0ec"; // user-applet：小程序
    private static String APPSecret_Platform = "1cbb547a2859770417794ce41ad97779"; // user-platform：公众号

    /**
     * @MethodName: WechatAuthTest
     * @Description: 测试
     * @Param: [code]
     * @Return: void
     * @Author: LiuZW
     * @Date: 2019/10/24/024 11:36
     **/
    public static String WechatAuthTest(String code){

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appid",APPID_User);
        requestMap.put("secret",APPSecret_User);
        requestMap.put("code",code);
        requestMap.put("grant_type","authorization_code");
        String result = HttpUtils.doGet(wechatAccessTokenUrl, requestMap);
        log.info("result:{{}}",result);
        JSONObject jsonResult = (JSONObject)JSON.parse(result);
        if(StringUtils.isNull(jsonResult.getString("errcode"))){
            String accessToken = jsonResult.getString("access_token");
            String refreshToken = jsonResult.getString("refresh_token");
            String openId = jsonResult.getString("openid");
            String unionId = jsonResult.getString("unionid");
            log.info("accessToken:{{}},refreshToken:{{}},openId:{{}},unionId:{{}}",
                    accessToken,refreshToken,openId,unionId);
            getWechatUserInfByOpenId(accessToken,openId);
        }else{
            log.error("we failed!");
        }
        return result;
    }

    /**
     * @MethodName: AppletAuthTest
     * @Description: 小程序授权测试
     * @Param: [code]
     * @Return: java.lang.String
     * @Author: LiuZW
     * @Date: 2019/10/25/025 14:52
     **/
    public static JSONObject AppletAuth(String code){

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appid",APPID_Applet);
        requestMap.put("secret",APPSecret_Applet);
        requestMap.put("js_code",code);
        requestMap.put("grant_type","authorization_code");
        String result = HttpUtils.doGet(AppletAccessTokenUrl, requestMap);
        log.info("result:{{}}",result);
        JSONObject jsonObject = (JSONObject)JSON.parse(result);
        JSONObject jsonResult = new JSONObject();
        if(StringUtils.isNull(jsonObject.getString("errcode"))){
            String sessionKey = jsonObject.getString("session_key");
            String openId = jsonObject.getString("openid");
            String unionId = jsonObject.getString("unionid");
            log.info("sessionKey:{{}},openId:{{}},unionId:{{}}",
                    sessionKey,openId,unionId);
            jsonResult.put("openId",openId);
            jsonResult.put("unionId",unionId);
            return jsonResult;
            //return new ResultJson<>(ResultStatusEnum.COMMON_SUCCESS,jsonResult);
        }else{
            log.error("小程序授权失败!");
            return jsonResult;
            //return new ResultJson(ResultStatusEnum.COMMON_FAIL,"小程序授权失败："+result);
        }
    }

    public static void main(String[] args) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appid",APPID_Applet);
        requestMap.put("secret",APPSecret_Applet);
        requestMap.put("js_code","0230TaZE13OFY70Z380F18ChZE10TaZp");
        requestMap.put("grant_type","authorization_code");
        String result = HttpUtils.doGet(AppletAccessTokenUrl, requestMap);
        log.info("result:{{}}",result);
        JSONObject jsonResult = (JSONObject)JSON.parse(result);
        if(StringUtils.isNull(jsonResult.getString("errcode"))){
            String sessionKey = jsonResult.getString("session_key");
            String openId = jsonResult.getString("openid");
            String unionId = jsonResult.getString("unionid");
            log.info("sessionKey:{{}},openId:{{}},unionId:{{}}",
                    sessionKey,openId,unionId);
            decryptWechat(sessionKey,"YS9V4qGgnGd38VLSxI0V9EjrXrx9K2dtaegq0OkEXtLTWzLWFglbeEhYozgh2ezCxD5RIXnjUagPCf7eyzo9vJPWhPuB94rPac8ZoYFiMGIE8SVHUkBmqh7YyB5xBBU5/qTo2ToVpDo/44WAXOFjBu25ddihgJdmnttNKyfPSjduOLIIYirOQZgr1fqVleJBZ9ct+3wICPuP1JGL1dxh5Vl+xQAQsCxA7KMN5U3caghc+4SyqLVthMy4A8NLXo/TErIPXHlwgfisIIh+pIIkLOikz5GquAgfzAjuMdJEd0bWgbQcjZ7cVdIEo9juEbCwvPhbd/Ug4K4HVZTv/Csurbs0fIFyvvE/HA3RQ7GsUNIQ5/2PHUyZ4AZxYRcy2pbvTBfltoB3XilKuhZPv7v3FwhevFQXwwDEROG+lYQORRAPzvPEiSezO5K22SDeiKt0u6tXcUI8lVVLVlgxUgYY5st/phdDytdf5bzHcGNF0jte6AFprjnLN2XDqw2YPvJbUJ1AMgb3KvmrLIJgQFbU7ECuNRJAPEXYs1GWc4+nqR0=",
                    "KbqJcjr4g0E15PXLGT9eqg==");
        }else{
            log.error("we failed!");
        }

        /*
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("appid",APPID_User);
        requestMap.put("secret",APPSecret_User);
        requestMap.put("code","061MD0n326JyNP0IVLm32EGdn32MD0ng");
        requestMap.put("grant_type","authorization_code");
        String result = HttpUtils.get(wechatAccessTokenUrl, requestMap);
        log.info("result:{{}}",result);
        JSONObject jsonResult = (JSONObject)JSON.parse(result);
        if(StringUtils.isNull(jsonResult.getString("errcode"))){
            String accessToken = jsonResult.getString("access_token");
            String refreshToken = jsonResult.getString("refresh_token");
            String openId = jsonResult.getString("openid");
            String unionId = jsonResult.getString("unionid");
            log.info("accessToken:{{}},refreshToken:{{}},openId:{{}},unionId:{{}}",
                    accessToken,refreshToken,openId,unionId);
            getWechatUserInfByOpenId(accessToken,openId);
        }else{
            log.error("we failed!");
        }

         */


    }

    private static void getWechatUserInfByOpenId(String accessToken, String openId) {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("access_token",accessToken);
        requestMap.put("openid",openId);
        String result = HttpUtils.doGet(WechatUserInfoUrl, requestMap);
        JSONObject jsonResult = (JSONObject)JSON.parse(result);
        log.info("jsonResult:{{}}",jsonResult);
    }

    public static JSONObject decryptWechat(String sessionKey, String encryptedData, String iv) {
        byte[] encrypted64 = Base64.decodeBase64(encryptedData);
        byte[] key64 = Base64.decodeBase64(sessionKey);
        byte[] iv64 = Base64.decodeBase64(iv);
        byte[] resultByte = AESUtils.decrypt(encrypted64, key64, iv64);
        String result = null;
        if (null != resultByte && resultByte.length > 0) {
            try {
                result = new String(resultByte, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            log.info("解密结果为：{{}}",JSONObject.parseObject(result));
        }
        return JSONObject.parseObject(result);
    }
}
