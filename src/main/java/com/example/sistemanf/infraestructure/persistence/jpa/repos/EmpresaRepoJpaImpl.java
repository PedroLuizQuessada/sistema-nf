package com.example.sistemanf.infraestructure.persistence.jpa.repos;

import com.example.sistemanf.datasources.EmpresaDataSource;
import com.example.sistemanf.dtos.EmpresaDto;
import com.example.sistemanf.infraestructure.persistence.jpa.mappers.EmpresaJpaDtoMapper;
import com.example.sistemanf.infraestructure.persistence.jpa.models.EmpresaJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EmpresaRepoJpaImpl implements EmpresaDataSource {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EmpresaJpaDtoMapper empresaJpaDtoMapper;

    @Override
    public Optional<EmpresaDto> findEmpresaById(Long id) {
        Optional<EmpresaJpa> optionalEmpresaJpa = Optional.ofNullable(entityManager.find(EmpresaJpa.class, id));
        return optionalEmpresaJpa.map(empresaJpaDtoMapper::getDto);
    }
}
