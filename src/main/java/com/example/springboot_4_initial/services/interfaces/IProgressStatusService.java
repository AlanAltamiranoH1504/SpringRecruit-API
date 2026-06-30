package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.progressStatus.RequestProgressStatusDTO;
import com.example.springboot_4_initial.dto.progressStatus.RequestUpdateProgressStatusDTO;
import com.example.springboot_4_initial.dto.progressStatus.ResponseProgressStatusDTO;

import java.util.List;

public interface IProgressStatusService {
    public abstract List<ResponseProgressStatusDTO> getAllProgressStatus(boolean status);
    public abstract ResponseProgressStatusDTO getProgressStatus(Long idProgressStatus);
    public abstract ResponseProgressStatusDTO createProgressStatus(RequestProgressStatusDTO requestProgressStatusDTO);
    public abstract ResponseProgressStatusDTO updateProgressStatus(Long idProgress, RequestUpdateProgressStatusDTO requestUpdateProgressStatusDTO);
    public abstract void deleteProgressStatus(Long idProgressStatus);
    public abstract void destroyProgressStatus(Long idProgressStatus);
    public abstract void changeStatus(Long idProgress);
}
