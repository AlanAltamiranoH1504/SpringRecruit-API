package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.services.interfaces.ICryptoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class CryptoService implements ICryptoService {
    @Value("${secret.key.offocus}")
    private String SECRET_KEY;

    @Override
    public String encrypt(String data) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(data.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long decrypt(String data) {
        try {
            SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decode = Base64.getDecoder().decode(data);

            return Long.parseLong(new String(cipher.doFinal(decode)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
