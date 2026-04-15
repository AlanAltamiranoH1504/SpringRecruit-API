package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.ListEntityDTO;
import com.example.springboot_4_initial.exceptions.ListEmptyException;
import com.example.springboot_4_initial.models.ProgressStatus;
import com.example.springboot_4_initial.models.WorkModality;
import com.example.springboot_4_initial.repositories.IWorkModalityRepository;
import com.example.springboot_4_initial.services.interfaces.IWorkModalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkModalityService implements IWorkModalityService {
    @Autowired
    private IWorkModalityRepository iWorkModalityRepository;

    @Override
    public List<WorkModality> findAll(ListEntityDTO listEntityDTO) {
        List<WorkModality> listWorkModality = iWorkModalityRepository.listByStatus(listEntityDTO.getStatus());
        if (listWorkModality.isEmpty()) {
            throw new ListEmptyException("La lista de modalidades de trabajo esta vacia");
        }
        return listWorkModality;
    }

    @Override
    public ProgressStatus save() {
        return null;
    }

    @Override
    public ProgressStatus findById(Long idProgressStatus) {
        return null;
    }
}
