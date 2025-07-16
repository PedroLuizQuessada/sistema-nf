package com.example.sistemanf.usecases;

import com.example.sistemanf.entities.Log;
import com.example.sistemanf.entities.Usuario;
import com.example.sistemanf.enums.TipoUsuarioEnum;
import com.example.sistemanf.gateways.LogGateway;
import com.example.sistemanf.gateways.UsuarioGateway;

import java.util.List;
import java.util.Objects;

public class ConsultarLogsUseCase {

    private final LogGateway logGateway;
    private final UsuarioGateway usuarioGateway;

    public ConsultarLogsUseCase(LogGateway logGateway, UsuarioGateway usuarioGateway) {
        this.logGateway = logGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public List<Log> execute(int page, int size, Long solicitacao, String requesterEmail) {
        Usuario usuario = usuarioGateway.findUserByEmail(requesterEmail);
        Long funcionarioId = null;
        Long empresaId = usuario.getEmpresa().getId();

        if (Objects.equals(usuario.getTipo(), TipoUsuarioEnum.FUNCIONARIO))
            funcionarioId = usuario.getId();

        return logGateway.consultarLogs(page, size, solicitacao, empresaId, funcionarioId);
    }
}
