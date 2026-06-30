package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.RequestListEntityDTO;
import com.example.springboot_4_initial.dto.workModality.RequestUpdateWorkModalityDTO;
import com.example.springboot_4_initial.dto.workModality.RequestWorkModalityDTO;
import com.example.springboot_4_initial.services.interfaces.IWorkModalityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/wm")
public class WorkModalityController {
    private final IWorkModalityService iWorkModalityService;

    @GetMapping("")
    public ResponseEntity<?> getAllWorkModality(@Valid @RequestBody RequestListEntityDTO requestListEntityDTO){
        return ResponseEntity.status(HttpStatus.OK).body(iWorkModalityService.getAllWorkModality(requestListEntityDTO.getStatus()));
    }

    @GetMapping("/{idWorkModality}")
    public ResponseEntity<?> getWorkModality(@PathVariable Long idWorkModality) {
        return ResponseEntity.status(HttpStatus.OK).body(iWorkModalityService.getWorkModalityById(idWorkModality));
    }

    @PostMapping("")
    public ResponseEntity<?> saveWorkModality(@Valid @RequestBody RequestWorkModalityDTO requestWorkModalityDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iWorkModalityService.createWorkModality(requestWorkModalityDTO));
    }

    @PutMapping("/{idWorkModality}")
    public ResponseEntity<?> updateWorkModality(@PathVariable Long idWorkModality, @Valid @RequestBody RequestUpdateWorkModalityDTO requestUpdateContractType) {
        return ResponseEntity.status(HttpStatus.OK).body(iWorkModalityService.updateWorkModality(requestUpdateContractType, idWorkModality));
    }

    @DeleteMapping("/{idWorkModality}")
    public ResponseEntity<?> deleteWorkModality(@PathVariable Long idWorkModality) {
        iWorkModalityService.deleteWorkModality(idWorkModality);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/change/{idWorkModality}")
    public ResponseEntity<?> changeStatus(@PathVariable Long idWorkModality) {
        iWorkModalityService.chageStatus(idWorkModality);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/destroy/{idWorkModality}")
    public ResponseEntity<?> destroyWorkModality(@PathVariable Long idWorkModality) {
        iWorkModalityService.destroyWorkModality(idWorkModality);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
