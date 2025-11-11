//Cargar datos de los usuarios para crear automaticamente un usuario admin y contraseña en la base de datos
package umg.edu.logisticayenvios.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import umg.edu.logisticayenvios.entity.Rol;
import umg.edu.logisticayenvios.entity.Usuario;
import umg.edu.logisticayenvios.repository.RolRepository;
import umg.edu.logisticayenvios.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(RolRepository rolRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.rolRepository = rolRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (rolRepository.findByNombre("ROLE_ADMIN").isEmpty()) {
            Rol admin = new Rol();
            admin.setNombre("ROLE_ADMIN");
            rolRepository.save(admin);
        }

        if (rolRepository.findByNombre("ROLE_USER").isEmpty()) {
            Rol user = new Rol();
            user.setNombre("ROLE_USER");
            rolRepository.save(user);
        }

        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario adminUser = new Usuario();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin123")); 
            var rolAdmin = rolRepository.findByNombre("ROLE_ADMIN").get();
            adminUser.setRoles(Set.of(rolAdmin));
            usuarioRepository.save(adminUser);
        }
    }
}


