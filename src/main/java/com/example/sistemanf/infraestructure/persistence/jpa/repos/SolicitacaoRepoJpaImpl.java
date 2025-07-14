package com.example.sistemanf.infraestructure.persistence.jpa.repos;

import com.example.sistemanf.datasources.SolicitacaoDataSource;
import com.example.sistemanf.dtos.SolicitacaoDto;
import com.example.sistemanf.infraestructure.persistence.jpa.mappers.SolicitacaoJpaDtoMapper;
import com.example.sistemanf.infraestructure.persistence.jpa.models.SolicitacaoJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SolicitacaoRepoJpaImpl implements SolicitacaoDataSource {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SolicitacaoJpaDtoMapper solicitacaoJpaDtoMapper;

    @Override
    public Long selectMaxId() {
        Query query = entityManager.createQuery("SELECT max(solicitacao.id) FROM SolicitacaoJpa solicitacao");
        return (Long) query.getSingleResult(); //TODO mudar isso pra email_DD_MM_YYYY_24HH_mm_sss.png
    }

    @Override
    @Transactional
    public SolicitacaoDto criarSolicitacao(SolicitacaoDto solicitacaoDto) {
        SolicitacaoJpa solicitacaoJpa = solicitacaoJpaDtoMapper.getJpa(solicitacaoDto);
        solicitacaoJpa = entityManager.merge(solicitacaoJpa);
        return solicitacaoJpaDtoMapper.getDto(solicitacaoJpa);
    }
}
