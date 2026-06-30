package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.progressStatus.RequestProgressStatusDTO;
import com.example.springboot_4_initial.dto.progressStatus.RequestUpdateProgressStatusDTO;
import com.example.springboot_4_initial.dto.progressStatus.ResponseProgressStatusDTO;
import com.example.springboot_4_initial.exceptions.CreatedEntityException;
import com.example.springboot_4_initial.exceptions.ListEmptyException;
import com.example.springboot_4_initial.exceptions.NotFoundEntity;
import com.example.springboot_4_initial.exceptions.UpdateException;
import com.example.springboot_4_initial.models.ProgressStatus;
import com.example.springboot_4_initial.repositories.IProgressStatusRepository;
import com.example.springboot_4_initial.services.interfaces.IProgressStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProgressStatusService implements IProgressStatusService {
    private final IProgressStatusRepository iProgressStatusRepository;

    @Override
    public List<ResponseProgressStatusDTO> getAllProgressStatus(boolean status) {
        List<ProgressStatus> progressStatuses = iProgressStatusRepository.findAllByStatus(status);
        if (progressStatuses.isEmpty()) {
            throw new ListEmptyException("Empty list progress status");
        }
        return progressStatuses.stream()
                .map(progress -> {
                    return ResponseProgressStatusDTO.builder()
                            .idProgressStatus(progress.getId_progress_status())
                            .nameProgressStatus(progress.getName_progress_status())
                            .descriptionProgressStatus(progress.getDescription_progress_status())
                            .status(progress.isStatus())
                            .build();
                }).toList();
    }

    @Override
    public ResponseProgressStatusDTO getProgressStatus(Long idProgressStatus) {
        ProgressStatus progressStatus = iProgressStatusRepository.findById(idProgressStatus)
                .orElseThrow(() -> new NotFoundEntity("Not Found Progress Status with id " + idProgressStatus));
        return ResponseProgressStatusDTO.builder()
                .idProgressStatus(progressStatus.getId_progress_status())
                .nameProgressStatus(progressStatus.getName_progress_status())
                .descriptionProgressStatus(progressStatus.getDescription_progress_status())
                .status(progressStatus.isStatus())
                .build();
    }

    @Override
    public ResponseProgressStatusDTO createProgressStatus(RequestProgressStatusDTO requestProgressStatusDTO) {
        ProgressStatus progressByName = iProgressStatusRepository.findByName(requestProgressStatusDTO.getName_progress_status());
        if (progressByName != null) {
            throw new CreatedEntityException("Progress status already exists");
        }
        ProgressStatus progressToSave = ProgressStatus.builder()
                .name_progress_status(requestProgressStatusDTO.getName_progress_status())
                .description_progress_status(requestProgressStatusDTO.getDescription_progress_status())
                .status(true)
                .build();
        iProgressStatusRepository.save(progressToSave);
        return ResponseProgressStatusDTO.builder()
                .idProgressStatus(progressToSave.getId_progress_status())
                .nameProgressStatus(progressToSave.getName_progress_status())
                .descriptionProgressStatus(progressToSave.getDescription_progress_status())
                .status(progressToSave.isStatus())
                .build();
    }

    @Override
    public ResponseProgressStatusDTO updateProgressStatus(Long idProgress, RequestUpdateProgressStatusDTO requestUpdateProgressStatusDTO) {
        ProgressStatus progressToUpdate = iProgressStatusRepository.findById(idProgress)
                .orElseThrow(() -> new NotFoundEntity("Not Found Progress Status with id " + idProgress));
        ProgressStatus progressByName = iProgressStatusRepository.findByName(requestUpdateProgressStatusDTO.getName_progress_status());
        if (progressByName != null && !progressByName.getId_progress_status().equals(progressToUpdate.getId_progress_status())) {
            throw new UpdateException("Progress status already exists");
        }
        BeanUtils.copyProperties(requestUpdateProgressStatusDTO, progressToUpdate);
        iProgressStatusRepository.save(progressToUpdate);
        return ResponseProgressStatusDTO.builder()
                .idProgressStatus(progressToUpdate.getId_progress_status())
                .nameProgressStatus(progressToUpdate.getName_progress_status())
                .descriptionProgressStatus(progressToUpdate.getDescription_progress_status())
                .status(progressToUpdate.isStatus())
                .build();
    }

    @Override
    public void deleteProgressStatus(Long idProgressStatus) {
        ProgressStatus progressStatus = iProgressStatusRepository.findById(idProgressStatus)
                .orElseThrow(() -> new NotFoundEntity("Not Found Progress Status with id " + idProgressStatus));
        progressStatus.setStatus(false);
        iProgressStatusRepository.save(progressStatus);
    }

    @Override
    public void destroyProgressStatus(Long idProgressStatus) {
        ProgressStatus progressStatus = iProgressStatusRepository.findById(idProgressStatus)
                .orElseThrow(() -> new NotFoundEntity("Not Found Progress Status with id " + idProgressStatus));
        iProgressStatusRepository.delete(progressStatus);
    }

    @Override
    public void changeStatus(Long idProgress) {
        ProgressStatus progressStatus = iProgressStatusRepository.findById(idProgress)
                .orElseThrow(() -> new NotFoundEntity("Not Found Progress Status with id " + idProgress));
        progressStatus.setStatus(!progressStatus.isStatus());
        iProgressStatusRepository.save(progressStatus);
    }
}
