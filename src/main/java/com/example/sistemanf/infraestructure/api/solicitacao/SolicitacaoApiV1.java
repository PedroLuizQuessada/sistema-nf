package com.example.sistemanf.infraestructure.api.solicitacao;

import com.example.sistemanf.controllers.RequesterController;
import com.example.sistemanf.controllers.SolicitacaoController;
import com.example.sistemanf.datasources.*;
import com.example.sistemanf.dtos.RequesterDto;
import com.example.sistemanf.dtos.SolicitacaoDto;
import com.example.sistemanf.dtos.UsuarioDto;
import com.example.sistemanf.dtos.requests.AtualizarStatusSolicitacaoRequest;
import com.example.sistemanf.dtos.requests.ConsultarSolicitacoesRequest;
import com.example.sistemanf.dtos.requests.UploadNotaFiscalRequest;
import com.example.sistemanf.enums.TipoUsuarioEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/solicitacao")
@Tag(name = "Usuário API V1", description = "Versão 1 do controlador referente a solicitações")
public class SolicitacaoApiV1 {

    private final SolicitacaoController solicitacaoController;
    private final RequesterController requesterController;

    public SolicitacaoApiV1(NotaFiscalDataSource notaFiscalDataSource, SolicitacaoDataSource solicitacaoDataSource, UsuarioDataSource usuarioDataSource,
                            RequesterDataSource requesterDataSource, TokenDataSource tokenDataSource, LogDataSource logDataSource) {
        this.solicitacaoController = new SolicitacaoController(notaFiscalDataSource, solicitacaoDataSource, usuarioDataSource, logDataSource);
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
    public ResponseEntity<SolicitacaoDto> usuarioFuncionarioUploadNotaFiscal(@AuthenticationPrincipal UserDetails userDetails,
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

    @Operation(summary = "Cancela solicitação",
            description = "Requer autenticação e tipo de usuário 'FUNCIONARIO'",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204",
                    description = "Solicitação cancelada com sucesso"),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403",
                    description = "Usuário autenticado não é 'FUNCIONARIO'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404",
                    description = "Solicitação a ser cancelada não encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PutMapping("/cancelar/{id}")
    public ResponseEntity<Void> usuarioFuncionarioCancelarSolicitacao(@AuthenticationPrincipal UserDetails userDetails,
                                                             @RequestHeader(name = "Authorization", required = false) String token,
                                                             @PathVariable("id") Long id) {
        RequesterDto requester = getRequester(userDetails, token);
        log.info("Usuário funcionário {} cancelando solicitação: {}", requester.email(), id);
        solicitacaoController.cancelarSolicitacao(id, requester.email());
        log.info("Usuário funcionário {} cancelou usuário: {}", requester.email(), id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Atualizar status de solicitação",
            description = "Requer autenticação e tipo de usuário 'GERENTE'",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Status da solicitação atualizado com sucesso"),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403",
                    description = "Usuário autenticado não é 'GERENTE'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404",
                    description = "Solicitação a ter seu status atualizado não encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PutMapping("/atualizar-status/{id}")
    public ResponseEntity<SolicitacaoDto> usuarioGerenteAtualizarStatusSolicitacao(@AuthenticationPrincipal UserDetails userDetails,
                                                                      @RequestHeader(name = "Authorization", required = false) String token,
                                                                      @PathVariable("id") Long id,
                                                                      @RequestBody @Valid AtualizarStatusSolicitacaoRequest request) {
        RequesterDto requester = getRequester(userDetails, token);
        log.info("Usuário gerente {} atualizando status da solicitação {} para: {}", requester.email(), id, request.status());
        SolicitacaoDto solicitacao = solicitacaoController.atualizarStatusSolicitacao(request, id, requester.email());
        log.info("Usuário gerente {} atualizou status da solicição {} para: {}", requester.email(), id, solicitacao.status());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(solicitacao);
    }

    @Operation(summary = "Consultar solicitações",
            description = "Requer autenticação",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Solicitações consultadas com sucesso",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = SolicitacaoDto.class)))),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @GetMapping()
    public ResponseEntity<List<SolicitacaoDto>> usuarioConsultarSolicitacoes(@AuthenticationPrincipal UserDetails userDetails,
                                                                                        @RequestHeader(name = "Authorization", required = false) String token,
                                                                                        @RequestBody @Valid ConsultarSolicitacoesRequest request) {
        RequesterDto requester = getRequester(userDetails, token);
        log.info("Usuário {} consultando solicitações", requester.email());
        List<SolicitacaoDto> solicitacaoDtoList = solicitacaoController.consultarSolicitacoes(request, requester.email());
        log.info("Usuário {} consultou {} solicitações", requester.email(), solicitacaoDtoList.size());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(solicitacaoDtoList);
    }

    private RequesterDto getRequester(UserDetails userDetails, String token) {
        return (!Objects.isNull(userDetails)) ?
                requesterController.getRequester(userDetails.getAuthorities().stream().findFirst().isPresent() ?
                        TipoUsuarioEnum.valueOf(String.valueOf(userDetails.getAuthorities().stream().findFirst().get())) : null, userDetails.getUsername()) :
                requesterController.getRequester(token);
    }
}
