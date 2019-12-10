package com.example.demo1.utils.secret;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;

public class AESUtils {

    //加密方式
    public static String KEY_ALGORITHM = "AES";
    //避免重复new生成多个BouncyCastleProvider对象，因为GC回收不了，会造成内存溢出
    //只在第一次调用decrypt()方法时才new 对象
    public static boolean initialized = false;

    /**
     * @param originalContent
     * @param encryptKey
     * @param ivByte
     * @return
     */
    public byte[] encrypt(byte[] originalContent, byte[] encryptKey, byte[] ivByte) {
        initialize();
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec skeySpec = new SecretKeySpec(encryptKey, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(ivByte));
            byte[] encrypted = cipher.doFinal(originalContent);
            return encrypted;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
    encryptedData: "p+TX9U3dGW22bjyzJgRLnd37PF628QaKK5O7UM0jVqOwZXKZnzSjhFQvsRHO/IIDFLYa5msNAHMyBS+MXxSHZTdg78Xk0dycL56nIsV3usfPiRD3PYtB90xj7ZZo/+f6khQZYVz/pupG7D3dlYfA83PlKgivgOtVISna7VcglXa9GrTucWvrLNc0ou8XTixEb+ntep4D3Kix3A6d2063Cw=="
    errMsg: "getPhoneNumber:ok"
    iv: "bWAvKZVygdp7Zdv7KY69ng=="
     */
    public static void main(String[] args) {
        String sessionKey = "BAKJyo3L7kOCkxMyR317Qg==";//getSessionKey("033JiVkn17hswp0jcjln1C9Fkn1JiVkE");
        System.out.println(sessionKey);//BAKJyo3L7kOCkxMyR317Qg==
        String encryptedData = "p+TX9U3dGW22bjyzJgRLnd37PF628QaKK5O7UM0jVqOwZXKZnzSjhFQvsRHO/IIDFLYa5msNAHMyBS+MXxSHZTdg78Xk0dycL56nIsV3usfPiRD3PYtB90xj7ZZo/+f6khQZYVz/pupG7D3dlYfA83PlKgivgOtVISna7VcglXa9GrTucWvrLNc0ou8XTixEb+ntep4D3Kix3A6d2063Cw==";
        String iv = "bWAvKZVygdp7Zdv7KY69ng==";
        byte[] encrypted64 = Base64.decodeBase64(encryptedData);
        byte[] key64 = Base64.decodeBase64(sessionKey);
        byte[] iv64 = Base64.decodeBase64(iv);
        byte[] resultByte = decrypt(encrypted64, key64, iv64);
        System.out.println(resultByte.toString());
        if (null != resultByte && resultByte.length > 0) {
            String result = null;
            try {
                result = new String(resultByte, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println(JSONObject.parseObject(result));
        }

    }


    /**
     * AES解密
     * 填充模式AES/CBC/PKCS7Padding
     * 解密模式128
     *
     * @param content 目标密文
     * @return
     * @throws Exception
     * @throws InvalidKeyException
     * @throws NoSuchProviderException
     */
    public static byte[] decrypt(byte[] content, byte[] aesKey, byte[] ivByte) {
        initialize();
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key sKeySpec = new SecretKeySpec(aesKey, "AES");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * BouncyCastle作为安全提供，防止我们加密解密时候因为jdk内置的不支持改模式运行报错。
     **/
    public static void initialize() {
        if (initialized)
            return;
        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }

    // 生成iv
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception {
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }
}
