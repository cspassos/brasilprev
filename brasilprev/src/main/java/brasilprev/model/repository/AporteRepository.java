package brasilprev.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import brasilprev.model.entity.Aporte;

@Repository
public interface AporteRepository extends JpaRepository<Aporte, Long>{

	Optional<Aporte> findByPlanoIdAndClienteId(Long idPlano, Long idCliente);
}
