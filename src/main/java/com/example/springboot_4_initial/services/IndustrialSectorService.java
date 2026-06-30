package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.industrialSector.RequestIndustrialSectorDTO;
import com.example.springboot_4_initial.dto.industrialSector.RequestUpdateIndustrialSectorDTO;
import com.example.springboot_4_initial.dto.industrialSector.ResponseIndustrialSectorDTO;
import com.example.springboot_4_initial.exceptions.CreatedEntityException;
import com.example.springboot_4_initial.exceptions.ListEmptyException;
import com.example.springboot_4_initial.exceptions.NotFoundEntity;
import com.example.springboot_4_initial.exceptions.UpdateException;
import com.example.springboot_4_initial.models.IndustrialSector;
import com.example.springboot_4_initial.repositories.IIndustrialSectorRepository;
import com.example.springboot_4_initial.services.interfaces.IIndustrialSectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IndustrialSectorService implements IIndustrialSectorService {
    private final IIndustrialSectorRepository iIndustrialSectorRepository;

    @Override
    public List<ResponseIndustrialSectorDTO> getAllIndustrialSectors(boolean status) {
        List<IndustrialSector> industrialSectorList = iIndustrialSectorRepository.findAllByStatus(status);
        if (industrialSectorList.isEmpty()) {
            throw new ListEmptyException("List emty");
        }
        return industrialSectorList.stream()
                .map(indus -> {
                    return ResponseIndustrialSectorDTO.builder()
                            .idIndustrialSector(indus.getId_industrial_sector())
                            .nameIndustrialSector(indus.getName_industrial_sector())
                            .descriptionIndustrialSector(indus.getDescription_industrial_sector())
                            .status(indus.isStatus())
                            .build();
                }).toList();
    }

    @Override
    public ResponseIndustrialSectorDTO getIndustrialSectorById(Long idIndustrialSector) {
        IndustrialSector industrialSector = iIndustrialSectorRepository.findById(idIndustrialSector)
                .orElseThrow(() -> new NotFoundEntity("Not Found With ID: " +  idIndustrialSector));
        return ResponseIndustrialSectorDTO.builder()
                .idIndustrialSector(industrialSector.getId_industrial_sector())
                .nameIndustrialSector(industrialSector.getName_industrial_sector())
                .descriptionIndustrialSector(industrialSector.getDescription_industrial_sector())
                .status(industrialSector.isStatus())
                .build();
    }

    @Override
    public ResponseIndustrialSectorDTO saveIndustrialSector(RequestIndustrialSectorDTO requestIndustrialSectorDTO) {
        IndustrialSector sectorByName = iIndustrialSectorRepository.findByName(requestIndustrialSectorDTO.getName_industrial_sector());
        if (sectorByName != null) {
            throw new CreatedEntityException("Industrial sector already exists");
        }
        IndustrialSector industrialSector = IndustrialSector.builder()
                .name_industrial_sector(requestIndustrialSectorDTO.getName_industrial_sector())
                .description_industrial_sector(requestIndustrialSectorDTO.getDescription_industrial_sector())
                .status(true)
                .build();
        iIndustrialSectorRepository.save(industrialSector);
        return ResponseIndustrialSectorDTO.builder()
                .idIndustrialSector(industrialSector.getId_industrial_sector())
                .nameIndustrialSector(industrialSector.getName_industrial_sector())
                .descriptionIndustrialSector(industrialSector.getDescription_industrial_sector())
                .status(true)
                .build();
    }

    @Override
    public ResponseIndustrialSectorDTO updateIndustrialSector(Long idIndustrialSector, RequestUpdateIndustrialSectorDTO requestUpdateIndustrialSectorDTO) {
        IndustrialSector industrialToUpdate = iIndustrialSectorRepository.findById(idIndustrialSector)
                .orElseThrow(() -> new NotFoundEntity("Not Found With ID: " +  idIndustrialSector));
        IndustrialSector industrialByName = iIndustrialSectorRepository.findByName(requestUpdateIndustrialSectorDTO.getName_industrial_sector());
        if (industrialByName != null && !industrialByName.getId_industrial_sector().equals(idIndustrialSector)) {
            throw new UpdateException("Industrial sector already exists");
        }
        BeanUtils.copyProperties(requestUpdateIndustrialSectorDTO, industrialToUpdate);
        iIndustrialSectorRepository.save(industrialToUpdate);
        return ResponseIndustrialSectorDTO.builder()
                .idIndustrialSector(industrialToUpdate.getId_industrial_sector())
                .nameIndustrialSector(industrialToUpdate.getName_industrial_sector())
                .descriptionIndustrialSector(industrialToUpdate.getDescription_industrial_sector())
                .status(industrialToUpdate.isStatus())
                .build();
    }

    @Override
    public void deleteIndustrialSector(Long idIndustrialSector) {
        IndustrialSector industrialSector = iIndustrialSectorRepository.findById(idIndustrialSector)
                .orElseThrow(() -> new  NotFoundEntity("Not Found Industrial Sector With ID: " + idIndustrialSector));
        industrialSector.setStatus(false);
        iIndustrialSectorRepository.save(industrialSector);
    }

    @Override
    public void destroyIndustrialSector(Long idIndustrialSector) {
        ResponseIndustrialSectorDTO industrialSector = this.getIndustrialSectorById(idIndustrialSector);
        iIndustrialSectorRepository.deleteById(idIndustrialSector);
    }

    @Override
    public void changeStatus(Long idIndustrialSector) {
        IndustrialSector industrialSector = iIndustrialSectorRepository.findById(idIndustrialSector)
                .orElseThrow(() -> new NotFoundEntity("Not Found Industrial Sector With ID: " + idIndustrialSector));
        industrialSector.setStatus(!industrialSector.isStatus());
        iIndustrialSectorRepository.save(industrialSector);
    }
}
