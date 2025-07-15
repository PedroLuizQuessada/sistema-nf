package com.example.sistemanf.infraestructure.persistence.jpa.repos;

import com.example.sistemanf.datasources.SolicitacaoDataSource;
import com.example.sistemanf.dtos.SolicitacaoDto;
import com.example.sistemanf.enums.StatusSolicitacaoEnum;
import com.example.sistemanf.infraestructure.persistence.jpa.mappers.SolicitacaoJpaDtoMapper;
import com.example.sistemanf.infraestructure.persistence.jpa.models.SolicitacaoJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class SolicitacaoRepoJpaImpl implements SolicitacaoDataSource {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SolicitacaoJpaDtoMapper solicitacaoJpaDtoMapper;

    @Override
    @Transactional
    public SolicitacaoDto criarSolicitacao(SolicitacaoDto solicitacaoDto) {
        SolicitacaoJpa solicitacaoJpa = solicitacaoJpaDtoMapper.getJpa(solicitacaoDto);
        solicitacaoJpa = entityManager.merge(solicitacaoJpa);
        return solicitacaoJpaDtoMapper.getDto(solicitacaoJpa);
    }

    @Override
    public Optional<SolicitacaoDto> findSolicitacaoByIdAndEmailFuncionario(Long id, String email) {
        Query query = entityManager.createQuery("SELECT solicitacao FROM SolicitacaoJpa solicitacao WHERE solicitacao.id = :id AND solicitacao.funcionario.email = :email");
        query.setParameter("id", id);
        query.setParameter("email", email);
        try {
            SolicitacaoJpa solicitacaoJpa = (SolicitacaoJpa) query.getSingleResult();
            return Optional.ofNullable(solicitacaoJpaDtoMapper.getDto(solicitacaoJpa));
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<SolicitacaoDto> findSolicitacaoById(Long id) {
        Optional<SolicitacaoJpa> optionalSolicitacaoJpa = Optional.ofNullable(entityManager.find(SolicitacaoJpa.class, id));
        return optionalSolicitacaoJpa.map(solicitacaoJpaDtoMapper::getDto);
    }

    @Override
    @Transactional
    public void cancelarSolicitacao(SolicitacaoDto solicitacaoDto) {
        Query query = entityManager.createQuery("UPDATE SolicitacaoJpa solicitacao SET solicitacao.status = :status WHERE solicitacao.id = :id");
        query.setParameter("status", StatusSolicitacaoEnum.CANCELADO);
        query.setParameter("id", solicitacaoDto.id());
        query.executeUpdate();
    }

    @Override
    @Transactional
    public SolicitacaoDto atualizarSolicitacao(SolicitacaoDto solicitacaoDto) {
        SolicitacaoJpa solicitacaoJpa = solicitacaoJpaDtoMapper.getJpa(solicitacaoDto);
        solicitacaoJpa = entityManager.merge(solicitacaoJpa);
        return solicitacaoJpaDtoMapper.getDto(solicitacaoJpa);
    }
}
