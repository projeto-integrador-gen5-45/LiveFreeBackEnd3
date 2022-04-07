package org.generation.liveFree.repository;

import java.util.List;

import org.generation.liveFree.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
	public List<Servico> findAllByTituloContainingIgnoreCase(String titulo);
}
