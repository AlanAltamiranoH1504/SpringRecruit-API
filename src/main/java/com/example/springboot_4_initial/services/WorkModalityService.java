package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.workModality.RequestUpdateWorkModalityDTO;
import com.example.springboot_4_initial.dto.workModality.RequestWorkModalityDTO;
import com.example.springboot_4_initial.dto.workModality.ResponseWorkModalityDTO;
import com.example.springboot_4_initial.exceptions.CreatedEntityException;
import com.example.springboot_4_initial.exceptions.ListEmptyException;
import com.example.springboot_4_initial.exceptions.NotFoundEntity;
import com.example.springboot_4_initial.exceptions.UpdateException;
import com.example.springboot_4_initial.models.WorkModality;
import com.example.springboot_4_initial.repositories.IWorkModalityRepository;
import com.example.springboot_4_initial.services.interfaces.IWorkModalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkModalityService implements IWorkModalityService {
    private final IWorkModalityRepository iWorkModalityRepository;

    @Override
    public List<ResponseWorkModalityDTO> getAllWorkModality(boolean status) {
        List<WorkModality> workModalities = iWorkModalityRepository.getAllByStatus(status);
        if (workModalities.isEmpty()) {
            throw new ListEmptyException("List empty");
        }
        return workModalities.stream()
                .map(modality -> ResponseWorkModalityDTO
                        .builder()
                        .idWorkModality(modality.getId_work_modality())
                        .nameWorkModality(modality.getName_work_modality())
                        .status(modality.isStatus())
                        .build())
                .toList();
    }

    @Override
    public ResponseWorkModalityDTO getWorkModalityById(Long idWorkModality) {
        WorkModality workModality = iWorkModalityRepository.findById(idWorkModality)
                .orElseThrow(() -> new NotFoundEntity("Not found workmodality with id " + idWorkModality));
        return ResponseWorkModalityDTO.builder()
                .idWorkModality(workModality.getId_work_modality())
                .nameWorkModality(workModality.getName_work_modality())
                .status(workModality.isStatus())
                .build();
    }

    @Override
    public ResponseWorkModalityDTO createWorkModality(RequestWorkModalityDTO requestWorkModalityDTO) {
        WorkModality wmByName = iWorkModalityRepository.findByName(requestWorkModalityDTO.getName_work_modality());
        if (wmByName != null) {
            throw new CreatedEntityException("Workmodality already exists");
        }
        WorkModality wm = WorkModality.builder()
                .name_work_modality(requestWorkModalityDTO.getName_work_modality())
                .status(true)
                .build();
        iWorkModalityRepository.save(wm);
        return ResponseWorkModalityDTO.builder()
                .idWorkModality(wm.getId_work_modality())
                .nameWorkModality(wm.getName_work_modality())
                .status(wm.isStatus())
                .build();
    }

    @Override
    public ResponseWorkModalityDTO updateWorkModality(RequestUpdateWorkModalityDTO requestUpdateWorkModalityDTO, Long idWorkModality) {
        WorkModality wmToUpdate = iWorkModalityRepository.findById(idWorkModality)
                .orElseThrow(() -> new NotFoundEntity("Not found workmodality with id " + idWorkModality));
        WorkModality wmByName = iWorkModalityRepository.findByName(requestUpdateWorkModalityDTO.getName_work_modality());
        if (wmByName != null && !wmByName.getId_work_modality().equals(wmToUpdate.getId_work_modality())) {
            throw new UpdateException("Workmodality already exists");
        }
        BeanUtils.copyProperties(requestUpdateWorkModalityDTO, wmToUpdate);
        iWorkModalityRepository.save(wmToUpdate);
        return ResponseWorkModalityDTO.builder()
                .idWorkModality(wmToUpdate.getId_work_modality())
                .nameWorkModality(wmToUpdate.getName_work_modality())
                .status(wmToUpdate.isStatus())
                .build();
    }

    @Override
    public void deleteWorkModality(Long idWorkModality) {
        WorkModality workModality = iWorkModalityRepository.findById(idWorkModality)
                .orElseThrow(() -> new NotFoundEntity("Not found workmodality with id " + idWorkModality));
        workModality.setStatus(false);
        iWorkModalityRepository.save(workModality);
    }

    @Override
    public void destroyWorkModality(Long idWorkModality) {
        WorkModality workModality = iWorkModalityRepository.findById(idWorkModality)
                .orElseThrow(() ->new NotFoundEntity("Not found workmodality with id " + idWorkModality));
        iWorkModalityRepository.delete(workModality);
    }

    @Override
    public void chageStatus(Long idWorkModality) {
        WorkModality workModality = iWorkModalityRepository.findById(idWorkModality)
                .orElseThrow(() -> new  NotFoundEntity("Not found workmodality with id " + idWorkModality));
        workModality.setStatus(!workModality.isStatus());
        iWorkModalityRepository.save(workModality);
    }
}
