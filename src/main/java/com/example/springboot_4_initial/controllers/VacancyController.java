package com.example.springboot_4_initial.controllers;

import com.example.springboot_4_initial.dto.vancacy.*;
import com.example.springboot_4_initial.models.Category;
import com.example.springboot_4_initial.models.User;
import com.example.springboot_4_initial.models.Vacancy;
import com.example.springboot_4_initial.repositories.ICategoryRepository;
import com.example.springboot_4_initial.services.interfaces.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/vacancy")
public class VacancyController {
    @Autowired
    private IVacancyService iVacancyService;
    @Autowired
    private ICategoryRepository iCategoryRepository;
    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private IImageService iImageService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IResponseService iResponseService;

    @GetMapping("/list")
    public ResponseEntity<?> list_vacancies(@Valid @RequestBody ListVacanciesDTO listVacanciesDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iVacancyService.list_vacancies(listVacanciesDTO.getStatus()));
    }

    @PostMapping("/save_vacancy")
    public ResponseEntity<?> save_vacancy(@Valid @RequestBody CreateVacancyDTO createVacancyDTO) {
        iVacancyService.save_vacancy(createVacancyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(iResponseService.generate_response(true, "Vacante guarda correctamente"));
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> find_vacancy(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(iVacancyService.get_vacancy(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update_vacancy(@Valid @RequestBody UpdateVacancyDTO updateVacancyDTO, @PathVariable Long id, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
//        if (bindingResult.hasErrors()) {
//            bindingResult.getFieldErrors().forEach(error -> {
//                json.put(error.getField(), error.getDefaultMessage());
//            });
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json);
//        }
//        Category category = iCategoryService.get_category(updateVacancyDTO.getCategory());
//        Vacancy vacancy_to_update = iVacancyService.get_vacancy(id);
//        vacancy_to_update.setName(updateVacancyDTO.getName());
//        vacancy_to_update.setDescription(updateVacancyDTO.getDescription());
//        vacancy_to_update.setSalary(updateVacancyDTO.getSalary());
//        vacancy_to_update.setStatus(updateVacancyDTO.getStatus());
//        vacancy_to_update.setCategory(category);
//        iVacancyService.save_vacancy(vacancy_to_update);
//        json.put("status", true);
//        json.put("message", "Vacante actualizada");
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete_vacancy(@PathVariable Long id) {
        Map<String, Object> json = new HashMap<>();
        iVacancyService.delete_vacancy(id);
        json.put("status", true);
        json.put("message", "Vacante eliminada");
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }

    @PostMapping("/save_img_vacancy/{id}")
    public ResponseEntity<?> save_img_vacancy(@PathVariable Long id, @RequestParam("img_vacancy") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(iResponseService.generate_response(true, "Imagen de vacante guarda correctamente"));
    }

    @GetMapping("/category/{id_category}")
    public ResponseEntity<?> list_vacancies_by_category(@PathVariable Long id_category) {
        return ResponseEntity.status(HttpStatus.OK).body(iVacancyService.list_vacancies_by_category(id_category));
    }

    // ! Modificar metodo por request body
    @GetMapping("/name/{name_vacancy}")
    public ResponseEntity<?> list_vacancies_by_name(@PathVariable String name_vacancy) {
        return ResponseEntity.status(HttpStatus.OK).body(iVacancyService.list_vacancies_by_name(name_vacancy));
    }

    @PostMapping("/search")
    public ResponseEntity<?> search_vacacies(@Valid @RequestBody VacancyFilterDTO vacancyFilterDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(iVacancyService.search_vacancies(vacancyFilterDTO));
    }

    @PostMapping("/save_vacancies")
    public ResponseEntity<?> save_vacancies(@Valid @RequestBody SaveVacanciesDTO saveVacanciesDTO) {
        iVacancyService.save_vacancies(saveVacanciesDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iResponseService.generate_response(true, "Vacantes guardadas correctamente"));
    }
}
