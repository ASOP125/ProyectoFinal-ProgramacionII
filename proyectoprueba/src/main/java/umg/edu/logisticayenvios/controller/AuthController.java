package umg.edu.logisticayenvios.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import umg.edu.logisticayenvios.dto.AuthRequest;
import umg.edu.logisticayenvios.dto.AuthResponse;
import umg.edu.logisticayenvios.entity.Usuario;
import umg.edu.logisticayenvios.repository.UsuarioRepository;
import umg.edu.logisticayenvios.security.JwtService;

import java.util.stream.Collectors;
@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager,
                          UsuarioRepository usuarioRepository,
                          JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
    }

    @PostMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            // Autenticar con Spring (lanza excepción si falla)
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            // Obtener usuario para generar token con roles
            Usuario user = usuarioRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            log.info("Usuario encontrado: {}", user);

            var roles = user.getRoles().stream().map(r -> r.getNombre()).collect(Collectors.toList());
            String token = jwtService.generarToken(user.getUsername(), roles);

            return ResponseEntity.ok(new AuthResponse(token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }
}
