package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.application.CreateApplicationDTO;
import com.example.springboot_4_initial.dto.application.UpdateApplicationDTO;
import com.example.springboot_4_initial.services.interfaces.IApplicationService;
import com.example.springboot_4_initial.services.interfaces.IResponseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/application")
public class ApplicationController {
    @Autowired
    private IApplicationService iApplicationService;
    @Autowired
    private IResponseService iResponseService;

    @GetMapping("/list/by_candidate/{idCandidateCrypt}")
    public ResponseEntity<?> listApplicationByCandidate(@PathVariable String idCandidateCrypt) {
        return ResponseEntity.status(HttpStatus.OK).body(iApplicationService.listByIdCandidate(idCandidateCrypt));
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveApplication(
            @RequestPart("file") MultipartFile cv,
            @RequestPart("data") @Valid CreateApplicationDTO createApplicationDTO
    ) throws IOException {
        iApplicationService.saveApplication(createApplicationDTO, cv);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                iResponseService.generate_response(true, "Aplicación a vacante realizada de manera correcta")
        );
    }

    @GetMapping("/find/{idApplication}")
    public ResponseEntity<?> findApplicationById(@PathVariable Long idApplication) {
        return ResponseEntity.status(HttpStatus.OK).body(
                iApplicationService.findById(idApplication)
        );
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateApplicationById(@Valid @RequestBody UpdateApplicationDTO updateApplicationDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(
                iApplicationService.updateApplication(updateApplicationDTO)
        );
    }

    @DeleteMapping("/delete/{idApplication}")
    public ResponseEntity<?> deleteApplicationById(@PathVariable Long idApplication) {
        iApplicationService.deleteApplication(idApplication);
        return ResponseEntity.status(HttpStatus.OK).body(
                iResponseService.generate_response(true, "Vacante eliminada correctamente")
        );
    }

    @DeleteMapping("/destroy/{idApplication}")
    public ResponseEntity<?> destroyApplicationById(@PathVariable Long idApplication) {
        iApplicationService.destroyApplication(idApplication);
        return ResponseEntity.status(HttpStatus.OK).body(
                iResponseService.generate_response(true, "Vacante eliminada correctamente")
        );
    }
}
