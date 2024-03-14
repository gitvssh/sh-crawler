package com.example.shcrawler.common;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;

class JasyptConfigTest {


    final static String ALGORITHM = "PBEWithMD5AndDES";

    @Test
    void stringEncryptor() throws IOException {
        // Given
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("sh_secret_pass");
        config.setAlgorithm(ALGORITHM);
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        // When
        // Then
        String input = "mongodb://shlee:1234@localhost:27017/shsystem?authSource=admin&authMechanism=SCRAM-SHA-1";
        String encrypt = encryptor.encrypt(input);
        System.out.println("암호화된 문자열 : " + encrypt);
        String origin = encryptor.decrypt(encrypt);
        assertThat(origin).isEqualTo(input);
    }

}