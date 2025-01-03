	package br.com.richard.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.richard.exceptions.ResourceNotFoundException;
import br.com.richard.model.Person;
import br.com.richard.repositories.PersonRepository;

@Service
public class PersonServices {
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository repository;
	
	public List<Person> findAll(){
		logger.info("find all people!");
		return repository.findAll();
	}
	
	public Person findById(Long id){
		logger.info("finding one person!");
		return repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("no records found for this id"));
	}
	
	public Person create(Person person) {
		logger.info("creating one person!");
		return repository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("updating one person!");
		var entity = repository.findById(person.getId())
			.orElseThrow(() -> new ResourceNotFoundException("no records found for this id"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("no records found for this id"));
		repository.delete(entity);
	}
}
