package br.com.ezpet.nimbus21.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import br.com.ezpet.nimbus21.domain.Produto;
import br.com.ezpet.nimbus21.domain.dto.ProdutoDTO;
import br.com.ezpet.nimbus21.repository.ProdutoRepository;

@RestController
@RequestMapping("produto")
public class ProdutoResource {

	@Autowired
	ProdutoRepository produtoRepo;

	@CrossOrigin
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<ProdutoDTO> readAll() {
		List<Produto> produtos = produtoRepo.findAll();
		return ProdutoDTO.converter(produtos);
	}
	
	@CrossOrigin
	@GetMapping("{id}")
	public ResponseEntity<ProdutoDTO> read(@PathVariable("id") Long codigo) {
		Optional<Produto> produto = produtoRepo.findById(codigo);
		return produto.map(p -> ResponseEntity.ok(new ProdutoDTO(p))).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ProdutoDTO> create(@RequestBody @Valid Produto produto, UriComponentsBuilder uriBuilder) {
		produtoRepo.save(produto);
		
		URI uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(new ProdutoDTO(produto));
	}
	
	@PutMapping("{id}")
	@Transactional
	public ResponseEntity<ProdutoDTO> update(@PathVariable("id") Long codigo, @RequestBody @Valid Produto produtoNovo) {
		Optional<Produto> produto = produtoRepo.findById(codigo);
		
		return produto.map(p -> {
			p.setDescricao(produtoNovo.getDescricao());
			p.setEspecificacao(produtoNovo.getEspecificacao());
			p.setFoto(produtoNovo.getFoto());
			p.setNome(produtoNovo.getNome());
			p.setPreco(produtoNovo.getPreco());
			p.setSubcategoria(produtoNovo.getSubcategoria());
//			p.setTipoAnimal(produtoNovo.getTipoAnimal());
//			p.setTipoFisico(produtoNovo.getTipoFisico());
			p.setTipoProduto(produtoNovo.getTipoProduto());
			p.setUsuarioComercial(produtoNovo.getUsuarioComercial());
			p.setMarca(produtoNovo.getMarca());
			p.setPrecoAntigo(produtoNovo.getPrecoAntigo());
			p.setSubcategoria(produtoNovo.getSubcategoria());
			produtoRepo.save(p);
			return ResponseEntity.ok(new ProdutoDTO(p));
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable("id") Long codigo) {
		Optional<Produto> produto = produtoRepo.findById(codigo);
		return produto.map(p -> {
			produtoRepo.deleteById(codigo);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}
