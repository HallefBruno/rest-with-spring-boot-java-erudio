package br.com.erudio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "permission")
@Data
public class Permission implements GrantedAuthority, Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    public Permission(Long id, String description) {
	this.id = id;
	this.description = description;
    }

    public Permission() {
    }
    
    @Override
    public String getAuthority() {
	return this.description;
    }
    
}
