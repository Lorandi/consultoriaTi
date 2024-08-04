package com.consultoriaTi.gestao.resource;

import com.consultoriaTi.gestao.dto.AuthenticationDTO;
import com.consultoriaTi.gestao.dto.LoginResponseDTO;
import com.consultoriaTi.gestao.dto.RegisterDTO;
import com.consultoriaTi.gestao.entity.User;
import com.consultoriaTi.gestao.service.AuthorizationService;
import com.consultoriaTi.gestao.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthenticationResource {

    private final AuthenticationManager authenticationManager;
    private final AuthorizationService authorizationService;
    private final TokenService tokenService;

    @PostMapping("/login")
    @ResponseStatus(CREATED)
    @Operation(summary = "User login",
            responses = {@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = AuthenticationDTO.class)))})
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody AuthenticationDTO requestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(requestDTO.login(), requestDTO.password());
        var authentication = this.authenticationManager.authenticate(usernamePassword);

        var token = this.tokenService.generateToken((User)authentication.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    @ResponseStatus(CREATED)
    @Operation(summary = "User register",
            responses = {@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = RegisterDTO.class)))})
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO requestDTO) {
        if(this.authorizationService.loadUserByUsername(requestDTO.login()) !=null){
            return ResponseEntity.badRequest().body("User already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(requestDTO.password());
        User newUser = new User(requestDTO.login(), encryptedPassword, requestDTO.role());

        this.authorizationService.save(newUser);

        return ResponseEntity.ok().build();

    }

}
