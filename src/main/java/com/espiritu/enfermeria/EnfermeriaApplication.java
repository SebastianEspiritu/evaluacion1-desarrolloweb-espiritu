package com.espiritu.enfermeria;

import com.espiritu.enfermeria.model.Rol;
import com.espiritu.enfermeria.model.Usuario;
import com.espiritu.enfermeria.repository.RolRepository;
import com.espiritu.enfermeria.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class EnfermeriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnfermeriaApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UsuarioRepository usuarioRepository,
									  RolRepository rolRepository,
									  PasswordEncoder passwordEncoder) {
		return args -> {
			// 1. Obtener o crear Rol ADMINISTRADOR
			Rol adminRol = rolRepository.findAll().stream()
					.filter(r -> "ADMINISTRADOR".equalsIgnoreCase(r.getNombre()))
					.findFirst()
					.orElseGet(() -> rolRepository.save(Rol.builder().nombre("ADMINISTRADOR").descripcion("Rol Admin").activo(true).build()));

			// 2. Obtener o crear Rol ENFERMERO
			Rol enfermeroRol = rolRepository.findAll().stream()
					.filter(r -> "ENFERMERO".equalsIgnoreCase(r.getNombre()))
					.findFirst()
					.orElseGet(() -> rolRepository.save(Rol.builder().nombre("ENFERMERO").descripcion("Rol Enfermero").activo(true).build()));

			// 3. Reencriptar usuario admin
			Usuario admin = usuarioRepository.findByUsername("admin").orElse(new Usuario());
			admin.setUsername("admin");
			admin.setPassword(passwordEncoder.encode("admin123"));
			admin.setNombreCompleto("Administrador Sistema");
			admin.setEmail("admin@clinica.com");
			admin.setActivo(true);
			admin.setRoles(Set.of(adminRol));
			usuarioRepository.save(admin);

			// 4. Reencriptar usuario enfermero1
			Usuario enfermero = usuarioRepository.findByUsername("enfermero1").orElse(new Usuario());
			enfermero.setUsername("enfermero1");
			enfermero.setPassword(passwordEncoder.encode("admin123"));
			enfermero.setNombreCompleto("Sebastián Espíritu");
			enfermero.setEmail("seb@clinica.com");
			enfermero.setActivo(true);
			enfermero.setRoles(Set.of(enfermeroRol));
			usuarioRepository.save(enfermero);

			System.out.println("=================================================");
			System.out.println(">>> USUARIOS Y CONTRASEÑAS CONFIGURADOS OK <<<");
			System.out.println("=================================================");
		};
	}
}