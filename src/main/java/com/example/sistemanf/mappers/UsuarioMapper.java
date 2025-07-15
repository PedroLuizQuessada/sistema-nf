package com.example.sistemanf.mappers;

import com.example.sistemanf.dtos.LoginDto;
import com.example.sistemanf.dtos.UsuarioDto;
import com.example.sistemanf.dtos.requests.CriarUsuarioFuncionarioRequest;
import com.example.sistemanf.entities.Empresa;
import com.example.sistemanf.entities.Usuario;
import com.example.sistemanf.enums.TipoUsuarioEnum;

import java.util.Objects;

public class UsuarioMapper {

    private UsuarioMapper(){}

    public static  Usuario getEntidade(CriarUsuarioFuncionarioRequest request, Empresa empresa, TipoUsuarioEnum tipo) {
        return new Usuario(null, empresa, request.nome(), tipo, request.email(), request.senha(), null, true, true);
    }

    public static Usuario getEntidade(UsuarioDto usuarioDto, boolean criptografar) {
        return new Usuario(usuarioDto.id(),
                !Objects.isNull(usuarioDto.empresa()) ? EmpresaMapper.getEntidade(usuarioDto.empresa()) : null,
                usuarioDto.nome(), usuarioDto.tipo(), usuarioDto.email(), usuarioDto.senha(), usuarioDto.dataCriacao(),
                criptografar, usuarioDto.ativo());
    }

    public static UsuarioDto getDto(Usuario usuario) {
        return new UsuarioDto(usuario.getId(),
                !Objects.isNull(usuario.getEmpresa()) ? EmpresaMapper.getDto(usuario.getEmpresa()) : null,
                usuario.getNome(), usuario.getTipo(), usuario.getEmail(), usuario.getSenha(), usuario.getDataCriacao(), usuario.getAtivo());
    }

    public static UsuarioDto getResponse(Usuario usuario) {
        return new UsuarioDto(usuario.getId(),
                !Objects.isNull(usuario.getEmpresa()) ? EmpresaMapper.getDto(usuario.getEmpresa()) : null,
                usuario.getNome(), usuario.getTipo(), usuario.getEmail(), null, usuario.getDataCriacao(), usuario.getAtivo());
    }

    public static LoginDto getLoginDto(Usuario usuario) {
        return new LoginDto(usuario.getEmail(), usuario.getSenha(), usuario.getTipo());
    }
}
