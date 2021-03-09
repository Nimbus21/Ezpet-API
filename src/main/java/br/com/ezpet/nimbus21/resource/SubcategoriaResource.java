package br.com.ezpet.nimbus21.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ezpet.nimbus21.domain.Subcategoria;
import br.com.ezpet.nimbus21.domain.dto.SubcategoriaDTO;
import br.com.ezpet.nimbus21.repository.SubcategoriaRepository;

@RestController
@RequestMapping("subcategoria")
public class SubcategoriaResource {

	@Autowired
	SubcategoriaRepository subcategoriaRepo;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<SubcategoriaDTO> readAll() {
		List<Subcategoria> subcategorias = subcategoriaRepo.findAll();
		return SubcategoriaDTO.converter(subcategorias);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<SubcategoriaDTO> read(@PathVariable("id") Long codigo) {
		Optional<Subcategoria> subcategoria = subcategoriaRepo.findById(codigo);
		return subcategoria.map(s -> ResponseEntity.ok(new SubcategoriaDTO(s))).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<SubcategoriaDTO> create(@RequestBody @Valid Subcategoria subcategoria, UriComponentsBuilder uriBuilder) {
		subcategoriaRepo.save(subcategoria);
		URI uri = uriBuilder.path("/subcategoria/{id}").buildAndExpand(subcategoria.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(new SubcategoriaDTO(subcategoria));
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<SubcategoriaDTO> update(@PathVariable("id") Long codigo, @RequestBody @Valid Subcategoria subcategoriaNova) {
		Optional<Subcategoria> subcategoria = subcategoriaRepo.findById(codigo);
		
		return subcategoria.map(s -> {
			s.setAtivo(subcategoriaNova.getAtivo());
			s.setCategoria(subcategoriaNova.getCategoria());
			s.setDescricao(subcategoriaNova.getDescricao());
			s.setIcone(subcategoriaNova.getIcone());
			s.setNome(subcategoriaNova.getNome());
			s.setProdutos(subcategoriaNova.getProdutos());
			subcategoriaRepo.save(s);
			return ResponseEntity.ok(new SubcategoriaDTO(s));
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long codigo) {
		Optional<Subcategoria> subcategoria = subcategoriaRepo.findById(codigo);
		return subcategoria.map(p -> {
			subcategoriaRepo.deleteById(codigo);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
