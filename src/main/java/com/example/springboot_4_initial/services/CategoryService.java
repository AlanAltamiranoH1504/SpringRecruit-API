package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.categories.RequestCategoryDTO;
import com.example.springboot_4_initial.dto.categories.RequestUpdateCategoryDTO;
import com.example.springboot_4_initial.dto.categories.ResponseCategoryDTO;
import com.example.springboot_4_initial.exceptions.CreatedEntityException;
import com.example.springboot_4_initial.exceptions.ListEmptyException;
import com.example.springboot_4_initial.exceptions.NotFoundEntity;
import com.example.springboot_4_initial.exceptions.UpdateException;
import com.example.springboot_4_initial.models.Category;
import com.example.springboot_4_initial.repositories.ICategoryRepository;
import com.example.springboot_4_initial.services.interfaces.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final ICategoryRepository iCategoryRepository;

    @Override
    public List<ResponseCategoryDTO> findAllCategories(boolean status) {
        List<Category> categories = iCategoryRepository.findCategoriesByStatus(status);
        if (categories.isEmpty()) {
            throw new ListEmptyException("Empty category list");
        }
        return categories
                .stream()
                .map(category -> {
                    return ResponseCategoryDTO.builder()
                            .idCategory(category.getId())
                            .name(category.getName())
                            .description(category.getDescription())
                            .status(category.isStatus())
                            .build();
                }).toList();
    }

    @Override
    public ResponseCategoryDTO findCategoryById(Long idCategory) {
        Category category = iCategoryRepository.findById(idCategory)
                .orElseThrow(() -> new NotFoundEntity("Category not found"));
        return ResponseCategoryDTO.builder()
                .idCategory(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .status(category.isStatus())
                .build();
    }

    @Override
    public ResponseCategoryDTO saveCategory(RequestCategoryDTO requestCategoryDTO) {
        Category categoryToSave = Category.builder()
                .name(requestCategoryDTO.getName())
                .description(requestCategoryDTO.getDescription())
                .status(true)
                .build();
        iCategoryRepository.save(categoryToSave);
        if (categoryToSave.getId() == null) {
            throw new CreatedEntityException("Error creating category");
        }
        return ResponseCategoryDTO.builder()
                .idCategory(categoryToSave.getId())
                .name(categoryToSave.getName())
                .description(categoryToSave.getDescription())
                .status(categoryToSave.isStatus())
                .build();
    }

    @Override
    public ResponseCategoryDTO updateCategory(Long idCategory, RequestUpdateCategoryDTO requestUpdateCategoryDTO) {
        Category categoryToUpdate = iCategoryRepository.findById(idCategory)
                .orElseThrow(() -> new NotFoundEntity("Not Found Category"));
        Optional<Category> categoryByNameRequest = iCategoryRepository.findByName(requestUpdateCategoryDTO.getName());
        if (categoryByNameRequest.isPresent() && !categoryByNameRequest.get().getId().equals(categoryToUpdate.getId())) {
            throw new UpdateException("Name category already exists");
        }
        BeanUtils.copyProperties(requestUpdateCategoryDTO, categoryToUpdate);
        iCategoryRepository.save(categoryToUpdate);
        return ResponseCategoryDTO.builder()
                .idCategory(categoryToUpdate.getId())
                .name(categoryToUpdate.getName())
                .description(categoryToUpdate.getDescription())
                .status(categoryToUpdate.isStatus())
                .build();
    }

    @Override
    public void deleteCategoryById(Long idCategory) {
        Category categoryToDelete = iCategoryRepository.findById(idCategory)
                .orElseThrow(() -> new NotFoundEntity("Category not found with id: " + idCategory));
        categoryToDelete.setStatus(false);
        iCategoryRepository.save(categoryToDelete);
    }

    @Override
    public void destroyCategory(Long idCategory) {
        Category categoryToDelete = iCategoryRepository.findById(idCategory)
                .orElseThrow(() -> new NotFoundEntity("Category not found with id: " + idCategory));
        iCategoryRepository.delete(categoryToDelete);
    }

    @Override
    public void changeStatusCategory(Long idCategory) {
        Category categoryToChange = iCategoryRepository.findById(idCategory)
                .orElseThrow(() -> new NotFoundEntity("Category not found with id: " + idCategory));
        categoryToChange.setStatus(!categoryToChange.isStatus());
        iCategoryRepository.save(categoryToChange);
    }
}
