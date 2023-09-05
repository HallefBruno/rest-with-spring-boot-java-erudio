package br.com.erudio.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date launchDate;

    @Column(nullable = false, scale = 62, precision = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private String title;

    public Book() {
    }

    public Book(Long id, String author, Date launchDate, BigDecimal price, String title) {
	this.id = id;
	this.author = author;
	this.launchDate = launchDate;
	this.price = price;
	this.title = title;
    }
    
    @PrePersist
    @PreUpdate
    void pre() {
	if(this.launchDate == null) {
	    this.launchDate = new Date();
	}
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public Date getLaunchDate() {
	return launchDate;
    }

    public void setLaunchDate(Date launchDate) {
	this.launchDate = launchDate;
    }

    public BigDecimal getPrice() {
	return price;
    }

    public void setPrice(BigDecimal price) {
	this.price = price;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 17 * hash + Objects.hashCode(this.id);
	hash = 17 * hash + Objects.hashCode(this.author);
	hash = 17 * hash + Objects.hashCode(this.launchDate);
	hash = 17 * hash + Objects.hashCode(this.price);
	hash = 17 * hash + Objects.hashCode(this.title);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final Book other = (Book) obj;
	if (!Objects.equals(this.author, other.author)) {
	    return false;
	}
	if (!Objects.equals(this.title, other.title)) {
	    return false;
	}
	if (!Objects.equals(this.id, other.id)) {
	    return false;
	}
	if (!Objects.equals(this.launchDate, other.launchDate)) {
	    return false;
	}
	return Objects.equals(this.price, other.price);
    }

    @Override
    public String toString() {
	return "Book{" + "id=" + id + ", author=" + author + ", launchDate=" + launchDate + ", price=" + price + ", title=" + title + '}';
    }
    
    
}
