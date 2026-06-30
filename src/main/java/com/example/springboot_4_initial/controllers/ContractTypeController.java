package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.RequestListEntityDTO;
import com.example.springboot_4_initial.dto.contractType.RequestContractTypeRequest;
import com.example.springboot_4_initial.dto.contractType.RequestUpdateContractType;
import com.example.springboot_4_initial.services.interfaces.IContractTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contract_types")
@RequiredArgsConstructor
public class ContractTypeController {
    private final IContractTypeService iContractTypeService;

    @GetMapping("")
    public ResponseEntity<?> getAllContractType(@Valid @RequestBody RequestListEntityDTO requestListEntityDTO){
        return ResponseEntity.status(HttpStatus.OK).body(iContractTypeService.getAllContractTypes(requestListEntityDTO.getStatus()));
    }

    @GetMapping("/{idContract}")
    public ResponseEntity<?> getContractType(@PathVariable Long idContract) {
        return ResponseEntity.status(HttpStatus.OK).body(iContractTypeService.getContractType(idContract));
    }

    @PostMapping("")
    public ResponseEntity<?> saveContractType(@Valid @RequestBody RequestContractTypeRequest requestContractTypeRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(iContractTypeService.saveContractType(requestContractTypeRequest));
    }

    @PutMapping("/{idContract}")
    public ResponseEntity<?> updateContractType(@PathVariable Long idContract, @Valid @RequestBody RequestUpdateContractType requestUpdateContractType) {
        return ResponseEntity.status(HttpStatus.OK).body(iContractTypeService.updateContractType(idContract, requestUpdateContractType));
    }

    @DeleteMapping("/{idContract}")
    public ResponseEntity<?> deleteContractType(@PathVariable Long idContract) {
        iContractTypeService.deleteContractType(idContract);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/change/{idContract}")
    public ResponseEntity<?> changeStatus(@PathVariable Long idContract) {
        iContractTypeService.changeStatusContractType(idContract);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/destroy/{idContract}")
    public ResponseEntity<?> destroyContractType(@PathVariable Long idContract) {
        iContractTypeService.destroyContractType(idContract);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
