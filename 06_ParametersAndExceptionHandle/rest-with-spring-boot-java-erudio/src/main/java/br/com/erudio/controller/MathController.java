package br.com.erudio.controller;

import br.com.erudio.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {
  
  @Autowired
  private MathService mathService;
  
  @RequestMapping(path = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
  public Double sum(
    @PathVariable(value = "numberOne", required = true) String numberOne, 
    @PathVariable(value = "numberTwo", required = true) String numberTwo) {
    
    return mathService.sum(numberOne, numberTwo);
  }
  
  @RequestMapping(path = "/subtraction/{numberOne}/{numberTwo}", method = RequestMethod.GET)
  public Double sub(
    @PathVariable(value = "numberOne", required = true) String numberOne, 
    @PathVariable(value = "numberTwo", required = true) String numberTwo) {
    
    return mathService.substraction(numberOne, numberTwo);
  }

  
}
