package com.consultoriaTi.client.resource;


import com.consultoriaTi.client.dto.ClientCreateDTO;
import com.consultoriaTi.client.dto.ClientDTO;
import com.consultoriaTi.client.dto.ClientUpdateDTO;
import com.consultoriaTi.client.enums.ClientStatusEnum;
import com.consultoriaTi.client.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
@Tag(name = "Client")
public class ClientResource {

    private final ClientService service;

    @GetMapping("/{id}")
    @Operation(summary = "Search Client by id",
            responses = {@ApiResponse(responseCode = "200", description = "Resource successfully retrieved",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class)))})
    public ClientDTO findById(@Valid @PathVariable Long id) {
        return service.findDTOById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Create client",
            responses = {@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = ClientDTO.class)))})
    public ClientDTO create(@Valid @RequestBody ClientCreateDTO requestDTO) {
        return service.create(requestDTO);
    }

    @PutMapping
    @Operation(summary = "Update Client by id",
            responses = {@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ClientDTO.class)))})
    public ClientDTO update(@Valid @RequestBody ClientUpdateDTO updateDTO) {
        return service.update(updateDTO);
    }

    @GetMapping
    @Operation(summary = "Find all Clients",
            responses = {@ApiResponse(responseCode = "200",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientDTO.class))))})
    public Page<ClientDTO> findAll(@RequestParam(required = false) Optional<String> name,
                                   @RequestParam(required = false) Optional<String> phone,
                                   @RequestParam(required = false) Optional<String> contactEmail,
                                   @RequestParam(required = false) Optional<List<ClientStatusEnum>> clientStatus,
                                   @RequestParam(defaultValue = "0") Integer page,
                                   @RequestParam(defaultValue = "10") Integer size,
                                   @RequestParam(defaultValue = "clientId") String sort,
                                   @RequestParam(defaultValue = "DESC") Sort.Direction direction) {
        return service.findAll(name, phone, contactEmail, clientStatus, PageRequest.of(page, size, Sort.by(direction, sort)));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Client by id",
            responses = {@ApiResponse(responseCode = "204", description = "Elector successfully deleted")})
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
