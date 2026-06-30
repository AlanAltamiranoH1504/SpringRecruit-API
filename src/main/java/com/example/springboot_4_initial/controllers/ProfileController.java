package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.RequestListEntityDTO;
import com.example.springboot_4_initial.dto.profile.RequestUpdateProfileDTO;
import com.example.springboot_4_initial.dto.profile.RquestProfileDTO;
import com.example.springboot_4_initial.services.interfaces.IProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final IProfileService iProfileService;

    @GetMapping("")
    public ResponseEntity<?> getAllProfile(@Valid @RequestBody RequestListEntityDTO requestListEntityDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iProfileService.getAllProfiles(requestListEntityDTO.getStatus()));
    }

    @PostMapping("")
    public ResponseEntity<?> createProfile(@Valid @RequestBody RquestProfileDTO requestProfileDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iProfileService.createProfile(requestProfileDTO));
    }

    @GetMapping("/{idProfile}")
    public ResponseEntity<?> getProfileById(@PathVariable Long idProfile) {
        return ResponseEntity.status(HttpStatus.OK).body(iProfileService.getProfile(idProfile));
    }

    @PutMapping("/{idProfile}")
    public ResponseEntity<?> updateProfile(@PathVariable Long idProfile, @Valid @RequestBody RequestUpdateProfileDTO updateProfileDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iProfileService.updateProfile(idProfile, updateProfileDTO));
    }

    @DeleteMapping("/{idProfile}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long idProfile) {
        iProfileService.deleteProfile(idProfile);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/destroy/{idProfile}")
    public ResponseEntity<?> destroyProfile(@PathVariable Long idProfile) {
        iProfileService.destroyProfile(idProfile);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/change/{idProfile}")
    public ResponseEntity<?> changeStatusProfile(@PathVariable Long idProfile) {
        iProfileService.changeStatus(idProfile);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
