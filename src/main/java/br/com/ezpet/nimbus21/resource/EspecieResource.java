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

import br.com.ezpet.nimbus21.domain.Especie;
import br.com.ezpet.nimbus21.domain.dto.EspecieDTO;
import br.com.ezpet.nimbus21.repository.EspecieRepository;

@RestController
@RequestMapping("especie")
public class EspecieResource {

	@Autowired
	EspecieRepository especieRepo;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<EspecieDTO> readAll() {
		List<Especie> especies = especieRepo.findAll();
		return EspecieDTO.converter(especies);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<EspecieDTO> read(@PathVariable("id") Long codigo) {
		Optional<Especie> especie = especieRepo.findById(codigo);
		return especie.map(e -> ResponseEntity.ok(new EspecieDTO(e))).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EspecieDTO> create(@RequestBody @Valid Especie especie, UriComponentsBuilder uriBuilder) {
		especieRepo.save(especie);
		URI uri = uriBuilder.path("/especie/{id}").buildAndExpand(especie.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(new EspecieDTO(especie));
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<EspecieDTO> update(@PathVariable("id") Long codigo, @RequestBody @Valid Especie especieNova) {
		Optional<Especie> especie = especieRepo.findById(codigo);
		
		return especie.map(e -> {
			e.setAtivo(especieNova.getAtivo());
			e.setCategorias(especieNova.getCategorias());
			e.setDescricao(especieNova.getDescricao());
			e.setIcone(especieNova.getIcone());
			e.setNome(especieNova.getNome());
			especieRepo.save(e);
			return ResponseEntity.ok(new EspecieDTO(e));
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long codigo) {
		Optional<Especie> especie = especieRepo.findById(codigo);
		return especie.map(p -> {
			especieRepo.deleteById(codigo);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
