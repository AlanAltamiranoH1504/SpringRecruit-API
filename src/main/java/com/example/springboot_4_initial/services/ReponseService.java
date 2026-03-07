package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.services.interfaces.IResponseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReponseService implements IResponseService {
    @Override
    public Map<String, Object> generate_response(boolean status, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("message", message);

        return response;
    }
}
