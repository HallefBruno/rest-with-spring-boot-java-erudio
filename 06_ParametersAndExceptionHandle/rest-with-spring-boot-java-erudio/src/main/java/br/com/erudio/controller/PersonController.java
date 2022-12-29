package br.com.erudio.controller;

import br.com.erudio.data.vo.v1.PersonVOV1;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/person/v1")
public class PersonController {
  
  @Autowired
  private PersonService personService;
  
  @RequestMapping(path = "/findBy/{id}", method = RequestMethod.GET)
  public ResponseEntity<EntityModel<PersonVOV1>> findByPerson(@PathVariable(name = "id", required = true) Long id) {
    return ResponseEntity.ok(personService.findById(id));
  }
  
  @RequestMapping(path = "/findAll", method = RequestMethod.GET)
  public ResponseEntity<List<PersonVOV1>> findAllPerson() {
    return ResponseEntity.ok(personService.findAll());
  }
  
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(path = "/create", method = RequestMethod.POST)
  public PersonVOV1 createPerson(@RequestBody PersonVOV1 person) {
    return personService.create(person);
  }
  
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(path = "/create/v2", method = RequestMethod.POST)
  public PersonVOV2 createPersonV2(@RequestBody PersonVOV2 person) {
    return personService.createV2(person);
  }
  
  @RequestMapping(path = "/update", method = RequestMethod.PUT)
  public PersonVOV1 updatePerson(@RequestBody(required = true) PersonVOV1 person) {
    return ResponseEntity.ok(personService.update(person)).getBody();
  }
  
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
  public void deletePerson(@PathVariable(required = true) Long id) {
    personService.deletePerson(id);
  }
  
}
