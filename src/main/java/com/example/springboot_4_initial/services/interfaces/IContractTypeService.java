package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.contractType.RequestContractTypeRequest;
import com.example.springboot_4_initial.dto.contractType.RequestUpdateContractType;
import com.example.springboot_4_initial.dto.contractType.ResponseContractTypeDTO;
import com.example.springboot_4_initial.models.ContractType;

import java.util.List;

public interface IContractTypeService {
    public abstract List<ResponseContractTypeDTO> getAllContractTypes(boolean status);
    public abstract ResponseContractTypeDTO getContractType(Long idContractType);
    public abstract ResponseContractTypeDTO saveContractType(RequestContractTypeRequest requestContractTypeRequest);
    public abstract ResponseContractTypeDTO updateContractType(Long idContractType, RequestUpdateContractType requestUpdateContractType);
    public abstract void deleteContractType(Long idContractType);
    public abstract void changeStatusContractType(Long idContractType);
    public abstract void destroyContractType(Long idContractType);
}
