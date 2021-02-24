package br.com.ezpet.nimbus21.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.ezpet.nimbus21.domain.Foto;
import br.com.ezpet.nimbus21.repository.FotoRepository;

@RestController
@RequestMapping("foto")
public class FotoResource {

	@Autowired
	FotoRepository fotoRepo;
	
	@PostMapping("/upload")
	@ResponseBody
	public BodyBuilder uploadFoto(@RequestParam("imageFile") MultipartFile file){
		
		try {
			System.out.println("Tamanho de bytes " + file.getBytes().length);
			Foto foto = new Foto(file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
			fotoRepo.save(foto);
			
//			return ResponseEntity.status(HttpStatus.ACCEPTED);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.OK);
	}
	
//	@GetMapping(value = "/get/{nome}", produces = MediaType.IMAGE_JPEG_VALUE)
//	public Foto getFoto(@PathVariable("nome") String nome) throws IOException {
//		final Optional<Foto> retrievedImage = fotoRepo.findByNome(nome);
//		Foto foto = new Foto(retrievedImage.get().getNome(), retrievedImage.get().getTipo(), decompressBytes(retrievedImage.get().getFotoByte()));
//		return foto;
//	}
	
	@GetMapping(value = "/get/{nome}", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getFoto(@PathVariable("nome") String nome) throws IOException {
		final Optional<Foto> retrievedImage = fotoRepo.findByNome(nome);
		Foto foto = new Foto(retrievedImage.get().getNome(), retrievedImage.get().getTipo(), decompressBytes(retrievedImage.get().getFotoByte()));
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
