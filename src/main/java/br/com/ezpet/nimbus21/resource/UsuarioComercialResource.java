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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ezpet.nimbus21.domain.UsuarioComercial;
import br.com.ezpet.nimbus21.domain.dto.UsuarioComercialDTO;
import br.com.ezpet.nimbus21.repository.UsuarioComercialRepository;

@RestController
@RequestMapping("usuarioComercial")
public class UsuarioComercialResource {

	@Autowired
	UsuarioComercialRepository usuarioComercialRepo;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<UsuarioComercialDTO> readAll() {
		List<UsuarioComercial> usuariosComercial = usuarioComercialRepo.findAll();
		return UsuarioComercialDTO.converter(usuariosComercial);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<UsuarioComercialDTO> readEmail(@PathVariable("email") String email){
		Optional<UsuarioComercial> usuarioComercial = usuarioComercialRepo.findByEmail(email);
		return usuarioComercial.map(u -> ResponseEntity.ok(new UsuarioComercialDTO(u))).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UsuarioComercialDTO> read(@PathVariable("id") Long codigo) {
		Optional<UsuarioComercial> usuarioComercial = usuarioComercialRepo.findById(codigo);
		return usuarioComercial.map(u -> ResponseEntity.ok(new UsuarioComercialDTO(u))).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioComercialDTO> create(@RequestBody UsuarioComercial usuarioComercial, UriComponentsBuilder uriBuilder) {
		usuarioComercialRepo.save(usuarioComercial);
		
		URI uri = uriBuilder.path("/usuarioComercial/{id}").buildAndExpand(usuarioComercial.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioComercialDTO(usuarioComercial));
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<UsuarioComercialDTO> update(@PathVariable("id") Long codigo, @RequestBody UsuarioComercial usuarioComercialNovo) {
		Optional<UsuarioComercial> usuarioComercial = usuarioComercialRepo.findById(codigo);
		
		return usuarioComercial.map(u -> {
			u.setCep(usuarioComercialNovo.getCep());
			u.setCnpj(usuarioComercialNovo.getCnpj());
			u.setCodigo(usuarioComercialNovo.getCodigo());
			u.setEmail(usuarioComercialNovo.getEmail());
			u.setEndereco(usuarioComercialNovo.getEndereco());
			u.setFoto(usuarioComercialNovo.getFoto());
			u.setHorarioFuncionamento(usuarioComercialNovo.getHorarioFuncionamento());
			u.setNome(usuarioComercialNovo.getNome());
			u.setNomeContato(usuarioComercialNovo.getNomeContato());
			u.setPosts(usuarioComercialNovo.getPosts());
			u.setProdutos(usuarioComercialNovo.getProdutos());
			u.setSenha(usuarioComercialNovo.getSenha());
			u.setTelefone(usuarioComercialNovo.getTelefone());
			u.setTipoUsuario(usuarioComercialNovo.getTipoUsuario());
			usuarioComercialRepo.save(u);
			return ResponseEntity.ok(new UsuarioComercialDTO(u));
		}).orElse(ResponseEntity.notFound().build());
	}
	
	
}
