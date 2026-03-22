package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.ListEntityDTO;
import com.example.springboot_4_initial.dto.industrialSector.CreateIndustrialSectorDTO;
import com.example.springboot_4_initial.dto.industrialSector.UpdateIndustrialSectorDTO;
import com.example.springboot_4_initial.models.IndustrialSector;
import com.example.springboot_4_initial.models.ProgressStatus;

import java.util.List;

public interface IIndustrialSectorService {
    public abstract List<IndustrialSector> findAll(ListEntityDTO listEntityDTO);
    public abstract IndustrialSector save(CreateIndustrialSectorDTO createIndustrialSectorDTO);
    public abstract IndustrialSector findById(Long idIndustrialSector);
    public abstract IndustrialSector update(UpdateIndustrialSectorDTO updateIndustrialSectorDTO, Long idIndustrialSector);
    public abstract void deleteById(Long idIndustrialSector);
    public abstract void destroyById(Long idIndustrialSector);
}
