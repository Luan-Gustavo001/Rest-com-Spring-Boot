package com.gustavo.luan.service;

import com.gustavo.luan.controllers.PersonContoller;
import com.gustavo.luan.data.dto.PersonDTO;
import com.gustavo.luan.exception.RequiredObjectIsNullException;
import com.gustavo.luan.exception.ResourceNotFoundException;
import static com.gustavo.luan.mapper.ObjectMapper.parseObject;
import static com.gustavo.luan.mapper.ObjectMapper.parseListObjects;
import com.gustavo.luan.model.Person;
import com.gustavo.luan.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServices {

    @Autowired
    PersonRepository repository;

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    public PersonDTO findById(Long id){
        logger.info("Achando uma pessoa!");
        var entidade = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não achou nenhum registro com esse ID: " + id));

        var dto = parseObject(entidade, PersonDTO.class);

        addHateoasLinks(dto);
        return dto;
    }



    public List<PersonDTO> findAll(){
        logger.info("Achando varias pessoaa!");

        var persons = parseListObjects(repository.findAll(), PersonDTO.class);
        persons.forEach(this :: addHateoasLinks);

        return persons;
    }

    public PersonDTO create(PersonDTO person) {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Criando uma pessoa!");
        var entity = parseObject(person, Person.class);

        var dto = parseObject(repository.save(entity), PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public PersonDTO update(PersonDTO person) {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Atualizando uma pessoa!");

        Person pessoa = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Não achou nenhum registro com esse ID"));

        pessoa.setFirstName(person.getFirstName());
        pessoa.setLastName(person.getLastName());
        pessoa.setAddress(person.getAddress());
        pessoa.setGender(person.getGender());

        var dto = parseObject(repository.save(pessoa), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id) {
        logger.info("Deletando uma pessoa!");

        Person pessoa = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não achou nenhum registro com esse ID: " + id));

        repository.delete(pessoa);
    }

    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonContoller.class).finById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonContoller.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonContoller.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonContoller.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonContoller.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}
