package com.consultoriaTi.gestao.resource;


import com.consultoriaTi.gestao.dto.ReportDTO;
import com.consultoriaTi.gestao.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportResource {

    private final ReportService service;

    @GetMapping()
    @Operation(summary = "Generate report",
            responses = {@ApiResponse(responseCode = "200", description = "Resource successfully retrieved",
                    content = @Content(schema = @Schema(implementation = ReportDTO.class)))})
    public ReportDTO create() {
        return service.create();
    }


    @PostMapping("/pdf")
    @ResponseStatus(CREATED)
    @Operation(summary = "Create report indicated",
            responses = {@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = ReportDTO.class)))})
    public void create(HttpServletResponse response){
        service.createPdf(response);
    }
}
