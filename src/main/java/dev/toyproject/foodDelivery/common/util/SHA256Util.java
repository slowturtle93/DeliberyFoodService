package dev.toyproject.foodDelivery.common.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 암호화 -> SHA-256
 */
@Slf4j
public class SHA256Util {

    private static final String ENCRYPTION_TYPE = "SHA-256";

    /**
     * 암호화 키 발행
     *
     *  NoSuchAlgorithmException : 잘못된 알고리즘을 입력하여 키를 생성할 경우 발생
     *  개발시 키 생성을 정상적으로 할 수 있다면 발생하지 않는 Exception 이므로 RuntimeException 으로 사용
     *
     * @param str
     * @return
     */
    public static String encryptSHA256(String str) {
        String SHA = null;

        MessageDigest sh;
        try {
            sh = MessageDigest.getInstance(ENCRYPTION_TYPE);
            sh.update(str.getBytes());
            byte[] byteData = sh.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            SHA = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("암호화 에러! SHA256Util 확인 필요 ", e);
        }
        return SHA;
    }

}
