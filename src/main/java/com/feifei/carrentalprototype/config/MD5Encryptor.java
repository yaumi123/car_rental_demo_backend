package com.feifei.carrentalprototype.config;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;

@Configurable
public class MD5Encryptor {
    @Value("${encrypt.md5}")
    String key;

    public static String encrypt(String text) {
        return DigestUtils.md5Hex(text);
    }
}
