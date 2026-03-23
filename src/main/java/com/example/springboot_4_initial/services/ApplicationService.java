package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.models.Application;
import com.example.springboot_4_initial.repositories.IApplicationRepository;
import com.example.springboot_4_initial.services.interfaces.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService implements IApplicationService {
    @Autowired
    private IApplicationRepository iApplicationRepository;

    @Override
    public List<Application> findAllByRecruiter(Long idRecruiter) {
        return List.of();
    }

    @Override
    public Application findById(Long idApplication) {
        return null;
    }

    @Override
    public Application saveApplication() {
        return null;
    }

    @Override
    public Application updateApplication() {
        return null;
    }

    @Override
    public void deleteApplication(Long idApplication) {

    }

    @Override
    public void destroyApplication(Long idApplication) {

    }
}
