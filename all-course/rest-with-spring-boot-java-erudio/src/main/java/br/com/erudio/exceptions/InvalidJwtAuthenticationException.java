package br.com.erudio.exceptions;

import java.io.Serializable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationException extends AuthenticationException implements Serializable {

    public InvalidJwtAuthenticationException(String ex) {
	super(ex);
    }

}
