package com.example.sistemanf.infraestructure.persistence.jpa.repos;

import com.example.sistemanf.datasources.SolicitacaoNovaSenhaDataSource;
import com.example.sistemanf.dtos.SolicitacaoNovaSenhaDto;
import com.example.sistemanf.infraestructure.persistence.jpa.mappers.SolicitacaoNovaSenhaJpaDtoMapper;
import com.example.sistemanf.infraestructure.persistence.jpa.models.SolicitacaoNovaSenhaJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SolicitacaoNovaSenhaRepoJpaImpl implements SolicitacaoNovaSenhaDataSource {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SolicitacaoNovaSenhaJpaDtoMapper solicitacaoNovaSenhaJpaDtoMapper;

    @Override
    @Transactional
    public SolicitacaoNovaSenhaDto criarSolicitacaoNovaSenha(SolicitacaoNovaSenhaDto solicitacaoNovaSenhaDto) {
        SolicitacaoNovaSenhaJpa solicitacaoNovaSenhaJpa = solicitacaoNovaSenhaJpaDtoMapper.getJpa(solicitacaoNovaSenhaDto);
        solicitacaoNovaSenhaJpa = entityManager.merge(solicitacaoNovaSenhaJpa);
        return solicitacaoNovaSenhaJpaDtoMapper.getDto(solicitacaoNovaSenhaJpa);
    }
}
