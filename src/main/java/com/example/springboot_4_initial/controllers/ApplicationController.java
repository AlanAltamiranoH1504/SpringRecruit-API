package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.application.CreateApplicationDTO;
import com.example.springboot_4_initial.dto.application.UpdateApplicationDTO;
import com.example.springboot_4_initial.dto.auth.IsJwtValidDTO;
import com.example.springboot_4_initial.repositories.IRecruiterRepository;
import com.example.springboot_4_initial.security.JwtService;
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

    @PostMapping("/list/by_recruiter")
    public ResponseEntity<?> listApplicationsByIdRecruiter(@Valid @RequestBody IsJwtValidDTO isJwtValidDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(
                iApplicationService.applicationsByIdRecruiter(isJwtValidDTO.getToken())
        );
    }

    @PostMapping("/find_all/by_recruiter")
    public ResponseEntity<?> findAllCandidatesByRecruiterVacancies(@Valid @RequestBody IsJwtValidDTO isJwtValidDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(
                iApplicationService.findAllCandidatesByRecruiterVacancies(isJwtValidDTO.getToken())
        );
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

    @PostMapping("/applications_details/by/vancancy/{idVacancy}")
    public ResponseEntity<?> getApplicationsDetailsByIdVacancy(@Valid @RequestBody IsJwtValidDTO isJwtValidDTO, @PathVariable Long idVacancy) {
        return ResponseEntity.status(HttpStatus.OK).body(
                iApplicationService.getApplicationsDetailsByIdVacancy(idVacancy, isJwtValidDTO.getToken())
        );
    }
}
