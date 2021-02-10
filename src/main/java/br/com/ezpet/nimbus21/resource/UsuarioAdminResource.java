package br.com.ezpet.nimbus21.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ezpet.nimbus21.domain.UsuarioAdmin;
import br.com.ezpet.nimbus21.domain.dto.UsuarioAdminDTO;
import br.com.ezpet.nimbus21.repository.UsuarioAdminRepository;

@RestController
@RequestMapping("usuarioAdmin")
public class UsuarioAdminResource {
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

	@Autowired
	UsuarioAdminRepository usuarioAdminRepo;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<UsuarioAdminDTO> readAll() {
		
		List<UsuarioAdmin> usuariosAdmin = usuarioAdminRepo.findAll();
		
		return UsuarioAdminDTO.converter(usuariosAdmin);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UsuarioAdminDTO> readEmail(@PathVariable("email") String email) {
		Optional<UsuarioAdmin> usuarioAdmin = usuarioAdminRepo.findByEmail(email);
		return usuarioAdmin.map(u -> ResponseEntity.ok(new UsuarioAdminDTO(u))).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioAdminDTO> create(@RequestBody @Valid UsuarioAdmin usuarioAdmin, UriComponentsBuilder uriBuilder) {
		usuarioAdminRepo.save(usuarioAdmin);
		
		URI uri = uriBuilder.path("/usuarioAdmin/{id}").buildAndExpand(usuarioAdmin.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioAdminDTO(usuarioAdmin));
	}
	
	
}
