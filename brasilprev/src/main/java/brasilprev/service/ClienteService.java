package brasilprev.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import brasilprev.dto.ClienteDTO;
import brasilprev.model.entity.Cliente;
import brasilprev.model.repository.ClienteRepository;

@Service
public class ClienteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(ClienteDTO dto) {
		LOGGER.debug("Salvar a Cliente!");
		Cliente cliente = new Cliente();
		cliente.setCpf(dto.getCpf());
		cliente.setEmail(dto.getEmail());
		cliente.setNome(dto.getNome());
		cliente.setRendaMensal(dto.getRendaMensal());
		cliente.setSexo(dto.getSexo());
		cliente.setDataDeNascimento(converterStringEmData(dto));
		
		return clienteRepository.save(cliente);
	}

	private LocalDate converterStringEmData(ClienteDTO dto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(dto.getDataDeNascimento(), formatter); 
	}

}
