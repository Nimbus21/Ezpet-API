package br.com.ezpet.nimbus21.resource;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ezpet.nimbus21.domain.UsuarioCliente;
import br.com.ezpet.nimbus21.domain.dto.UsuarioClienteDTO;
import br.com.ezpet.nimbus21.repository.UsuarioClienteRepository;

@RestController
@RequestMapping("usuarioCliente")
public class UsuarioClienteResource {

	@Autowired
	UsuarioClienteRepository usuarioClienteRepo;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<UsuarioClienteDTO> readAll(String nome) {
		
		List<UsuarioCliente> usuarioClientes = nome == null ? usuarioClienteRepo.findAll() : usuarioClienteRepo.findByNomeContaining(nome);
		
		return UsuarioClienteDTO.converter(usuarioClientes);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UsuarioClienteDTO> read(@PathVariable("id") Long codigo) {
		Optional<UsuarioCliente> usuarioCliente = usuarioClienteRepo.findById(codigo);
		
		System.out.println(usuarioCliente.get().getNome());
		System.out.println(usuarioCliente.get().getMascotes());
		System.out.println(usuarioCliente.get().getPosts());
		
		
		return usuarioCliente.map(u -> ResponseEntity.ok(new UsuarioClienteDTO(u))).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioClienteDTO> create(@RequestBody @Valid UsuarioCliente usuarioCliente, UriComponentsBuilder uriBuilder) {
		usuarioClienteRepo.save(usuarioCliente);
		
		URI uri = uriBuilder.path("/usuarioCliente/{id}").buildAndExpand(usuarioCliente.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioClienteDTO(usuarioCliente));
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<UsuarioClienteDTO> update(@PathVariable("id") Long codigo, @RequestBody @Valid UsuarioCliente usuarioClienteNovo) {
		Optional<UsuarioCliente> usuarioCliente = usuarioClienteRepo.findById(codigo);
		
		return usuarioCliente.map(u -> {
			u.setTipoUsuario(usuarioClienteNovo.getTipoUsuario());
			u.setNome(usuarioClienteNovo.getNome());
			u.setEmail(usuarioClienteNovo.getEmail());
			u.setSenha(usuarioClienteNovo.getSenha());
			u.setMascotes(usuarioClienteNovo.getMascotes());
			u.setEnderecos(usuarioClienteNovo.getEnderecos());
			u.setFotos(usuarioClienteNovo.getFotos());
			u.setLocalizacao(usuarioClienteNovo.getLocalizacao());
			u.setPedidos(usuarioClienteNovo.getPedidos());
			u.setPosts(usuarioClienteNovo.getPosts());
			u.setRecomendacoes(usuarioClienteNovo.getRecomendacoes());
			usuarioClienteRepo.save(u);
			return ResponseEntity.ok(new UsuarioClienteDTO(u));
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@PatchMapping("{id}")
	@Transactional
	public ResponseEntity<UsuarioClienteDTO> updateSenha(@PathVariable("id") Long codigo, @RequestBody Map<String, String> senha) {
		Optional<UsuarioCliente> usuarioCliente = usuarioClienteRepo.findById(codigo);
				
//		try {
//			usuarioCliente.get().setSenha(senha.get("senha"));
//			return ResponseEntity.ok().build();
//		} catch (Exception e) {
//			return ResponseEntity.notFound().build();
//		}
		
		return usuarioCliente.map(u -> {
			u.setSenha(senha.get("senha"));
			usuarioClienteRepo.save(u);
			return ResponseEntity.ok(new UsuarioClienteDTO(u));
		}).orElse(ResponseEntity.notFound().build());
			
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long codigo) {
		Optional<UsuarioCliente> usuarioCliente = usuarioClienteRepo.findById(codigo);
		return usuarioCliente.map(u -> {
			usuarioClienteRepo.deleteById(codigo);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
	
}
