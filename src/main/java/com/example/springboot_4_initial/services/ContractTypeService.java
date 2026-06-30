package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.contractType.RequestContractTypeRequest;
import com.example.springboot_4_initial.dto.contractType.RequestUpdateContractType;
import com.example.springboot_4_initial.dto.contractType.ResponseContractTypeDTO;
import com.example.springboot_4_initial.exceptions.CreatedEntityException;
import com.example.springboot_4_initial.exceptions.ListEmptyException;
import com.example.springboot_4_initial.exceptions.NotFoundEntity;
import com.example.springboot_4_initial.exceptions.UpdateException;
import com.example.springboot_4_initial.models.ContractType;
import com.example.springboot_4_initial.repositories.IContractTypeRepository;
import com.example.springboot_4_initial.services.interfaces.IContractTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContractTypeService implements IContractTypeService {
    private final IContractTypeRepository iContractTypeRepository;

    @Override
    public List<ResponseContractTypeDTO> getAllContractTypes(boolean status) {
        List<ContractType> contractTypes = iContractTypeRepository.findAllByStatus(status);
        if (contractTypes.isEmpty()) {
            throw new ListEmptyException("Empty contract types list");
        }
        return contractTypes.stream()
                .map(contract -> {
                    return ResponseContractTypeDTO.builder()
                            .idContractType(contract.getId_contract_type())
                            .nameContractType(contract.getName_contract_type())
                            .status(contract.isStatus())
                            .build();
                }).toList();
    }

    @Override
    public ResponseContractTypeDTO getContractType(Long idContractType) {
        ContractType contractType = iContractTypeRepository.findById(idContractType)
                .orElseThrow(() -> new NotFoundEntity("Not found contract type with id: " + idContractType));
        return ResponseContractTypeDTO.builder()
                .idContractType(contractType.getId_contract_type())
                .nameContractType(contractType.getName_contract_type())
                .status(contractType.isStatus())
                .build();
    }

    @Override
    public ResponseContractTypeDTO saveContractType(RequestContractTypeRequest requestContractTypeRequest) {
        ContractType contractByName = iContractTypeRepository.findByName(requestContractTypeRequest.getNameContractType());
        if (contractByName != null) {
            throw new CreatedEntityException("ContractType already exists");
        }
        ContractType contractToSave = ContractType.builder()
                .name_contract_type(requestContractTypeRequest.getNameContractType())
                .status(true)
                .build();
        iContractTypeRepository.save(contractToSave);
        if (contractToSave.getId_contract_type() == null) {
            throw new CreatedEntityException("Error creating contract type");
        }
        return ResponseContractTypeDTO.builder()
                .idContractType(contractToSave.getId_contract_type())
                .nameContractType(contractToSave.getName_contract_type())
                .status(contractToSave.isStatus())
                .build();
    }

    @Override
    public ResponseContractTypeDTO updateContractType(Long idContractType, RequestUpdateContractType requestUpdateContractType) {
        ContractType contractToUpdate = iContractTypeRepository.findById(idContractType)
                .orElseThrow(() -> new NotFoundEntity("Not found contract type with id: " + idContractType));
        ContractType contractTypeByName = iContractTypeRepository.findByName(requestUpdateContractType.getName_contract_type());
        if (contractTypeByName != null && !contractTypeByName.getName_contract_type().equals(contractToUpdate.getName_contract_type())) {
            throw new UpdateException("Name of type of contract already registered");
        }
        BeanUtils.copyProperties(requestUpdateContractType, contractToUpdate);
        iContractTypeRepository.save(contractToUpdate);
        return ResponseContractTypeDTO.builder()
                .idContractType(contractToUpdate.getId_contract_type())
                .nameContractType(contractToUpdate.getName_contract_type())
                .status(contractToUpdate.isStatus())
                .build();
    }

    @Override
    public void deleteContractType(Long idContractType) {
        ContractType contractType = iContractTypeRepository.findById(idContractType)
                .orElseThrow(() -> new NotFoundEntity("Not found contract type with id: " + idContractType));
        contractType.setStatus(false);
        iContractTypeRepository.save(contractType);
    }

    @Override
    public void changeStatusContractType(Long idContractType) {
        ContractType contractType = iContractTypeRepository.findById(idContractType)
                .orElseThrow(() -> new NotFoundEntity("Not found contract type with id: " + idContractType));
        contractType.setStatus(!contractType.isStatus());
        iContractTypeRepository.save(contractType);
    }

    @Override
    public void destroyContractType(Long idContractType) {
        ContractType contractType = iContractTypeRepository.findById(idContractType)
                .orElseThrow(() -> new NotFoundEntity("Not found contract type with id: " + idContractType));
        iContractTypeRepository.delete(contractType);
    }
}
