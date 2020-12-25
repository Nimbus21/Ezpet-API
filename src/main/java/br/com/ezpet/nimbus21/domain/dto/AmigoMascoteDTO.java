package br.com.ezpet.nimbus21.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.ezpet.nimbus21.domain.AmigoMascote;
import br.com.ezpet.nimbus21.domain.Mascote;
import lombok.Getter;

@Getter
public class AmigoMascoteDTO {

	private Long codigo;
	private Mascote mascote;


	public AmigoMascoteDTO(AmigoMascote amigoMascote) {
		this.codigo = amigoMascote.getCodigo();
		this.mascote = amigoMascote.getMascote();
	}

	public static List<AmigoMascoteDTO> converter(List<AmigoMascote> amigoMascotes) {
		return amigoMascotes.stream().map(AmigoMascoteDTO::new).collect(Collectors.toList());
	}

}
