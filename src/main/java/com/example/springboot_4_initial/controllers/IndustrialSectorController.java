package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.ListEntityDTO;
import com.example.springboot_4_initial.dto.industrialSector.CreateIndustrialSectorDTO;
import com.example.springboot_4_initial.dto.industrialSector.UpdateIndustrialSectorDTO;
import com.example.springboot_4_initial.services.interfaces.IIndustrialSectorService;
import com.example.springboot_4_initial.services.interfaces.IResponseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/industrial_sector")
public class IndustrialSectorController {
    @Autowired
    private IIndustrialSectorService iIndustrialSectorService;
    @Autowired
    private IResponseService iResponseService;

    @PostMapping("/list")
    public ResponseEntity<?> listIndustrialSectors(@Valid @RequestBody ListEntityDTO listEntityDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iIndustrialSectorService.findAll(listEntityDTO));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveIndustrialSector(@Valid @RequestBody CreateIndustrialSectorDTO createIndustrialSectorDTO) {
        iIndustrialSectorService.save(createIndustrialSectorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                iResponseService.generate_response(true, "Sector industrial guardado correctamente")
        );
    }

    @GetMapping("/find/{idIndustrialSector}")
    public ResponseEntity<?> findIndustrialSector(@PathVariable Long idIndustrialSector) {
        return ResponseEntity.status(HttpStatus.OK).body(iIndustrialSectorService.findById(idIndustrialSector));
    }

    @PutMapping("/update/{idIndustrialSector}")
    public ResponseEntity<?> updateIndustrialSector(@Valid @RequestBody UpdateIndustrialSectorDTO updateIndustrialSectorDTO, @PathVariable Long idIndustrialSector) {
        iIndustrialSectorService.update(updateIndustrialSectorDTO, idIndustrialSector);
        return ResponseEntity.status(HttpStatus.OK).body(
                iResponseService.generate_response(true, "Sector industrial actualizado correctamente")
        );
    }

    @DeleteMapping("/delete/{idIndustrialSector}")
    public ResponseEntity<?> deleteIndustrialSector(@PathVariable Long idIndustrialSector) {
        iIndustrialSectorService.deleteById(idIndustrialSector);
        return ResponseEntity.status(HttpStatus.OK).body(
                iResponseService.generate_response(true, "Sector industrial eliminado correctamente")
        );
    }

    @DeleteMapping("/destroy/{idIndustrialSector}")
    public ResponseEntity<?> destroyIndustrialSector(@PathVariable Long idIndustrialSector) {
        iIndustrialSectorService.destroyById(idIndustrialSector);
        return ResponseEntity.status(HttpStatus.OK).body(
                iResponseService.generate_response(true, "Sector industrial eliminado correctamente")
        );
    }

}
