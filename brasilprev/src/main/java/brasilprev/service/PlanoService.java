package brasilprev.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import brasilprev.dto.PlanoDTO;
import brasilprev.dto.ResgateDTO;
import brasilprev.model.entity.Aporte;
import brasilprev.model.entity.Cliente;
import brasilprev.model.entity.Plano;
import brasilprev.model.entity.Resgate;
import brasilprev.model.repository.AporteRepository;
import brasilprev.model.repository.ClienteRepository;
import brasilprev.model.repository.PlanoRepository;
import brasilprev.model.repository.ProdutoRepository;

@Service
public class PlanoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlanoService.class);
	
	@Autowired
	private PlanoRepository planoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private AporteRepository aporteRepository;
	
	public Plano salvar(PlanoDTO dto) {
		LOGGER.debug("Salvar Plano!");
		return planoRepository.save(converterDtoEmEntidade(dto));
	}

	private Plano converterDtoEmEntidade(PlanoDTO dto) {
		Plano plano = new Plano();
		plano.setNome(dto.getNome());
		plano.setProduto(produtoRepository.findById(dto.getIdProduto()).get());
		plano.setSaldoPlano(dto.getSaldoPlano());
		return plano;
	}

	public void contratarPlano(PlanoDTO dto) {
		LOGGER.debug("Contratar Plano!");
		
		List<Cliente> clientes = new ArrayList<>();
		Cliente cliente = new Cliente();
		
		Resgate resgate = new Resgate();
		
		Aporte aporte = new Aporte();
		
		LocalDate dataAtual = LocalDate.now();
		
		Plano plano = new Plano();
		plano = planoRepository.findById(dto.getIdPlano()).get();
		cliente = clienteRepository.findById(dto.getIdCliente()).get();
		if(validarCliente(dto, dataAtual, plano, cliente)) {
			clientes.add(cliente);
			plano.setClientes(clientes);
			
			resgate.setPrimeiroResgate("S");
			resgate.setDataProximoResgate(dataAtual.plusDays(60));
			
			aporte.setPlano(plano);
			aporte.setCliente(cliente);
			aporte.setValorAporte(dto.getValorMinimoAporteInicial());
			aporte.setResgate(resgate);
			
			aporteRepository.save(aporte);
		}
		
		planoRepository.save(plano);
}

	private Boolean validarCliente(PlanoDTO dto, LocalDate dataAtual, Plano plano, Cliente cliente) {
		if(!dataAtual.isBefore(plano.getProduto().getExpiracaoDeVenda())) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Período de venda já passou!");
		} else if(dto.getValorMinimoAporteInicial() < plano.getProduto().getValorMinimoAporteInicial()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O aporte deve ser igual ou superior ao valor do aporte plano!");
		}
		long idade = ChronoUnit.YEARS.between(cliente.getDataDeNascimento(), dataAtual);
		if(idade < plano.getProduto().getIdadeDeEntrada()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Idade menor do que a permitida!");
		}else if(idade >= plano.getProduto().getIdadeDeSaida()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Idade maior do que a permitida!");
		}
		return true;
	}

	public void resgatePlano(ResgateDTO dto) {
		
		LocalDate dataResgate = converterStringEmData(dto.getDtResgate());
		
		Plano plano = planoRepository.findById(dto.getIdPlano()).get();
		Cliente cliente = clienteRepository.findById(dto.getIdCliente()).get();
		Aporte aporte = aporteRepository.findByPlanoIdAndClienteId(plano.getId(), cliente.getId()).get();
		
		if(plano.getSaldoPlano() > 0) {
			
			if(aporte.getResgate().getPrimeiroResgate() == "S" && dataResgate.isBefore(aporte.getResgate().getDataProximoResgate())) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O Período do primeiro resgate é de 60 dias após o aporte!");
			} else if(aporte.getResgate().getPrimeiroResgate() == "N" && dataResgate.isBefore(aporte.getResgate().getDataProximoResgate())) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O Período do segundo resgate é de 30 dias após o primeiro resgate!");
			} else if(dto.getVrResgate() > plano.getSaldoPlano()){
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O valor a ser resgatado é maior do que o valor disponível no plano!");
			}else {
				plano.setSaldoPlano(plano.getSaldoPlano() - dto.getVrResgate());
				planoRepository.save(plano);
				
				aporte.getResgate().setPrimeiroResgate("N");
				aporte.getResgate().setVrUltimoResgate(dto.getVrResgate());;
				aporteRepository.save(aporte);
			}
		
		}  else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano cancelado!");
		}
		
	}
	
	private LocalDate converterStringEmData(String data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(data, formatter); 
	}

	public void aporteExtra(PlanoDTO dto) {
		
		Plano plano = planoRepository.findById(dto.getIdPlano()).get();
		Cliente cliente = clienteRepository.findById(dto.getIdCliente()).get();
		Aporte aporte = aporteRepository.findByPlanoIdAndClienteId(plano.getId(), cliente.getId()).get();
		
		if(plano.getSaldoPlano() > 0) {
			if(dto.getValorMinimoAporteExtra() < plano.getProduto().getValorMinimoAporteExtra()) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aporte extra deve ser superior ou igual ao valor permitido do plano!");
			}else {
				plano.setSaldoPlano(plano.getSaldoPlano() + dto.getValorMinimoAporteExtra());
				planoRepository.save(plano);
				
				aporte.setValorAporteExtra(dto.getValorMinimoAporteExtra());
				aporteRepository.save(aporte);
			}
		}  else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano cancelado!");
		}
		
		
	}

}
