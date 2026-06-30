package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.RequestListEntityDTO;
import com.example.springboot_4_initial.dto.industrialSector.RequestIndustrialSectorDTO;
import com.example.springboot_4_initial.dto.industrialSector.RequestUpdateIndustrialSectorDTO;
import com.example.springboot_4_initial.services.interfaces.IIndustrialSectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/industrial_sector")
@RequiredArgsConstructor
public class IndustrialSectorController {
    private final IIndustrialSectorService iIndustrialSectorService;

    @GetMapping("")
    public ResponseEntity<?> getAllIndustrialSectors(@Valid @RequestBody RequestListEntityDTO requestListEntityDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iIndustrialSectorService.getAllIndustrialSectors(requestListEntityDTO.getStatus()));
    }

    @PostMapping("")
    public ResponseEntity<?> createIndustrialSector(@Valid @RequestBody RequestIndustrialSectorDTO requestIndustrialSectorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iIndustrialSectorService.saveIndustrialSector(requestIndustrialSectorDTO));
    }

    @GetMapping("/{idIndustria}")
    public ResponseEntity<?> getIndustrialSector(@PathVariable Long idIndustria){
        return ResponseEntity.status(HttpStatus.OK).body(iIndustrialSectorService.getIndustrialSectorById(idIndustria));
    }

    @PutMapping("/{idIndustria}")
    public ResponseEntity<?> updateIndustrialSector(@PathVariable Long idIndustria, @Valid @RequestBody RequestUpdateIndustrialSectorDTO requestUpdateIndustrialSectorDTO){
        return ResponseEntity.status(HttpStatus.OK).body(iIndustrialSectorService.updateIndustrialSector(idIndustria, requestUpdateIndustrialSectorDTO));
    }

    @DeleteMapping("/{idIndustria}")
    public ResponseEntity<?> deleteIndustrialSector(@PathVariable Long idIndustria){
        iIndustrialSectorService.deleteIndustrialSector(idIndustria);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/destroy/{idIndustria}")
    public ResponseEntity<?> destroyIndustrialSector(@PathVariable Long idIndustria){
        iIndustrialSectorService.destroyIndustrialSector(idIndustria);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/change/{idIndustria}")
    public ResponseEntity<?> changeStatus(@PathVariable Long idIndustria){
        iIndustrialSectorService.changeStatus(idIndustria);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
