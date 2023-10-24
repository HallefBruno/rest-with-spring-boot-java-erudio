package br.com.erudio.data.vo.v1.security;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class TokenVO implements Serializable {
    
    private String username;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

    public TokenVO() {
    }
    
    public TokenVO(String username, Boolean authenticated, Date created, Date expiration, String accessToken, String refreshToken) {
	this.username = username;
	this.authenticated = authenticated;
	this.created = created;
	this.expiration = expiration;
	this.accessToken = accessToken;
	this.refreshToken = refreshToken;
    }
    
}
