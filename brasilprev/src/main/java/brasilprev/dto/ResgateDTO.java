package brasilprev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResgateDTO {

	private Long idPlano;
	private Long idCliente;
	private Double vrResgate;
	private String dtResgate;
	
}
