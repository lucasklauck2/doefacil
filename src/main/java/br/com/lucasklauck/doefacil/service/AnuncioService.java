package br.com.lucasklauck.doefacil.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.lucasklauck.doefacil.domain.Anuncio;
import br.com.lucasklauck.doefacil.domain.ImagemAnuncio;
import br.com.lucasklauck.doefacil.dto.AnuncioDTO;
import br.com.lucasklauck.doefacil.repository.AnuncioRepository;

@Service
public class AnuncioService {

	@Autowired
	private AnuncioRepository anuncioRepository;

	public void criar(AnuncioDTO anuncioDTO, Long codigoUsuario) {

		Anuncio anuncio = new Anuncio();
		anuncio.setCodigoUsuario(codigoUsuario);
		anuncio.setAberto(Boolean.TRUE);
		anuncio.setTitulo(anuncioDTO.getTitulo());
		anuncio.setDescricao(anuncioDTO.getDescricao());
		anuncio.setLongitude(anuncioDTO.getLongitude());
		anuncio.setLatitude(anuncioDTO.getLatitude());
		anuncio.setQuantidade(anuncioDTO.getQuantidade());

		Anuncio anuncioSalvo = anuncioRepository.save(anuncio);

		anuncioDTO.getListaImagens().stream().map(imagemBase64 -> {

			ImagemAnuncio imagem = new ImagemAnuncio();
			imagem.setCodigoAnuncio(anuncioSalvo.getId());
			imagem.setImagemBase64(imagemBase64);

			return imagem;

		}).forEach(imagem -> anuncio.getListaImagens().add(imagem));

		anuncioRepository.save(anuncioSalvo);

	}
}
