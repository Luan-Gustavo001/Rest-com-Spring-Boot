package com.gustavo.luan.service;

import com.gustavo.luan.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){
        logger.info("Achando uma pessoa!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Luan");
        person.setLastName("Gustavo");
        person.setAddress("Santa Cruz do Rio Pardo, SP, Brasil");
        person.setGender("Male");

        return person;
    }

    public List<Person> findAll(){
        logger.info("Achando varias pessoaa!");

        List<Person> persons = new ArrayList<Person>();

        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }
        return persons;
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

        return person;
    }

    public Person update(Person person) {
        logger.info("Atualizando uma pessoa!");

        return person;
    }

    public void delete(String id) {
        logger.info("Deletando uma pessoa!");

    }
}
