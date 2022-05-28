package brasilprev.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import brasilprev.model.entity.Resgate;

@Repository
public interface ResgateRepository extends JpaRepository<Resgate, Long>{

}
