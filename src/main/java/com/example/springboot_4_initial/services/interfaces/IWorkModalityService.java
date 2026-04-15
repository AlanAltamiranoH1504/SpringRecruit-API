package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.ListEntityDTO;
import com.example.springboot_4_initial.models.ProgressStatus;
import com.example.springboot_4_initial.models.WorkModality;

import java.util.List;

public interface IWorkModalityService {
    public abstract List<WorkModality> findAll(ListEntityDTO listEntityDTO);
    public abstract ProgressStatus save();
    public abstract ProgressStatus findById(Long idProgressStatus);
}
