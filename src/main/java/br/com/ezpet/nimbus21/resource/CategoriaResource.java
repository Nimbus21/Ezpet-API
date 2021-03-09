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

import br.com.ezpet.nimbus21.domain.Categoria;
import br.com.ezpet.nimbus21.domain.dto.CategoriaDTO;
import br.com.ezpet.nimbus21.repository.CategoriaRepository;

@RestController
@RequestMapping("categoria")
public class CategoriaResource {

	@Autowired
	CategoriaRepository categoriaRepo;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<CategoriaDTO> readAll() {
		List<Categoria> categorias = categoriaRepo.findAll();
		return CategoriaDTO.converter(categorias);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CategoriaDTO> read(@PathVariable("id") Long codigo) {
		Optional<Categoria> categoria = categoriaRepo.findById(codigo);
		return categoria.map(e -> ResponseEntity.ok(new CategoriaDTO(e))).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoriaDTO> create(@RequestBody @Valid Categoria categoria, UriComponentsBuilder uriBuilder) {
		categoriaRepo.save(categoria);
		URI uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(new CategoriaDTO(categoria));
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<CategoriaDTO> update(@PathVariable("id") Long codigo, @RequestBody @Valid Categoria categoriaNova) {
		Optional<Categoria> categoria = categoriaRepo.findById(codigo);
		
		return categoria.map(c -> {
			c.setAtivo(categoriaNova.getAtivo());
			c.setEspecie(categoriaNova.getEspecie());
			c.setIcone(categoriaNova.getIcone());
			c.setNome(categoriaNova.getNome());
			c.setSubcategorias(categoriaNova.getSubcategorias());
			categoriaRepo.save(c);
			return ResponseEntity.ok(new CategoriaDTO(c));
		}).orElse(ResponseEntity.notFound().build());
		
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long codigo) {
		Optional<Categoria> categoria = categoriaRepo.findById(codigo);
		return categoria.map(p -> {
			categoriaRepo.deleteById(codigo);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
