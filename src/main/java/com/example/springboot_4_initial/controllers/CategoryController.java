package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.RequestListEntityDTO;
import com.example.springboot_4_initial.dto.categories.RequestCategoryDTO;
import com.example.springboot_4_initial.dto.categories.RequestUpdateCategoryDTO;
import com.example.springboot_4_initial.dto.categories.ResponseCategoryDTO;
import com.example.springboot_4_initial.services.interfaces.ICategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryService iCategoryService;

    @GetMapping("")
    public ResponseEntity<List<ResponseCategoryDTO>> getAllCategories(@Valid @RequestBody RequestListEntityDTO requestListEntityDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iCategoryService.findAllCategories(requestListEntityDTO.getStatus()));
    }

    @PostMapping("")
    public ResponseEntity<ResponseCategoryDTO> createCategory(@Valid @RequestBody RequestCategoryDTO requestCategoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iCategoryService.saveCategory(requestCategoryDTO));
    }

    @GetMapping("/{idCategory}")
    public ResponseEntity<?> findCategoryById(@PathVariable Long idCategory) {
        return ResponseEntity.status(HttpStatus.OK).body(iCategoryService.findCategoryById(idCategory));
    }

    @PutMapping("/{idCategory}")
    public ResponseEntity<?> updateCategory(@PathVariable Long idCategory, @Valid @RequestBody RequestUpdateCategoryDTO requestUpdateCategoryDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iCategoryService.updateCategory(idCategory, requestUpdateCategoryDTO));
    }

    @DeleteMapping("/{idCategory}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long idCategory) {
        iCategoryService.deleteCategoryById(idCategory);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/change/{idCategory}")
    public ResponseEntity<?> changeStatusCategory(@PathVariable Long idCategory) {
        iCategoryService.changeStatusCategory(idCategory);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/destroy/{idCategory}")
    public ResponseEntity<?> destroyCategory(@PathVariable Long idCategory) {
        iCategoryService.destroyCategory(idCategory);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
