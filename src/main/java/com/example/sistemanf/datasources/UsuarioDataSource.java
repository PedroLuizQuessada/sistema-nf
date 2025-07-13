package com.example.sistemanf.datasources;

import com.example.sistemanf.dtos.UsuarioDto;

import java.util.Optional;

public interface UsuarioDataSource {
    Optional<UsuarioDto> findUserByEmail(String email);
    Optional<UsuarioDto> findUserById(Long id);
    Long countByNome(String nome);
    Long countByEmail(String email);
    UsuarioDto criarUsuario(UsuarioDto usuarioDto);
    void excluirUsuario(UsuarioDto usuarioDto);
}
