package com.example.springboot_4_initial.services.interfaces;

import java.util.Map;

public interface IResponseService {
    public Map<String, Object> generate_response(boolean status, String message);
}
