package com.example.springboot_4_initial.services.interfaces;

public interface ICryptoService {
    public abstract String encrypt(String data);
    public abstract Long decrypt(String data);
}
