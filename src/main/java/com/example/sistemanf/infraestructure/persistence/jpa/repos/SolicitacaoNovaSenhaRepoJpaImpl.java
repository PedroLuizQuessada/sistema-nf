package com.example.sistemanf.infraestructure.persistence.jpa.repos;

import com.example.sistemanf.datasources.SolicitacaoNovaSenhaDataSource;
import com.example.sistemanf.dtos.SolicitacaoNovaSenhaDto;
import com.example.sistemanf.infraestructure.persistence.jpa.mappers.SolicitacaoNovaSenhaJpaDtoMapper;
import com.example.sistemanf.infraestructure.persistence.jpa.models.SolicitacaoNovaSenhaJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Override
    public Optional<SolicitacaoNovaSenhaDto> findSolicitacaoNovaSenhaAtivaByCodigoAndUsuarioId(String codigo, Long usuarioId) {
        Query query = entityManager.createQuery("SELECT solicitacaoNovaSenha FROM SolicitacaoNovaSenhaJpa solicitacaoNovaSenha " +
                "WHERE solicitacaoNovaSenha.dataExpiracao > CURRENT_TIMESTAMP AND solicitacaoNovaSenha.ativo = TRUE AND " +
                "solicitacaoNovaSenha.codigo = :codigo AND solicitacaoNovaSenha.usuario.id = :usuarioId");
        query.setParameter("codigo", codigo);
        query.setParameter("usuarioId", usuarioId);
        try {
            SolicitacaoNovaSenhaJpa solicitacaoNovaSenhaJpa = (SolicitacaoNovaSenhaJpa) query.getSingleResult();
            return Optional.ofNullable(solicitacaoNovaSenhaJpaDtoMapper.getDto(solicitacaoNovaSenhaJpa));
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void consumirSolicitacaoNovaSenha(Long id) {
        Query query = entityManager.createQuery("UPDATE SolicitacaoNovaSenhaJpa solicitacaoNovaSenha SET solicitacaoNovaSenha.ativo = FALSE WHERE solicitacaoNovaSenha.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
