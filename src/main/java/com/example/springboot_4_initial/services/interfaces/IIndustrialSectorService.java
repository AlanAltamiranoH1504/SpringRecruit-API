package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.industrialSector.RequestIndustrialSectorDTO;
import com.example.springboot_4_initial.dto.industrialSector.RequestUpdateIndustrialSectorDTO;
import com.example.springboot_4_initial.dto.industrialSector.ResponseIndustrialSectorDTO;

import java.util.List;

public interface IIndustrialSectorService {
    public abstract List<ResponseIndustrialSectorDTO> getAllIndustrialSectors(boolean status);
    public abstract ResponseIndustrialSectorDTO getIndustrialSectorById(Long idIndustrialSector);
    public abstract ResponseIndustrialSectorDTO saveIndustrialSector(RequestIndustrialSectorDTO requestIndustrialSectorDTO);
    public abstract ResponseIndustrialSectorDTO updateIndustrialSector(Long idIndustrialSector, RequestUpdateIndustrialSectorDTO requestUpdateIndustrialSectorDTO);
    public abstract void deleteIndustrialSector(Long idIndustrialSector);
    public abstract void destroyIndustrialSector(Long idIndustrialSector);
    public abstract void changeStatus(Long idIndustrialSector);
}
