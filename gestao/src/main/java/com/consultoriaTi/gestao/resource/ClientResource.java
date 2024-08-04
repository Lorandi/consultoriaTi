package com.consultoriaTi.gestao.resource;


import com.consultoriaTi.gestao.dto.ClientDTO;
import com.consultoriaTi.gestao.service.ClientService;
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

@Slf4j
@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(name = "Client")
public class ClientResource {

    private final ClientService service;

    @GetMapping("/{id}")
    @Operation(summary = "Search Client by id",
            responses = {@ApiResponse(responseCode = "200", description = "Resource successfully retrieved",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class)))})
    public ClientDTO findById(@Valid @PathVariable Long id) {
        return service.findDTOByClientId(id);
    }

    @GetMapping
    @Operation(summary = "Find all Clients",
            responses = {@ApiResponse(responseCode = "200",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ClientDTO.class))))})
    public Page<ClientDTO> findAll(@RequestParam(defaultValue = "0") Integer page,
                                   @RequestParam(defaultValue = "10") Integer size,
                                   @RequestParam(defaultValue = "clientId") String sort,
                                   @RequestParam(defaultValue = "DESC") Sort.Direction direction) {
        return service.findAll(PageRequest.of(page, size, Sort.by(direction, sort)));
    }
}
