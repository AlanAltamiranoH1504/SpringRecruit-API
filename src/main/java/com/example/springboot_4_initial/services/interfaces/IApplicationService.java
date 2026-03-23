package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.models.Application;

import java.util.List;

public interface IApplicationService {
    public abstract List<Application> findAllByRecruiter(Long idRecruiter);
    public abstract Application findById(Long idApplication);
    public abstract Application saveApplication();
    public abstract Application updateApplication();
    public abstract void deleteApplication(Long idApplication);
    public abstract void destroyApplication(Long idApplication);
}
