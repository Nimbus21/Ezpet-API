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

import br.com.ezpet.nimbus21.domain.Marca;
import br.com.ezpet.nimbus21.domain.Produto;
import br.com.ezpet.nimbus21.domain.dto.MarcaDTO;
import br.com.ezpet.nimbus21.repository.MarcaRepository;

@RestController
@RequestMapping("marca")
public class MarcaResource {

	@Autowired
	MarcaRepository marcaRepo;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<MarcaDTO> readAll() {
		List<Marca> marcas = marcaRepo.findAll();
		return MarcaDTO.converter(marcas);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<MarcaDTO> read(@PathVariable("id") Long codigo) {
		Optional<Marca> marca = marcaRepo.findById(codigo);
		return marca.map(m -> ResponseEntity.ok(new MarcaDTO(m))).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<MarcaDTO> create(@RequestBody @Valid Marca marca, UriComponentsBuilder uriBuilder) {
		marcaRepo.save(marca);
		
		URI uri = uriBuilder.path("/marca/{id}").buildAndExpand(marca.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(new MarcaDTO(marca));
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<MarcaDTO> update(@PathVariable("id") Long codigo, @RequestBody @Valid Marca marcaNova) {
		Optional<Marca> marca = marcaRepo.findById(codigo);
		
		return marca.map(m -> {
			m.setAtivo(marcaNova.getAtivo());
			m.setLogo(marcaNova.getLogo());
			m.setNome(marcaNova.getNome());
			m.setProdutos(marcaNova.getProdutos());
			marcaRepo.save(m);
			return ResponseEntity.ok(new MarcaDTO(m));
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long codigo) {
		Optional<Marca> marca = marcaRepo.findById(codigo);
		return marca.map(m -> {
			marcaRepo.deleteById(codigo);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
