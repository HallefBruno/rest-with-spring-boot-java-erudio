package br.com.erudio.exceptions;

import java.io.Serializable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException implements Serializable {

  public ResourceNotFoundException(String ex) {
    super(ex);
  }
  
}
