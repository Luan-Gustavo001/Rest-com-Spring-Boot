package com.gustavo.luan.service;

import com.gustavo.luan.data.dto.v1.PersonDTO;
import com.gustavo.luan.data.dto.v2.PersonDTOV2;
import com.gustavo.luan.exception.ResourceNotFoundException;
import static com.gustavo.luan.mapper.ObjectMapper.parseObject;
import static com.gustavo.luan.mapper.ObjectMapper.parseListObjects;

import com.gustavo.luan.mapper.custom.PersonMapper;
import com.gustavo.luan.model.Person;
import com.gustavo.luan.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper pMapper;

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    public PersonDTO findById(Long id){
        logger.info("Achando uma pessoa!");
        var entidade = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não achou nenhum registro com esse ID: " + id));

        return parseObject(entidade, PersonDTO.class);
    }

    public List<PersonDTO> findAll(){
        logger.info("Achando varias pessoaa!");

        return parseListObjects(repository.findAll(), PersonDTO.class);
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

    public PersonDTO create(PersonDTO person) {
        logger.info("Criando uma pessoa!");
        var entity = parseObject(person, Person.class);

        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person) {
        logger.info("Criando uma pessoa!");
        var entity = pMapper.convertDTOToEntity(person);

        return pMapper.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Atualizando uma pessoa!");

        Person pessoa = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Não achou nenhum registro com esse ID"));

        pessoa.setFirstName(person.getFirstName());
        pessoa.setLastName(person.getLastName());
        pessoa.setAddress(person.getAddress());
        pessoa.setGender(person.getGender());

        return parseObject(repository.save(pessoa), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("Deletando uma pessoa!");

        Person pessoa = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não achou nenhum registro com esse ID: " + id));

        repository.delete(pessoa);
    }
}
