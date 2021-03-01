package br.com.ezpet.nimbus21.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping("popular")
	public ResponseEntity<UsuarioComercialDTO> popula(UriComponentsBuilder uriBuilder) {
		
		
		UsuarioComercial usu = new UsuarioComercial("A");
		usuarioComercialRepo.save(usu);
		
		UsuarioComercial usu2 = new UsuarioComercial("A");
		usuarioComercialRepo.save(usu2);
		
		UsuarioComercial usu3 = new UsuarioComercial("A");
		usuarioComercialRepo.save(usu3);
		
		UsuarioComercial usu4 = new UsuarioComercial("A");
		usuarioComercialRepo.save(usu4);

		UsuarioComercial usu5 = new UsuarioComercial("A");
		usuarioComercialRepo.save(usu5);
		
		UsuarioComercial usu6 = new UsuarioComercial("A");
		usuarioComercialRepo.save(usu6);
		
		UsuarioComercial usu7 = new UsuarioComercial("A");
		usuarioComercialRepo.save(usu7);
		
		UsuarioComercial usu8 = new UsuarioComercial("A");
		usuarioComercialRepo.save(usu8);
		
		URI uri = uriBuilder.path("/usuarioComercial/{id}").buildAndExpand(usu.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioComercialDTO(usu));
	}
	
//	@GetMapping("search")
//	public Page<UsuarioComercial> search(
//			@RequestParam("searchTerm") String searchTerm, 
//			@RequestParam (value = "page", required = false, defaultValue = "0") int page, 
//			@RequestParam (value = "size", required = false, defaultValue = "5") int size) {
//		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC,"nome");
//		return usuarioComercialRepo.search(searchTerm.toLowerCase(), pageRequest);
//	}
	
//	@GetMapping("searchh")
//	public Page<UsuarioComercial> findAll() {
//		int page = 0;
//		int size = 5;
//		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
//		
//		return new PageImpl<>(usuarioComercialRepo.findAll(), pageRequest, size);
//	}
	
	@GetMapping("all")
	@ResponseStatus(code = HttpStatus.OK)
	public List<UsuarioComercialDTO> novoFindAll(@RequestParam("page") int page) {
		int size = 8;
		Pageable pageRequest = PageRequest.of(page, size);
		Page<UsuarioComercial> allPage = usuarioComercialRepo.findAll(pageRequest);
		List<UsuarioComercial> allList = allPage.getContent();
		return UsuarioComercialDTO.converter(allList);
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
			u.setAbertura(usuarioComercialNovo.getAbertura());
			u.setFechamento(usuarioComercialNovo.getFechamento());
			u.setRole(usuarioComercialNovo.getRole());
			usuarioComercialRepo.save(u);
			return ResponseEntity.ok(new UsuarioComercialDTO(u));
		}).orElse(ResponseEntity.notFound().build());
	}
	
	
}
