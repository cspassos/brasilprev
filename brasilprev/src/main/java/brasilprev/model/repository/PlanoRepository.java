package brasilprev.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import brasilprev.model.entity.Plano;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Long>{

}
