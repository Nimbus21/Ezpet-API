package br.com.ezpet.nimbus21.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("{id}")
	public ResponseEntity<UsuarioComercialDTO> read(@PathVariable("id") Long codigo) {
		Optional<UsuarioComercial> usuarioComercial = usuarioComercialRepo.findById(codigo);
		return usuarioComercial.map(u -> ResponseEntity.ok(new UsuarioComercialDTO(u))).orElse(ResponseEntity.notFound().build());
	}
}
