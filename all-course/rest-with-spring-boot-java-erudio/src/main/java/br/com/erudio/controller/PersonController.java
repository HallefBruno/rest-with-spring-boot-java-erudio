package br.com.erudio.controller;

import br.com.erudio.data.vo.v1.PersonVOV1;
import br.com.erudio.data.vo.v2.PersonVOV2;
import br.com.erudio.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(path = "/api/person/v1")
@Tag(name = "people", description = "Endpoint Manager For People")
public class PersonController {
  
  @Autowired
  private PersonService personService;
  
  @RequestMapping(path = "/findBy/{id}", method = RequestMethod.GET)
  @Operation(summary = "Finds a Peoples", description = "Finds a Peoples", tags = "people", responses = {
    @ApiResponse(description = "Success", responseCode = "200", content = @Content(schema = @Schema(implementation = PersonVOV1.class))),
    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
  })
  public ResponseEntity<EntityModel<PersonVOV1>> findByPerson(@PathVariable(name = "id", required = true) Long id) {
    return ResponseEntity.ok(personService.findById(id));
  }
  
  @RequestMapping(path = "/findAll", method = RequestMethod.GET)
  @Operation(summary = "Finds All Peoples", description = "Finds All Peoples", tags = "people", responses = {
    @ApiResponse(description = "Success", responseCode = "200", content = {
      @Content(
        mediaType = "application/json",
        array = @ArraySchema(schema = @Schema(implementation = PersonVOV1.class))
      )
    }),
    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
  })
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
