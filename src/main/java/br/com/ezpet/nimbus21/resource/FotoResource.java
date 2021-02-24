package br.com.ezpet.nimbus21.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ezpet.nimbus21.domain.Foto;
import br.com.ezpet.nimbus21.domain.dto.FotoDTO;
import br.com.ezpet.nimbus21.repository.FotoRepository;

@RestController
@RequestMapping("foto")
public class FotoResource {

	@Autowired
	FotoRepository fotoRepo;
	
	@PostMapping("/upload")
	@Transactional
	public ResponseEntity<FotoDTO> uploadFoto(@RequestParam("imageFile") MultipartFile file, UriComponentsBuilder uriBuilder) throws IOException{
		
		System.out.println("Tamanho de bytes " + file.getBytes().length);
		String nome = UUID.randomUUID().toString() + "." + file.getOriginalFilename().split("\\.")[1];
		Foto foto = new Foto(file.getOriginalFilename(), nome, file.getContentType(), compressBytes(file.getBytes()));
		System.out.println("Foto em string: " + foto.toString());
		fotoRepo.save(foto);
		
		URI uri = uriBuilder.path("/get/{nome}").buildAndExpand(foto.getNomeOriginal()).toUri();
		
		return ResponseEntity.created(uri).body(new FotoDTO(foto));
	}
	
//	@GetMapping(value = "/get/{nome}", produces = MediaType.IMAGE_JPEG_VALUE)
//	public Foto getFoto(@PathVariable("nome") String nome) throws IOException {
//		final Optional<Foto> retrievedImage = fotoRepo.findByNome(nome);
//		Foto foto = new Foto(retrievedImage.get().getNome(), retrievedImage.get().getTipo(), decompressBytes(retrievedImage.get().getFotoByte()));
//		return foto;
//	}
	
	@GetMapping(value = "/get/{nome}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getFoto(@PathVariable("nome") String nome) throws IOException {
//		final Optional<Foto> retrievedImage = fotoRepo.findByNomeOriginal(nome);
		final Optional<Foto> retrievedImage = fotoRepo.findByNovoNome(nome);
		Foto foto = new Foto(retrievedImage.get().getNomeOriginal(), retrievedImage.get().getNovoNome(), retrievedImage.get().getTipo(), decompressBytes(retrievedImage.get().getFotoByte()));
		return foto.getFotoByte();
	}
	
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		
		while(!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		
		try {
			outputStream.close();
		} catch(Exception e) {
			
		}
		
		System.out.println("Tamanho da imagem comprimida " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}
	
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		
		try {
			while(!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (Exception e) {
			
		}
		
		return outputStream.toByteArray();
	}
}
