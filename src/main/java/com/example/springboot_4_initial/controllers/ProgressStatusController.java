package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.RequestListEntityDTO;
import com.example.springboot_4_initial.dto.progressStatus.RequestProgressStatusDTO;
import com.example.springboot_4_initial.dto.progressStatus.RequestUpdateProgressStatusDTO;
import com.example.springboot_4_initial.services.interfaces.IProgressStatusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/progress_status")
@RequiredArgsConstructor
public class ProgressStatusController {
    private final IProgressStatusService iProgressStatusService;

    @GetMapping("")
    public ResponseEntity<?> getAllProgress(@Valid @RequestBody RequestListEntityDTO requestListEntityDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iProgressStatusService.getAllProgressStatus(requestListEntityDTO.getStatus()));
    }

    @GetMapping("/{idProgress}")
    public ResponseEntity<?> getProgressStatus(@PathVariable Long idProgress) {
        return  ResponseEntity.status(HttpStatus.OK).body(iProgressStatusService.getProgressStatus(idProgress));
    }

    @PostMapping("")
    public ResponseEntity<?> saveProgressStatus(@Valid @RequestBody RequestProgressStatusDTO requestProgressStatusDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iProgressStatusService.createProgressStatus(requestProgressStatusDTO));
    }

    @PutMapping("/{idProgress}")
    public ResponseEntity<?> updateProgressStatus(@PathVariable Long idProgress, @Valid @RequestBody RequestUpdateProgressStatusDTO requestUpdateProgressStatusDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iProgressStatusService.updateProgressStatus(idProgress, requestUpdateProgressStatusDTO));
    }

    @DeleteMapping("/{idProgress}")
    public ResponseEntity<?> deleteProgressStatus(@PathVariable Long idProgress) {
        iProgressStatusService.deleteProgressStatus(idProgress);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/destroy/{idProgress}")
    public ResponseEntity<?> destroyProgressStatus(@PathVariable Long idProgress) {
        iProgressStatusService.destroyProgressStatus(idProgress);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/change/{idProgress}")
    public ResponseEntity<?> changeStatus(@PathVariable Long idProgress) {
        iProgressStatusService.changeStatus(idProgress);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
