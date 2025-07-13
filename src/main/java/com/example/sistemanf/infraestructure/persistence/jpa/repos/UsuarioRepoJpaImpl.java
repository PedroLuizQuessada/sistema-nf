package com.example.sistemanf.infraestructure.persistence.jpa.repos;

import com.example.sistemanf.datasources.UsuarioDataSource;
import com.example.sistemanf.dtos.UsuarioDto;
import com.example.sistemanf.infraestructure.persistence.jpa.mappers.UsuarioJpaDtoMapper;
import com.example.sistemanf.infraestructure.persistence.jpa.models.UsuarioJpa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class UsuarioRepoJpaImpl implements UsuarioDataSource {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private UsuarioJpaDtoMapper usuarioJpaDtoMapper;

    @Override
    public Optional<UsuarioDto> findUserByEmail(String email) {
        Query query = entityManager.createQuery("SELECT usuario FROM UsuarioJpa usuario WHERE usuario.email = :email");
        query.setParameter("email", email);
        try {
            UsuarioJpa usuarioJpa = (UsuarioJpa) query.getSingleResult();
            return Optional.ofNullable(usuarioJpaDtoMapper.getDto(usuarioJpa));
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UsuarioDto> findUserById(Long id) {
        Optional<UsuarioJpa> optionalUsuarioJpa = Optional.ofNullable(entityManager.find(UsuarioJpa.class, id));
        return optionalUsuarioJpa.map(usuarioJpaDtoMapper::getDto);
    }

    @Override
    public Long countByNome(String nome) {
        Query query = entityManager.createQuery("SELECT count(*) FROM UsuarioJpa usuario WHERE usuario.nome = :nome");
        query.setParameter("nome", nome);
        return (Long) query.getSingleResult();
    }

    @Override
    public Long countByEmail(String email) {
        Query query = entityManager.createQuery("SELECT count(*) FROM UsuarioJpa usuario WHERE usuario.email = :email");
        query.setParameter("email", email);
        return (Long) query.getSingleResult();
    }

    @Override
    @Transactional
    public UsuarioDto criarUsuario(UsuarioDto usuarioDto) {
        UsuarioJpa usuarioJpa = usuarioJpaDtoMapper.getJpa(usuarioDto);
        usuarioJpa = entityManager.merge(usuarioJpa);
        return usuarioJpaDtoMapper.getDto(usuarioJpa);
    }

    @Override
    @Transactional
    public void excluirUsuario(UsuarioDto usuarioDto) {
        Query query = entityManager.createQuery("DELETE FROM UsuarioJpa usuario WHERE usuario.id = :id");
        query.setParameter("id", usuarioDto.id());
        query.executeUpdate();
    }
}
