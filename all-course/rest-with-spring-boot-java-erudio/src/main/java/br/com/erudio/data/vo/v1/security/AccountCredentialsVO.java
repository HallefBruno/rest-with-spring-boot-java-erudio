package br.com.erudio.data.vo.v1.security;

import java.io.Serializable;
import lombok.Data;

@Data
public class AccountCredentialsVO implements Serializable {
    
    private String username;
    private String password;

    public AccountCredentialsVO(String username, String password) {
	this.username = username;
	this.password = password;
    }
    
}
