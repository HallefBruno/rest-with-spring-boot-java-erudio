package br.com.erudio.service;

import br.com.erudio.exceptions.UnsupportedMathOperationException;
import br.com.erudio.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MathService {
  
  @Autowired
  private Util util;
  
  public Double sum(String numberOne, String numberTwo) {
    if(!util.isNumeric(numberOne) || !util.isNumeric(numberTwo)) {
      throw new UnsupportedMathOperationException("Número inválido");
    }
    return util.parseStr(numberOne) + util.parseStr(numberTwo);
  }
  
  public Double substraction(String numberOne, String numberTwo) {
    if(!util.isNumeric(numberOne) || !util.isNumeric(numberTwo)) {
      throw new UnsupportedMathOperationException("Número inválido");
    }
    return util.parseStr(numberOne) - util.parseStr(numberTwo);
  }
  
}
