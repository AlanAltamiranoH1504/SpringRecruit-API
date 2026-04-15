package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.vancacy.*;
import com.example.springboot_4_initial.exceptions.ListEmptyException;
import com.example.springboot_4_initial.exceptions.vancacies.ErrorUpdateImgVacancy;
import com.example.springboot_4_initial.exceptions.vancacies.NotFoundEntityException;
import com.example.springboot_4_initial.exceptions.vancacies.NotFoundVacancy;
import com.example.springboot_4_initial.exceptions.vancacies.NotFoundVacancys;
import com.example.springboot_4_initial.models.Category;
import com.example.springboot_4_initial.models.Recruiter;
import com.example.springboot_4_initial.models.User;
import com.example.springboot_4_initial.models.Vacancy;
import com.example.springboot_4_initial.repositories.*;
import com.example.springboot_4_initial.security.JwtService;
import com.example.springboot_4_initial.services.interfaces.ICryptoService;
import com.example.springboot_4_initial.services.interfaces.IUserService;
import com.example.springboot_4_initial.services.interfaces.IVacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VacancyService implements IVacancyService {
    @Autowired
    private IVacancyRepository iVacancyRepository;
    @Autowired
    private ICryptoService iCryptoService;
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IContractTypeRepository iContractTypeRepository;
    @Autowired
    private IWorkModalityRepository iWorkModalityRepository;
    @Autowired
    private IIndustrialSectorRepository iIndustrialSectorRepository;
    @Autowired
    private IProgressStatusRepository iProgressStatusRepository;
    @Autowired
    private ICategoryRepository iCategoryRepository;
    @Autowired
    private JwtService jwtService;


    @Override
    public List<Vacancy> list_vacancies(boolean status) {
        List<Vacancy> list_vacancies = iVacancyRepository.list_vacancies(status);
        if (list_vacancies.isEmpty()) {
            throw new NotFoundVacancys("No existen registros de vacantes");
        }
        return list_vacancies;
    }

    @Override
    public List<VacancyWithApplicationDTO> listVacanciesByRecruiter(String tokenJWT) {
        Pageable pageable = PageRequest.of(0, 5);
        User user = iUserService.get_user(jwtService.extract_id_user(tokenJWT));
        List<VacancyWithApplicationDTO> vacancyList = iVacancyRepository.listVacanciesByIdRecruiter(user.getRecruiter().getId_recruiter(), pageable);

        if (vacancyList.isEmpty()) {
            throw new ListEmptyException("No existen registros de vacantes de ese reclutador");
        }
        return vacancyList;
    }

    @Override
    public Vacancy save_vacancy(CreateVacancyDTO createVacancyDTO) {
        // * Search Recruiter
        User user = iUserService.get_user(jwtService.extract_id_user(createVacancyDTO.getJwt()));
        Recruiter recruiter = user.getRecruiter();

        // * Save vacancy
        Vacancy vacancy_to_save = new Vacancy(
                createVacancyDTO.getName(),
                createVacancyDTO.getDescription(),
                createVacancyDTO.getLocation(),
                createVacancyDTO.getSalary(),
                LocalDate.now(),
                createVacancyDTO.getFinish_date(),
                createVacancyDTO.getRequirements(),
                createVacancyDTO.getResponsibilities(),
                null,
                true,
                iContractTypeRepository.getReferenceById(createVacancyDTO.getIdContract_type()),
                iProgressStatusRepository.getReferenceById(createVacancyDTO.getIdProgressStatus()),
                iIndustrialSectorRepository.getReferenceById(createVacancyDTO.getIdIndustrialSector()),
                iWorkModalityRepository.getReferenceById(createVacancyDTO.getIdWorkModality()),
                iCategoryRepository.getReferenceById(createVacancyDTO.getIdCategory()),
                recruiter
        );
        iVacancyRepository.save(vacancy_to_save);
        return vacancy_to_save;
    }

    @Override
    public Vacancy get_vacancy(Long id) {
        Optional<Vacancy> vacancy_to_show = iVacancyRepository.findById(id);
        if (!vacancy_to_show.isPresent()) {
            throw new NotFoundVacancy("El registro de la vacante no fue encontrado");
        }
        if (vacancy_to_show.get().getImage() != null) {
            vacancy_to_show.get().setImage(vacancy_to_show.get().getImage().replace("\\", "/"));
        } else {
            return vacancy_to_show.get();
        }
        return vacancy_to_show.get();
    }

    @Override
    public boolean delete_vacancy(Long id) {
//        Vacancy vacancy_to_delete = this.get_vacancy(id);
//        vacancy_to_delete.setStatus(false);
//        this.save_vacancy(vacancy_to_delete);
        return true;
    }

    @Override
    public Vacancy update_img_vacancy(String path_img, Long idVacancy) {
        Optional<Vacancy> vacancy_to_update = iVacancyRepository.findById(idVacancy);
        vacancy_to_update.get().setImage(path_img);
        iVacancyRepository.save(vacancy_to_update.get());
        if (Objects.equals(vacancy_to_update.get().getImage(), "")) {
            throw new ErrorUpdateImgVacancy("Ocurrio un error en el guardado de la imagen de la vacante");
        }
        return vacancy_to_update.get();
    }

    @Override
    public List<Vacancy> list_vacancies_by_category(Long id_category) {
        Optional<Category> category = iCategoryRepository.findById(id_category);
        if (category.isEmpty()) {
            throw new NotFoundEntityException("La categoria con id " + id_category + " no existe");
        }
        List<Vacancy> vacacies_by_category = iVacancyRepository.list_vacancies_by_category(id_category);
        if (vacacies_by_category.isEmpty()) {
            throw new ListEmptyException("No existen vacantes registradas en esa categoria");
        }
        return vacacies_by_category;
    }

    @Override
    public List<Vacancy> list_vacancies_by_name(String name) {
        List<Vacancy> vacancies_by_name = iVacancyRepository.list_vacancies_by_name(name);
        if (vacancies_by_name.isEmpty()) {
            throw new ListEmptyException("No existen vacantes registradas con coincidencias de ese nombre");
        }
        return vacancies_by_name;
    }

    @Override
    public List<Vacancy> search_vacancies(VacancyFilterDTO vacancyFilterDTO) {
        Specification<Vacancy> spec = VacancySpecifications.buildQuery(vacancyFilterDTO);
        return iVacancyRepository.findAll(spec);
    }

    @Override
    public boolean save_vacancies(SaveVacanciesDTO saveVacanciesDTO) {
        List<CreateVacancyDTO> vacanciesDTO = saveVacanciesDTO.getVacancies();
        for (CreateVacancyDTO vacancy: vacanciesDTO) {
            this.save_vacancy(vacancy);
        }
        return true;
    }
}
