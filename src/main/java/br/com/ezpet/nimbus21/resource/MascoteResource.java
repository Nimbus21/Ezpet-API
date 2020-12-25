package br.com.ezpet.nimbus21.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.ezpet.nimbus21.domain.Mascote;
import br.com.ezpet.nimbus21.domain.dto.MascoteDTO;
import br.com.ezpet.nimbus21.repository.MascoteRepository;

@RestController
@RequestMapping("mascote")
public class MascoteResource {

	@Autowired
	MascoteRepository mascoteRepo;
	
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<MascoteDTO> readAll() {
		List<Mascote> mascotes = mascoteRepo.findAll();
		return MascoteDTO.converter(mascotes);
	}
}
