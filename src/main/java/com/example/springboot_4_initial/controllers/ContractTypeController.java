package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.ListEntityDTO;
import com.example.springboot_4_initial.dto.contractType.CreateContractTypeDTO;
import com.example.springboot_4_initial.dto.contractType.UpdateContractTypeDTO;
import com.example.springboot_4_initial.services.interfaces.IContractTypeService;
import com.example.springboot_4_initial.services.interfaces.IResponseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contract_type")
public class ContractTypeController {
    @Autowired
    private IContractTypeService iContractTypeService;
    @Autowired
    private IResponseService iResponseService;

    @PostMapping("/list")
    public ResponseEntity<?> listContractsType(@Valid @RequestBody ListEntityDTO listEntityDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iContractTypeService.findAll(listEntityDTO));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveContractType(@Valid @RequestBody CreateContractTypeDTO createContractTypeDTO) {
        iContractTypeService.save(createContractTypeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                iResponseService.generate_response(true, "Tipo de contrato creado correctamente")
        );
    }

    @GetMapping("/find/{idContractType}")
    public ResponseEntity<?> findContractType(@PathVariable Long idContractType) {
        return ResponseEntity.status(HttpStatus.OK).body(
                iContractTypeService.findById(idContractType)
        );
    }

    @PutMapping("/update/{idContractType}")
    public ResponseEntity<?> updateContractType(@Valid @RequestBody UpdateContractTypeDTO updateContractTypeDTO, @PathVariable Long idContractType) {
        iContractTypeService.update(updateContractTypeDTO, idContractType);
        return ResponseEntity.status(HttpStatus.OK).body(
                iResponseService.generate_response(true, "Tipo de contrato actualizado correctamente")
        );
    }

    @DeleteMapping("/delete/{idContractType}")
    public ResponseEntity<?> deleteContractType(@PathVariable Long idContractType) {
        iContractTypeService.deleteCotractType(idContractType);
        return ResponseEntity.status(HttpStatus.OK).body(
                iResponseService.generate_response(true, "Tipo de contrato eliminado correctamente")
        );
    }

    @DeleteMapping("/destroy/{idContractType}")
    public ResponseEntity<?> destroyContractType(@PathVariable Long idContractType) {
        iContractTypeService.destroyContractType(idContractType);
        return ResponseEntity.status(HttpStatus.OK).body(
                iResponseService.generate_response(true, "Tipo de contrato destruido correctamente")
        );
    }
}
