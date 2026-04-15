package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.ListEntityDTO;
import com.example.springboot_4_initial.services.interfaces.IWorkModalityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/work_modality")
public class WorkModalityController {
    @Autowired
    private IWorkModalityService iWorkModalityService;

    @PostMapping("/list")
    public ResponseEntity<?> listWorkModality(@Valid @RequestBody ListEntityDTO listEntityDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iWorkModalityService.findAll(listEntityDTO));
    }
}
