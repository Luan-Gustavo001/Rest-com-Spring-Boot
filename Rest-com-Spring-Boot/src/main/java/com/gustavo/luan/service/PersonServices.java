package com.gustavo.luan.service;

import com.gustavo.luan.exception.ResourceNotFoundException;
import com.gustavo.luan.model.Person;
import com.gustavo.luan.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    PersonRepository repository;

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    public Person findById(Long id){
        logger.info("Achando uma pessoa!");

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não achou nenhum registro com esse ID: " + id));
    }

    public List<Person> findAll(){
        logger.info("Achando varias pessoaa!");

        return repository.findAll();
    }

    private Person mockPerson(int i) {

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("First Name " + i);
        person.setLastName("Last Name " + i);
        person.setAddress("Address " + i);
        person.setGender("Gender " + i);

        return person;
    }

    public Person create(Person person) {
        logger.info("Criando uma pessoa!");

        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Atualizando uma pessoa!");

        Person pessoa = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Não achou nenhum registro com esse ID"));

        pessoa.setFirstName(person.getFirstName());
        pessoa.setLastName(person.getLastName());
        pessoa.setAddress(person.getAddress());
        pessoa.setGender(person.getGender());

        return repository.save(pessoa);
    }

    public void delete(Long id) {
        logger.info("Deletando uma pessoa!");

        Person pessoa = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não achou nenhum registro com esse ID: " + id));

        repository.delete(pessoa);
    }
}
