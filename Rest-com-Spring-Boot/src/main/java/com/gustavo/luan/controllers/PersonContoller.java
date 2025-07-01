package com.gustavo.luan.controllers;

import com.gustavo.luan.data.dto.v1.PersonDTO;
import com.gustavo.luan.data.dto.v2.PersonDTOV2;
import com.gustavo.luan.service.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonContoller {

    @Autowired
    private PersonServices service;

    @GetMapping(value = "/{id}",
     produces = MediaType.APPLICATION_JSON_VALUE)
     public PersonDTO finById(@PathVariable("id") Long id) {

        return service.findById(id);
     }

    @GetMapping(
     produces = MediaType.APPLICATION_JSON_VALUE)
     public List<PersonDTO> findAll() {

        return service.findAll();
     }

    @PostMapping(
     produces = MediaType.APPLICATION_JSON_VALUE,
     consumes = MediaType.APPLICATION_JSON_VALUE)
     public PersonDTO create(@RequestBody PersonDTO person) {

        return service.create(person);
     }

    @PostMapping(value = "/v2",
     produces = MediaType.APPLICATION_JSON_VALUE,
     consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTOV2 createV2(@RequestBody PersonDTOV2 person) {

        return service.createV2(person);
    }

   @PutMapping(
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public PersonDTO update(@RequestBody PersonDTO person) {

        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
