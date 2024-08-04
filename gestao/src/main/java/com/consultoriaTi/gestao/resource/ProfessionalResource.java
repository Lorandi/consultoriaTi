package com.consultoriaTi.gestao.resource;


import com.consultoriaTi.gestao.dto.ProfessionalCreateDTO;
import com.consultoriaTi.gestao.dto.ProfessionalDTO;
import com.consultoriaTi.gestao.dto.ProfessionalUpdateDTO;
import com.consultoriaTi.gestao.enums.ProfessionalStatusEnum;
import com.consultoriaTi.gestao.service.ProfessionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/professional")
@RequiredArgsConstructor
@Tag(name = "Professional")
public class ProfessionalResource {

    private final ProfessionalService service;

    @GetMapping("/{id}")
    @Operation(summary = "Search Professional by id",
            responses = {@ApiResponse(responseCode = "200", description = "Resource successfully retrieved",
                    content = @Content(schema = @Schema(implementation = ProfessionalDTO.class)))})
    public ProfessionalDTO findById(@Valid @PathVariable Long id) {
        return service.findDTOById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Create professional",
            responses = {@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = ProfessionalDTO.class)))})
    public ProfessionalDTO create(@Valid @RequestBody ProfessionalCreateDTO requestDTO) {
        return service.create(requestDTO);
    }

    @PutMapping
    @Operation(summary = "Update Professional by id",
            responses = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ProfessionalDTO.class)))})
    public ProfessionalDTO update(@Valid @RequestBody ProfessionalUpdateDTO updateDTO) {
        return service.update(updateDTO);
    }

    @GetMapping
    @Operation(summary = "Find all Professionals",
            responses = {@ApiResponse(responseCode = "200",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ProfessionalDTO.class))))})
    public List<ProfessionalDTO> findAll(@RequestParam(required = false) Optional<String> name,
                                         @RequestParam(required = false) Optional<String> corporateEmail,
                                         @RequestParam(required = false) Optional<List<ProfessionalStatusEnum>> professionalStatus,
                                         @RequestParam(required = false) Optional<String> phone){
        return service.findAll(name, corporateEmail, professionalStatus, phone);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Professional by id",
            responses = {@ApiResponse(responseCode = "204", description = "Professional successfully deleted")})
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
