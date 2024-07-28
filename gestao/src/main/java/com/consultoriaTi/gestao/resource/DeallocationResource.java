package com.consultoriaTi.gestao.resource;


import com.consultoriaTi.gestao.dto.DeallocationCreateDTO;
import com.consultoriaTi.gestao.dto.DeallocationDTO;
import com.consultoriaTi.gestao.service.DeallocationService;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/deallocation")
@RequiredArgsConstructor
public class DeallocationResource {

    private final DeallocationService service;

    @GetMapping("/{id}")
    @Operation(summary = "Search Deallocation by id",
            responses = {@ApiResponse(responseCode = "200", description = "Resource successfully retrieved",
                    content = @Content(schema = @Schema(implementation = DeallocationDTO.class)))})
    public DeallocationDTO findById(@Valid @PathVariable Long id) {
        return service.findDTOById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Create Deallocation",
            responses = {@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = DeallocationDTO.class)))})
    public DeallocationDTO create(@Valid @RequestBody DeallocationCreateDTO requestDTO) {
        return service.create(requestDTO);
    }


    @GetMapping
    @Operation(summary = "Find all Deallocations",
            responses = {@ApiResponse(responseCode = "200",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = DeallocationDTO.class))))})
    public List<DeallocationDTO> findAll(   @RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            @RequestParam(defaultValue = "id") String sort,
                                            @RequestParam(defaultValue = "DESC") Sort.Direction direction) {
        return service.findAll(PageRequest.of(page, size, Sort.by(direction, sort)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Deallocation by id",
            responses = {@ApiResponse(responseCode = "204", description = "Deallocation successfully deleted")})
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
