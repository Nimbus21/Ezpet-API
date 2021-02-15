package br.com.ezpet.nimbus21.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ezpet.nimbus21.domain.UsuarioColaborador;
import br.com.ezpet.nimbus21.domain.UsuarioComercial;
import br.com.ezpet.nimbus21.domain.dto.UsuarioColaboradorDTO;
import br.com.ezpet.nimbus21.domain.dto.UsuarioComercialDTO;
import br.com.ezpet.nimbus21.repository.UsuarioColaboradorRepository;

@RestController
@RequestMapping("usuarioColab")
public class UsuarioColabResource {
	
	@Autowired
	UsuarioColaboradorRepository usuarioColabRepo;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<UsuarioColaboradorDTO> readAll() {
		List<UsuarioColaborador> usuariosColab = usuarioColabRepo.findAll();
		
		return UsuarioColaboradorDTO.converter(usuariosColab);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UsuarioColaboradorDTO> readEmail(@PathVariable("email") String email) {
		Optional<UsuarioColaborador> usuarioColab = usuarioColabRepo.findByEmail(email);
		
		return usuarioColab.map(u -> ResponseEntity.ok(new UsuarioColaboradorDTO(u))).orElse(ResponseEntity.notFound().build());
	}
	
	//not tested
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioColaboradorDTO> create(@RequestBody UsuarioColaborador usuarioColaborador, UriComponentsBuilder uriBuilder) {
		usuarioColabRepo.save(usuarioColaborador);
		
		URI uri = uriBuilder.path("/usuarioColaborador/{id}").buildAndExpand(usuarioColaborador.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioColaboradorDTO(usuarioColaborador));
	}
}
