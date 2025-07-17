package com.example.sistemanf.infraestructure.persistence.jpa.repos;

import com.example.sistemanf.datasources.SolicitacaoDataSource;
import com.example.sistemanf.dtos.SolicitacaoDto;
import com.example.sistemanf.enums.OrdenacaoConsultaSolicitacoesEnum;
import com.example.sistemanf.enums.StatusSolicitacaoEnum;
import com.example.sistemanf.infraestructure.persistence.jpa.mappers.SolicitacaoJpaDtoMapper;
import com.example.sistemanf.infraestructure.persistence.jpa.models.SolicitacaoJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Profile("jpa")
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

    @Override
    public List<SolicitacaoDto> consultarSolicitacoes(int page, int size, OrdenacaoConsultaSolicitacoesEnum ordenacao, boolean sentidoOrdenacao,
                                                      Long empresa, Long id, StatusSolicitacaoEnum status, Date dataEmissaoInicio,
                                                      Date dataEmissaoFim, Date dataUploadInicio, Date dataUploadFim, Double valor,
                                                      String nomeFuncionario, String descricao, String cnpjServico) {
        String campoOrdenacaoQuery = !Objects.isNull(ordenacao) ? ordenacao.getCampo() : "id";
        String sentidoOrdenacaoQuery = sentidoOrdenacao ? "ASC" : "DESC";

        Query query = entityManager.createQuery("SELECT solicitacao FROM SolicitacaoJpa solicitacao " +
                "WHERE (solicitacao.funcionario.empresa.id = :empresa) " +
                "AND (:id IS NULL or solicitacao.id = :id) " +
                "AND (:status IS NULL or solicitacao.status = :status) " +
                "AND (:dataEmissaoInicio IS NULL or solicitacao.dataEmissao >= :dataEmissaoInicio) " +
                "AND (:dataEmissaoFim IS NULL or solicitacao.dataEmissao <= :dataEmissaoFim) " +
                "AND (:dataUploadInicio IS NULL or solicitacao.dataUpload >= :dataUploadInicio) " +
                "AND (:dataUploadFim IS NULL or solicitacao.dataUpload <= :dataUploadFim) " +
                "AND (:valor IS NULL or solicitacao.valor = :valor) " +
                "AND (:nomeFuncionario IS NULL or solicitacao.funcionario.nome ILIKE :nomeFuncionario) " +
                "AND (:descricao IS NULL or solicitacao.descricao ILIKE :descricao) " +
                "AND (:cnpjServico IS NULL or solicitacao.cnpjServico ILIKE :cnpjServico) " +
                "ORDER BY  solicitacao." + campoOrdenacaoQuery + " " + sentidoOrdenacaoQuery);
        query.setParameter("empresa", empresa);
        query.setParameter("id", id);
        query.setParameter("status", status);
        query.setParameter("dataEmissaoInicio", dataEmissaoInicio);
        query.setParameter("dataEmissaoFim", dataEmissaoFim);
        query.setParameter("dataUploadInicio", dataUploadInicio);
        query.setParameter("dataUploadFim", dataUploadFim);
        query.setParameter("valor", valor);
        query.setParameter("nomeFuncionario", !Objects.isNull(nomeFuncionario) ? "%" + nomeFuncionario + "%" : null);
        query.setParameter("descricao", !Objects.isNull(descricao) ? "%" + descricao + "%" : null);
        query.setParameter("cnpjServico", !Objects.isNull(cnpjServico) ? "%" + cnpjServico + "%" : null);
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        List<SolicitacaoJpa> solicitacaoJpaList = query.getResultList();
        return solicitacaoJpaList.stream().map(solicitacaoJpa -> solicitacaoJpaDtoMapper.getDto(solicitacaoJpa)).toList();
    }
}
