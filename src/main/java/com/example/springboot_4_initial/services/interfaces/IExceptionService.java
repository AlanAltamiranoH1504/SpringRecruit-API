package com.example.springboot_4_initial.services.interfaces;

import java.util.Map;

public interface IExceptionService {
    public abstract Map<String, Object> generateMessage(boolean status, String message);
}
