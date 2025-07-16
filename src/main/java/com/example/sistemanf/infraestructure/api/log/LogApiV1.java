package com.example.sistemanf.infraestructure.api.log;

import com.example.sistemanf.controllers.LogController;
import com.example.sistemanf.controllers.RequesterController;
import com.example.sistemanf.datasources.LogDataSource;
import com.example.sistemanf.datasources.RequesterDataSource;
import com.example.sistemanf.datasources.TokenDataSource;
import com.example.sistemanf.datasources.UsuarioDataSource;
import com.example.sistemanf.dtos.LogDto;
import com.example.sistemanf.dtos.RequesterDto;
import com.example.sistemanf.enums.TipoUsuarioEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(path = "/api/v1/log")
@Tag(name = "Log API V1", description = "Versão 1 do controlador referente a logs")
public class LogApiV1 {

    private final LogController logController;
    private final RequesterController requesterController;

    public LogApiV1(LogDataSource logDataSource, UsuarioDataSource usuarioDataSource,
                    RequesterDataSource requesterDataSource, TokenDataSource tokenDataSource) {
        this.logController = new LogController(logDataSource, usuarioDataSource);
        this.requesterController = new RequesterController(requesterDataSource, tokenDataSource);
    }

    @Operation(summary = "Consultar logs de uma solicitação",
            description = "Requer autenticação",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Logs consultados com sucesso",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = LogDto.class)))),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<List<LogDto>> usuarioConsultarLogsSolicitacao(@AuthenticationPrincipal UserDetails userDetails,
                                                                             @RequestHeader(name = "Authorization", required = false) String token,
                                                                             @PathVariable("id") Long id,
                                                                             @RequestParam("page") int page,
                                                                             @RequestParam("size") int size) {
        RequesterDto requester = getRequester(userDetails, token);
        log.info("Usuário {} consultando logs da solicitação {}", requester.email(), id);
        List<LogDto> logDtoList = logController.consultarLogs(page, size, id, requester.email());
        log.info("Usuário {} consultou {} logs da solicitação {}", requester.email(), logDtoList.size(), id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(logDtoList);
    }

    private RequesterDto getRequester(UserDetails userDetails, String token) {
        return (!Objects.isNull(userDetails)) ?
                requesterController.getRequester(userDetails.getAuthorities().stream().findFirst().isPresent() ?
                        TipoUsuarioEnum.valueOf(String.valueOf(userDetails.getAuthorities().stream().findFirst().get())) : null, userDetails.getUsername()) :
                requesterController.getRequester(token);
    }
}
