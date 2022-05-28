package brasilprev.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
    private String nome;
	
	@Column
    private String susep;
	
	@Column
	private LocalDate expiracaoDeVenda;
	
	@Column
	private Double valorMinimoAporteInicial;
	
	@Column
	private Double valorMinimoAporteExtra;
	
	@Column
	private Integer idadeDeEntrada;
	
	@Column
	private Integer idadeDeSaida;
	
	@Column
	private Integer carenciaInicialDeResgate;
	
	@Column
	private Integer carenciaEntreResgates;
	

}
