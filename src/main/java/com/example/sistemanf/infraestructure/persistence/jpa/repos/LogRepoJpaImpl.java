package com.example.sistemanf.infraestructure.persistence.jpa.repos;

import com.example.sistemanf.datasources.LogDataSource;
import com.example.sistemanf.dtos.LogDto;
import com.example.sistemanf.infraestructure.persistence.jpa.mappers.LogJpaDtoMapper;
import com.example.sistemanf.infraestructure.persistence.jpa.models.LogJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Profile("jpa")
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

    @Override
    public List<LogDto> consultarLogs(int page, int size, Long solicitacao, Long empresa, Long funcionario) {
        Query query = entityManager.createQuery("SELECT log FROM LogJpa log " +
                "WHERE (log.solicitacao.id = :solicitacao) AND (log.solicitacao.funcionario.empresa.id = :empresa) " +
                "AND (:funcionario IS NULL or log.solicitacao.funcionario.id = :funcionario) " +
                "ORDER BY  log.id DESC");
        query.setParameter("solicitacao", solicitacao);
        query.setParameter("empresa", empresa);
        query.setParameter("funcionario", funcionario);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        List<LogJpa> logJpaList = query.getResultList();
        return logJpaList.stream().map(logJpa -> logJpaDtoMapper.getDto(logJpa)).toList();
    }
}
