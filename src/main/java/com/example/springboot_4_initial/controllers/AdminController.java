package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.ListEntityDTO;
import com.example.springboot_4_initial.dto.admin.UpdateAdminDTO;
import com.example.springboot_4_initial.services.interfaces.IAdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IAdminService iAdminService;

    @GetMapping("/list/admins")
    public ResponseEntity<?> list_admins(@Valid @RequestBody ListEntityDTO listEntityDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iAdminService.list_admin(listEntityDTO.getStatus()));
    }

    @GetMapping("/list/recruiter")
    public ResponseEntity<?> list_recruiter(@Valid @RequestBody ListEntityDTO listEntityDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iAdminService.list_recruiters(listEntityDTO.getStatus()));
    }

    @GetMapping("/list/candidates")
    public ResponseEntity<?> list_candidates(@Valid @RequestBody ListEntityDTO listEntityDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iAdminService.list_candidates(listEntityDTO.getStatus()));
    }

    @GetMapping("/find/{id_admin}")
    public ResponseEntity<?> find_admin(@PathVariable Long id_admin) {
        return ResponseEntity.status(HttpStatus.OK).body(iAdminService.get_admin(id_admin));
    }

    @PutMapping("/update/{id_admin}")
    public ResponseEntity<?> update_admin(@Valid @RequestBody UpdateAdminDTO updateAdminDTO, @PathVariable Long id_admin) {
        Map<String, Object> response = new HashMap<>();
        iAdminService.update_admin(updateAdminDTO, id_admin);

        response.put("status", true);
        response.put("message", "Datos actualizados correctamente");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
