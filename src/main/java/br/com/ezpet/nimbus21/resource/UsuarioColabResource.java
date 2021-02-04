package br.com.ezpet.nimbus21.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ezpet.nimbus21.domain.UsuarioColaborador;
import br.com.ezpet.nimbus21.domain.dto.UsuarioColaboradorDTO;
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
}
