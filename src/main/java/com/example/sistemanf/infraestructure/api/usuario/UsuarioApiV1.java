package com.example.sistemanf.infraestructure.api.usuario;

import com.example.sistemanf.controllers.RequesterController;
import com.example.sistemanf.controllers.UsuarioController;
import com.example.sistemanf.datasources.RequesterDataSource;
import com.example.sistemanf.datasources.TokenDataSource;
import com.example.sistemanf.datasources.UsuarioDataSource;
import com.example.sistemanf.dtos.RequesterDto;
import com.example.sistemanf.dtos.TokenDto;
import com.example.sistemanf.dtos.UsuarioDto;
import com.example.sistemanf.dtos.requests.CriarUsuarioFuncionarioRequest;
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
@RequestMapping(path = "/api/v1/usuario")
@Tag(name = "Usuário API V1", description = "Versão 1 do controlador referente a usuários")
public class UsuarioApiV1 {

    private final UsuarioController usuarioController;
    private final RequesterController requesterController;

    public UsuarioApiV1(UsuarioDataSource usuarioDataSource, RequesterDataSource requesterDataSource, TokenDataSource tokenDataSource) {
        this.usuarioController = new UsuarioController(usuarioDataSource, tokenDataSource);
        this.requesterController = new RequesterController(requesterDataSource, tokenDataSource);
    }

    @Operation(summary = "Gera token de acesso",
            description = "Requer autenticação",
            security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Token gerado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TokenDto.class))),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),

            @ApiResponse(responseCode = "500",
                    description = "Falha ao gerar token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @GetMapping("/gerar-token")
    public ResponseEntity<TokenDto> usuarioGerarToken(@AuthenticationPrincipal UserDetails userDetails,
                                                       @RequestHeader(name = "Authorization", required = false) String token) {
        RequesterDto requester = getRequester(userDetails, token);
        log.info("Usuário {} gerando token de acesso", requester.email());
        TokenDto tokenResponse = usuarioController.gerarToken(requester.tipo(), requester.email());
        log.info("Usuário {} gerou token de acesso", tokenResponse.email());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tokenResponse);
    }

    @Operation(summary = "Cria um usuário funcionário",
            description = "Requer autenticação e tipo de usuário 'GERENTE'",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "201",
                    description = "Usuário funcionário criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class))),
            @ApiResponse(responseCode = "400",
                    description = "Valores inválidos para o usuário funcionário a ser criado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403",
                    description = "Usuário autenticado não é 'GERENTE'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @PostMapping
    public ResponseEntity<UsuarioDto> usuarioGerenteCriarUsuarioFuncionario(@AuthenticationPrincipal UserDetails userDetails,
                                                   @RequestHeader(name = "Authorization", required = false) String token,
                                                   @RequestBody @Valid CriarUsuarioFuncionarioRequest request) {
        RequesterDto requester = getRequester(userDetails, token);
        log.info("Usuário gerente {} criando usuário funcionário: {}", requester.email(), request.nome());
        UsuarioDto usuario = usuarioController.criarUsuarioFuncionario(request, requester.email());
        log.info("Usuário gerente {} criou usuário funcionário: {}", requester.email(), usuario.nome());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuario);
    }

    @Operation(summary = "Exclui um usuário",
            description = "Requer autenticação e tipo de usuário 'GERENTE'",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204",
                    description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403",
                    description = "Usuário autenticado não é 'GERENTE'",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "404",
                    description = "Usuário a ser excluido não encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> usuarioGerenteExcluirUsuario(@AuthenticationPrincipal UserDetails userDetails,
                                       @RequestHeader(name = "Authorization", required = false) String token,
                                       @PathVariable("id") Long id) {
        RequesterDto requester = getRequester(userDetails, token);
        log.info("Usuário gerente {} excluindo usuário: {}", requester.email(), id);
        usuarioController.excluirUsuario(id, requester.email());
        log.info("Usuário gerente {} excluiu usuário: {}", requester.email(), id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT).build();
    }

    private RequesterDto getRequester(UserDetails userDetails, String token) {
        return (!Objects.isNull(userDetails)) ?
                requesterController.getRequester(userDetails.getAuthorities().stream().findFirst().isPresent() ?
                        TipoUsuarioEnum.valueOf(String.valueOf(userDetails.getAuthorities().stream().findFirst().get())) : null, userDetails.getUsername()) :
                requesterController.getRequester(token);
    }
}
