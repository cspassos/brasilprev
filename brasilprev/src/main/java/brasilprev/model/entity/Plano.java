package brasilprev.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

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
public class Plano implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
    private String nome;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Produto produto;
	
	@ManyToMany
    @JoinTable(name="plano_cliente", 
    	joinColumns={@JoinColumn(name="plano_id")}, 
    	inverseJoinColumns={@JoinColumn(name="cliente_id")})
	private List<Cliente> clientes;
	
	@Column
	private Double saldoPlano;
	
}
