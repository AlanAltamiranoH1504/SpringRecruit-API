package com.example.springboot_4_initial.services;

import com.example.springboot_4_initial.dto.ListEntityDTO;
import com.example.springboot_4_initial.dto.contractType.CreateContractTypeDTO;
import com.example.springboot_4_initial.dto.contractType.UpdateContractTypeDTO;
import com.example.springboot_4_initial.exceptions.CreatedEntityException;
import com.example.springboot_4_initial.exceptions.ListEmptyException;
import com.example.springboot_4_initial.exceptions.contractType.NameContractTypeError;
import com.example.springboot_4_initial.exceptions.vancacies.NotFoundEntityException;
import com.example.springboot_4_initial.models.ContractType;
import com.example.springboot_4_initial.repositories.IContractTypeRepository;
import com.example.springboot_4_initial.services.interfaces.IContractTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractTypeService implements IContractTypeService {
    @Autowired
    private IContractTypeRepository iContractTypeRepository;

    @Override
    public List<ContractType> findAll(ListEntityDTO listEntityDTO) {
        List<ContractType> contractTypesList = iContractTypeRepository.listContractTypes(listEntityDTO.getStatus());
        if (contractTypesList.isEmpty()) {
            throw new ListEmptyException("No existen tipos de contratos registrados en la base de datos");
        }
        return contractTypesList;
    }

    @Override
    public ContractType save(CreateContractTypeDTO createContractTypeDTO) {
        Optional<ContractType> nameInUse = iContractTypeRepository.getByNameContractType(createContractTypeDTO.getName_contract_type());
        if (nameInUse.isPresent()) {
            throw new CreatedEntityException("El nombre del tipo de contrato ya existe en la base de datos");
        }
        ContractType contractTypeToSave = new ContractType(createContractTypeDTO.getName_contract_type(), true);
        iContractTypeRepository.save(contractTypeToSave);
        return contractTypeToSave;
    }

    @Override
    public ContractType update(UpdateContractTypeDTO updateContractTypeDTO, Long idContractType) {
        ContractType contractTypeToUpdate = this.findById(idContractType);
        Optional<ContractType> nameInUse = iContractTypeRepository.getByNameContractType(updateContractTypeDTO.getName_contract_type());
        if (nameInUse.isPresent() && nameInUse.get().getId_contract_type() != idContractType) {
            throw new NameContractTypeError("El nombre del tipo de contrato ya existe en la base de datos");
        }

        BeanUtils.copyProperties(updateContractTypeDTO, contractTypeToUpdate);
        iContractTypeRepository.save(contractTypeToUpdate);
        return contractTypeToUpdate;
    }

    @Override
    public ContractType findById(Long idContractType) {
        Optional<ContractType> contractTypeToShow = iContractTypeRepository.findById(idContractType);
        if (contractTypeToShow.isEmpty()) {
            throw new NotFoundEntityException("No existe un tipo de contrato registrado con el id " + idContractType);
        }
        return contractTypeToShow.get();
    }

    @Override
    public void deleteCotractType(Long idContractType) {
        ContractType contractTypeToDelete = this.findById(idContractType);
        contractTypeToDelete.setStatus(false);
        iContractTypeRepository.save(contractTypeToDelete);
    }

    @Override
    public void destroyContractType(Long idContractType) {
        ContractType contractTypeToDelete = this.findById(idContractType);
        iContractTypeRepository.delete(contractTypeToDelete);
    }
}
