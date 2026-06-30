package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.workModality.RequestUpdateWorkModalityDTO;
import com.example.springboot_4_initial.dto.workModality.RequestWorkModalityDTO;
import com.example.springboot_4_initial.dto.workModality.ResponseWorkModalityDTO;

import java.util.List;

public interface IWorkModalityService {
    public List<ResponseWorkModalityDTO> getAllWorkModality(boolean status);
    public ResponseWorkModalityDTO getWorkModalityById(Long idWorkModality);
    public ResponseWorkModalityDTO createWorkModality(RequestWorkModalityDTO requestWorkModalityDTO);
    public ResponseWorkModalityDTO updateWorkModality(RequestUpdateWorkModalityDTO  requestUpdateWorkModalityDTO, Long idWorkModality);
    public void deleteWorkModality(Long idWorkModality);
    public void destroyWorkModality(Long idWorkModality);
    public void chageStatus(Long idWorkModality);
}
