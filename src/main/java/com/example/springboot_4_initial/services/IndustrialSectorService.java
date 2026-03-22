package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.ListEntityDTO;
import com.example.springboot_4_initial.dto.industrialSector.CreateIndustrialSectorDTO;
import com.example.springboot_4_initial.dto.industrialSector.UpdateIndustrialSectorDTO;
import com.example.springboot_4_initial.exceptions.CreatedEntityException;
import com.example.springboot_4_initial.exceptions.ListEmptyException;
import com.example.springboot_4_initial.exceptions.NotFoundEntity;
import com.example.springboot_4_initial.exceptions.industrialSector.NameIndustrialSectorError;
import com.example.springboot_4_initial.exceptions.vancacies.NotFoundEntityException;
import com.example.springboot_4_initial.models.IndustrialSector;
import com.example.springboot_4_initial.models.ProgressStatus;
import com.example.springboot_4_initial.repositories.IIndustrialSectorRepository;
import com.example.springboot_4_initial.services.interfaces.IIndustrialSectorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IndustrialSectorService implements IIndustrialSectorService {
    @Autowired
    private IIndustrialSectorRepository iIndustrialSectorRepository;

    @Override
    public List<IndustrialSector> findAll(ListEntityDTO listEntityDTO) {
        List<IndustrialSector> industrialSectors = iIndustrialSectorRepository.findAll(listEntityDTO.getStatus());
        if (industrialSectors.isEmpty()) {
            throw new ListEmptyException("No existen registros de sectores de industria");
        }
        return industrialSectors;
    }

    @Override
    public IndustrialSector save(CreateIndustrialSectorDTO createIndustrialSectorDTO) {
        Optional<IndustrialSector> nameInUse = iIndustrialSectorRepository.findByName(createIndustrialSectorDTO.getName_industrial_sector());
        if (nameInUse.isPresent()) {
            throw new CreatedEntityException("El nombre del sector industrial ya se encuentra registrado");
        }
        IndustrialSector industrialSectorToSave = new IndustrialSector(createIndustrialSectorDTO.getName_industrial_sector(), createIndustrialSectorDTO.getDescription_industrial_sector(), true);
        iIndustrialSectorRepository.save(industrialSectorToSave);

        return industrialSectorToSave;
    }

    @Override
    public IndustrialSector findById(Long idIndustrialSector) {
        Optional<IndustrialSector> industrialSectorToShow = iIndustrialSectorRepository.findById(idIndustrialSector);
        if (industrialSectorToShow.isPresent()) {
            return industrialSectorToShow.get();
        }
        throw new NotFoundEntityException("El sector industrial con el id " + idIndustrialSector + " no existe");
    }

    @Override
    public IndustrialSector update(UpdateIndustrialSectorDTO updateIndustrialSectorDTO, Long idIndustrialSector) {
        Optional<IndustrialSector> nameInUse = iIndustrialSectorRepository.findByName(updateIndustrialSectorDTO.getName_industrial_sector());
        if (nameInUse.isPresent() && nameInUse.get().getId_industrial_sector() != idIndustrialSector) {
            throw new NameIndustrialSectorError("El nombre del sector industrial ya se encuentra registrado");
        }
        IndustrialSector industrialSectorToUpdate = this.findById(idIndustrialSector);
        BeanUtils.copyProperties(updateIndustrialSectorDTO, industrialSectorToUpdate);
        iIndustrialSectorRepository.save(industrialSectorToUpdate);
        return industrialSectorToUpdate;
    }

    @Override
    public void deleteById(Long idIndustrialSector) {
        IndustrialSector industrialSectorToDelete = this.findById(idIndustrialSector);
        industrialSectorToDelete.setStatus(false);
        iIndustrialSectorRepository.save(industrialSectorToDelete);
    }

    @Override
    public void destroyById(Long idIndustrialSector) {
        IndustrialSector industrialSectorToDestroy = this.findById(idIndustrialSector);
        iIndustrialSectorRepository.delete(industrialSectorToDestroy);
    }
}
