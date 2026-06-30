package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.services.interfaces.IExceptionService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExceptionService implements IExceptionService {
    @Override
    public Map<String, Object> generateMessage(boolean status, String message) {
        Map<String, Object> json = new HashMap<>();
        json.put("status", status);
        json.put("message", message);
        return json;
    }
}
