package br.com.ezpet.nimbus21.resource;

import java.net.URI;
import java.util.Arrays;
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

import br.com.ezpet.nimbus21.domain.Role;
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
	
	@Autowired
    private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("criadmin")
	@ResponseStatus(code = HttpStatus.OK)
	public void callAdmin() {
		
		UsuarioAdmin thandyAdmin = new UsuarioAdmin("thandy_97@hotmail.com", passwordEncoder.encode("Cycle21"));
		thandyAdmin.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
		usuarioAdminRepo.save(thandyAdmin);
		System.out.println("ThandyAdmin criado");
	}
	
//	@GetMapping("/email/{email}")
//	public ResponseEntity<UsuarioComercialDTO> readEmail(@PathVariable("email") String email){
//		Optional<UsuarioComercial> usuarioComercial = usuarioComercialRepo.findByEmail(email);
//		return usuarioComercial.map(u -> ResponseEntity.ok(new UsuarioComercialDTO(u))).orElse(ResponseEntity.notFound().build());
//	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UsuarioAdminDTO> readEmail(@PathVariable("email") String email) {
		Optional<UsuarioAdmin> usuarioAdmin = usuarioAdminRepo.findByEmail(email);
		return usuarioAdmin.map(u -> ResponseEntity.ok(new UsuarioAdminDTO(u))).orElse(ResponseEntity.notFound().build());
	}
	
	//working
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioAdminDTO> create(@RequestBody @Valid UsuarioAdmin usuarioAdmin, UriComponentsBuilder uriBuilder) {
//		System.out.println("Eu cheguei");
		usuarioAdminRepo.save(usuarioAdmin);
		
		URI uri = uriBuilder.path("/usuarioAdmin/{id}").buildAndExpand(usuarioAdmin.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioAdminDTO(usuarioAdmin));
	}
	
	
}
