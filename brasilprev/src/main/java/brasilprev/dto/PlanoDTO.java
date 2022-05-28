package brasilprev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlanoDTO {

	private String nome;
	private Long idPlano;
	private Long idProduto;
	private Long idCliente;
	private Double valorMinimoAporteInicial;
	private Double valorMinimoAporteExtra;
	private Double saldoPlano;
	
}
