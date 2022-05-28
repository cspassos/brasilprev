package brasilprev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoDTO {

	private String nome;
    private String susep;
	private String expiracaoDeVenda;
	private Double valorMinimoAporteInicial;
	private Double valorMinimoAporteExtra;
	private Integer idadeDeEntrada;
	private Integer idadeDeSaida;
	private Integer carenciaInicialDeResgate;
	private Integer carenciaEntreResgates;
}
