package com.example.sistemanf.infraestructure.persistence.jpa.repos;

import com.example.sistemanf.datasources.LogDataSource;
import com.example.sistemanf.dtos.LogDto;
import com.example.sistemanf.infraestructure.persistence.jpa.mappers.LogJpaDtoMapper;
import com.example.sistemanf.infraestructure.persistence.jpa.models.LogJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LogRepoJpaImpl implements LogDataSource {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private LogJpaDtoMapper logJpaDtoMapper;

    @Override
    @Transactional
    public void criarLog(LogDto logDto) {
        LogJpa logJpa = logJpaDtoMapper.getJpa(logDto);
        entityManager.merge(logJpa);
    }
}
