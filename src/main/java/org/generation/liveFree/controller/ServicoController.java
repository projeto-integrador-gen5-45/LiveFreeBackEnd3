package org.generation.liveFree.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.generation.liveFree.model.Servico;
import org.generation.liveFree.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/servicos")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class ServicoController {

	@Autowired
	private ServicoRepository repository;

	@GetMapping
	public ResponseEntity<List<Servico>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Servico> getById(@PathVariable long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Servico>> getByTitle(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}

	@PostMapping
	public ResponseEntity<Servico> post(@Valid @RequestBody Servico servico){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(repository.save(servico));
	}

	@PutMapping
	public ResponseEntity<Servico> put(@Valid @RequestBody Servico servico){
		return repository.findById(servico.getId())
				.map(resp -> ResponseEntity.status(HttpStatus.OK).body(repository.save(servico)))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		Optional<Servico> post = repository.findById(id);

		if(post.isEmpty())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

		repository.deleteById(id);
	}

}