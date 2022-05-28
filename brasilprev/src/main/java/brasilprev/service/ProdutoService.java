package brasilprev.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brasilprev.dto.ProdutoDTO;
import brasilprev.model.entity.Produto;
import brasilprev.model.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoService.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto salvar(ProdutoDTO dto) {
		LOGGER.debug("Salvar o Produto!");
		Produto produto = new Produto();
		produto.setCarenciaEntreResgates(dto.getCarenciaEntreResgates());
		produto.setCarenciaInicialDeResgate(dto.getCarenciaInicialDeResgate());
		produto.setExpiracaoDeVenda(converterStringEmData(dto.getExpiracaoDeVenda()));
		produto.setIdadeDeEntrada(dto.getIdadeDeEntrada());
		produto.setIdadeDeSaida(dto.getIdadeDeSaida());
		produto.setNome(dto.getNome());
		produto.setSusep(dto.getSusep());
		produto.setValorMinimoAporteExtra(dto.getValorMinimoAporteExtra());
		produto.setValorMinimoAporteInicial(dto.getValorMinimoAporteInicial());
		return produtoRepository.save(produto);
	}
	
	private LocalDate converterStringEmData(String expiracaoVenda) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(expiracaoVenda, formatter); 
	}

}
