package com.consultoriaTi.gestao.resource;


import com.consultoriaTi.gestao.dto.AllocationCreateDTO;
import com.consultoriaTi.gestao.dto.AllocationDTO;
import com.consultoriaTi.gestao.dto.AllocationUpdateDTO;
import com.consultoriaTi.gestao.enums.AllocationStatusEnum;
import com.consultoriaTi.gestao.repository.spec.AllocationSpecification;
import com.consultoriaTi.gestao.service.AllocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;
import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/allocation")
@RequiredArgsConstructor
public class AllocationResource {

    private final AllocationService service;

    @GetMapping("/{id}")
    @Operation(summary = "Search Allocacation by id",
            responses = {@ApiResponse(responseCode = "200", description = "Resource successfully retrieved",
                    content = @Content(schema = @Schema(implementation = AllocationDTO.class)))})
    public AllocationDTO findById(@Valid @PathVariable Long id) {
        return service.findDTOById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Create Allocation",
            responses = {@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = AllocationDTO.class)))})
    public AllocationDTO create(@Valid @RequestBody AllocationCreateDTO requestDTO) {
        return service.create(requestDTO);
    }

    @PutMapping
    @Operation(summary = "Update Allocation by id",
            responses = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = AllocationDTO.class)))})
    public AllocationDTO update(@Valid @RequestBody AllocationUpdateDTO updateDTO) {
        return service.update(updateDTO);
    }

    @GetMapping
    @Operation(summary = "Find all Allocations",
            responses = {@ApiResponse(responseCode = "200",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = AllocationDTO.class))))})
    public List<AllocationDTO> findAll(@RequestParam(required = false) Optional<List<Long>> professionalId,
                                       @RequestParam(required = false) Optional<List<Long>> clientId,
                                       @RequestParam(required = false) Optional<String> role,
                                       @RequestParam(required = false) Optional<BigDecimal> valuePerHour,
                                       @RequestParam(required = false) @DateTimeFormat(iso = DATE) Optional<LocalDate> allocationStartDate,
                                       @RequestParam(required = false) @DateTimeFormat(iso = DATE) Optional<LocalDate> allocationEndDate,
                                       @RequestParam(required = false) Optional<List<AllocationStatusEnum>> allocationStatus,
                                       @RequestParam(required = false) Optional<String> professionalName,
                                       @RequestParam(defaultValue = "0") Integer page,
                                       @RequestParam(defaultValue = "10") Integer size,
                                       @RequestParam(defaultValue = "id") String sort,
                                       @RequestParam(defaultValue = "DESC") Sort.Direction direction) {
        return service.findAll(AllocationSpecification.builder()
                        .professionalId(professionalId)
                        .clientId(clientId)
                        .role(role)
                        .valuePerHour(valuePerHour)
                        .allocationStartDate(allocationStartDate)
                        .allocationEndDate(allocationEndDate)
                        .allocationStatus(allocationStatus)
                        .professionalName(professionalName)
                        .build(),
                PageRequest.of(page, size, Sort.by(direction, sort)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Allocation by id",
            responses = {@ApiResponse(responseCode = "204", description = "Allocation successfully deleted")})
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
