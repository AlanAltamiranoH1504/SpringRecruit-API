package com.example.springboot_4_initial.services.interfaces;

import com.example.springboot_4_initial.dto.ListEntityDTO;
import com.example.springboot_4_initial.dto.contractType.CreateContractTypeDTO;
import com.example.springboot_4_initial.dto.contractType.UpdateContractTypeDTO;
import com.example.springboot_4_initial.models.ContractType;

import java.util.List;

public interface IContractTypeService {
    public abstract List<ContractType> findAll(ListEntityDTO listEntityDTO);
    public abstract ContractType save(CreateContractTypeDTO createContractTypeDTO);
    public abstract ContractType update(UpdateContractTypeDTO updateContractTypeDTO, Long idContractType);
    public abstract ContractType findById(Long idContractType);
    public abstract void deleteCotractType(Long idContractType);
    public abstract void destroyContractType(Long idContractType);
}
