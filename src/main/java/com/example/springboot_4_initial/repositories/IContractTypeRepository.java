package com.example.springboot_4_initial.repositories;

import com.example.springboot_4_initial.models.ContractType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IContractTypeRepository extends JpaRepository<ContractType, Long> {
    @Query("SELECT c FROM ContractType c WHERE c.status = :status")
    List<ContractType> listContractTypes(@Param("status") boolean status);

    @Query("SELECT c FROM ContractType c WHERE c.name_contract_type = :name")
    Optional<ContractType> getByNameContractType(@Param("name") String name);
}
