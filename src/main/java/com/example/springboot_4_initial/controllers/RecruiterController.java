package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.ListEntityDTO;
import com.example.springboot_4_initial.dto.ShowEntityDTO;
import com.example.springboot_4_initial.dto.auth.IsJwtValidDTO;
import com.example.springboot_4_initial.dto.recruiter.UpdateRecruiterDTO;
import com.example.springboot_4_initial.services.interfaces.ICryptoService;
import com.example.springboot_4_initial.services.interfaces.IRecruiterService;
import com.example.springboot_4_initial.services.interfaces.IResponseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {
    @Autowired
    private IRecruiterService iRecruiterService;
    @Autowired
    private IResponseService iResponseService;
    @Autowired
    private ICryptoService iCryptoService;

    @GetMapping("/list")
    public ResponseEntity<?> list_recruiters(@RequestBody ListEntityDTO listEntityDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iRecruiterService.list_recruiters(listEntityDTO.getStatus()));
    }

    @GetMapping("/find_recruiter/{id_recruiter}")
    public ResponseEntity<?> find_recruiter(@PathVariable Long id_recruiter) {
        return ResponseEntity.status(HttpStatus.OK).body(iRecruiterService.get_recruiter(id_recruiter));
    }

    @GetMapping("/show_recruiter")
    public ResponseEntity<?> show_recruiter(@Valid @RequestBody ShowEntityDTO showEntityDTO) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(iRecruiterService.show_recruiter(iCryptoService.decrypt(showEntityDTO.getId_entity_crypt())));
    }

    @DeleteMapping("/delete_recruiter/{id_recruiter}")
    public ResponseEntity<?> delete_recruiter(@PathVariable Long id_recruiter) {
        iRecruiterService.delete_recruiter(id_recruiter);
        return ResponseEntity.status(HttpStatus.OK).body(iResponseService.generate_response(true,"Reclutador eliminado correctamente"));
    }

    @DeleteMapping("/destroy_recruiter/{id_recruiter}")
    public ResponseEntity<?> destroy_recruiter(@PathVariable Long id_recruiter) {
        iRecruiterService.destroy_recruiter(id_recruiter);
        return ResponseEntity.status(HttpStatus.OK).body(iResponseService.generate_response(true, "Reclutador eliminado correctamente"));
    }

    @PutMapping("/update_recruiter/{id_user}")
    public ResponseEntity<?> update_recruiter(@Valid @RequestBody UpdateRecruiterDTO updateRecruiterDTO, @PathVariable Long id_user) {
        iRecruiterService.update_recruiter(updateRecruiterDTO, id_user);
        return ResponseEntity.status(HttpStatus.OK).body(iResponseService.generate_response(true, "Datos de reclutador actualizados correctamente"));
    }

    @PostMapping("/recruiter_in_session")
    public ResponseEntity<?> userInSesion(@Valid @RequestBody IsJwtValidDTO isJwtValidDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iRecruiterService.recruiterInSession(isJwtValidDTO.getToken()));
    }
}
