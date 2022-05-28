package brasilprev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDTO {

private String nome;
	
    private String cpf;
	private String email;
	private String dataDeNascimento;
	private String sexo;
	private Double rendaMensal;
	
}
