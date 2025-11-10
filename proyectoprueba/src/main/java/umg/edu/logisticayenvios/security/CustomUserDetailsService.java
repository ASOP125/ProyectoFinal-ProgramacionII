package umg.edu.logisticayenvios.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import umg.edu.logisticayenvios.repository.UsuarioRepository;
import umg.edu.logisticayenvios.entity.Usuario;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        var authorities = usuario.getRoles().stream()
                .map(r -> new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + r.getNombre()))
                .collect(Collectors.toSet());

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.isActivo(), true, true, true, authorities);
    }
}
