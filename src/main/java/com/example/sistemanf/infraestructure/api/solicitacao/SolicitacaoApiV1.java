package com.example.sistemanf.infraestructure.api.solicitacao;

import com.example.sistemanf.controllers.RequesterController;
import com.example.sistemanf.controllers.SolicitacaoController;
import com.example.sistemanf.datasources.*;
import com.example.sistemanf.dtos.RequesterDto;
import com.example.sistemanf.dtos.SolicitacaoDto;
import com.example.sistemanf.dtos.UsuarioDto;
import com.example.sistemanf.dtos.requests.UploadNotaFiscalRequest;
import com.example.sistemanf.enums.TipoUsuarioEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/solicitacao")
@Tag(name = "Usuário API V1", description = "Versão 1 do controlador referente a solicitações")
public class SolicitacaoApiV1 {

    private final SolicitacaoController solicitacaoController;
    private final RequesterController requesterController;

    public SolicitacaoApiV1(NotaFiscalDataSource notaFiscalDataSource, SolicitacaoDataSource solicitacaoDataSource, UsuarioDataSource usuarioDataSource,
                            RequesterDataSource requesterDataSource, TokenDataSource tokenDataSource) {
        this.solicitacaoController = new SolicitacaoController(notaFiscalDataSource, solicitacaoDataSource, usuarioDataSource);
        this.requesterController = new RequesterController(requesterDataSource, tokenDataSource);
    }

    @Operation(summary = "Upload de nota fiscal",
            description = "Requer autenticação e tipo de usuário 'FUNCIONARIO'",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "Nota fiscal submetida com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class))),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403",
                    description = "Usuário autenticado não é 'FUNCIONARIO'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "500",
                    description = "Falha ao gerar arquivo da nota fical a ser submetida",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping("/upload-nf")
    public ResponseEntity<SolicitacaoDto> uploadNotaFiscal(@AuthenticationPrincipal UserDetails userDetails,
                                                                                @RequestHeader(name = "Authorization", required = false) String token,
                                                                                @RequestBody @Valid UploadNotaFiscalRequest request) {
        RequesterDto requester = getRequester(userDetails, token);
        log.info("Usuário funcionário {} submetendo nota fiscal", requester.email());
        SolicitacaoDto solicitacao = solicitacaoController.uploadNotaFiscal(request, requester.email());
        log.info("Usuário funcionário {} submeteu nota fiscal: {}", requester.email(), solicitacao.id());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(solicitacao);
    }

    private RequesterDto getRequester(UserDetails userDetails, String token) {
        return (!Objects.isNull(userDetails)) ?
                requesterController.getRequester(userDetails.getAuthorities().stream().findFirst().isPresent() ?
                        TipoUsuarioEnum.valueOf(String.valueOf(userDetails.getAuthorities().stream().findFirst().get())) : null, userDetails.getUsername()) :
                requesterController.getRequester(token);
    }
}
