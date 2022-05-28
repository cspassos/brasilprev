package brasilprev.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
    private String nome;
	
	@Column
    private String cpf;
	
	@Column
	private String email;
	
	@Column
	private LocalDate dataDeNascimento;
	
	@Column
	private String sexo;
	
	@Column
	private Double rendaMensal;
	
	@ManyToMany(mappedBy="clientes")
	private List<Plano> planos;
	
}
