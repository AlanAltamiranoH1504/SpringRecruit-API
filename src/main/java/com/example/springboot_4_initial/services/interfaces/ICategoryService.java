package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.categories.RequestCategoryDTO;
import com.example.springboot_4_initial.dto.categories.RequestUpdateCategoryDTO;
import com.example.springboot_4_initial.dto.categories.ResponseCategoryDTO;

import java.util.List;

public interface ICategoryService {
    public abstract List<ResponseCategoryDTO> findAllCategories(boolean status);
    public abstract ResponseCategoryDTO findCategoryById(Long idCategory);
    public abstract ResponseCategoryDTO saveCategory(RequestCategoryDTO requestCategoryDTO);
    public abstract ResponseCategoryDTO updateCategory(Long idCategory, RequestUpdateCategoryDTO requestUpdateCategoryDTO);
    public abstract void deleteCategoryById(Long idCategory);
    public abstract void destroyCategory(Long idCategory);
    public abstract void changeStatusCategory(Long idCategory);
}
