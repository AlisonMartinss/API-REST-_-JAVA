package voll.med.api.Entidades.Repository;

import io.micrometer.observation.ObservationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import voll.med.api.Entidades.Medico;

import java.util.List;

public interface MedicoRepository extends JpaRepository<Medico,Long> {


    Page<Medico>findAllBynome(Pageable paginacao,String nome);
}