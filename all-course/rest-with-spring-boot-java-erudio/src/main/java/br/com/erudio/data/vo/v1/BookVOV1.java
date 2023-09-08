package br.com.erudio.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import org.dozer.Mapping;
import org.springframework.hateoas.RepresentationModel;

@JsonPropertyOrder(value = {"id", "author", "launchDate", "price", "title"})
public class BookVOV1 extends RepresentationModel<BookVOV1> implements Serializable {
    
    @JsonProperty("id")
    @Mapping("id")
    private Long key;
    private String author;
    private Date launchDate;
    private BigDecimal price;
    private String title;
    
    
    public BookVOV1() {
    }

    public Long getKey() {
	return key;
    }

    public void setKey(Long key) {
	this.key = key;
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
	int hash = 3;
	hash = 89 * hash + Objects.hashCode(this.key);
	hash = 89 * hash + Objects.hashCode(this.author);
	hash = 89 * hash + Objects.hashCode(this.launchDate);
	hash = 89 * hash + Objects.hashCode(this.price);
	hash = 89 * hash + Objects.hashCode(this.title);
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
	final BookVOV1 other = (BookVOV1) obj;
	if (!Objects.equals(this.author, other.author)) {
	    return false;
	}
	if (!Objects.equals(this.title, other.title)) {
	    return false;
	}
	if (!Objects.equals(this.key, other.key)) {
	    return false;
	}
	if (!Objects.equals(this.launchDate, other.launchDate)) {
	    return false;
	}
	return Objects.equals(this.price, other.price);
    }

    @Override
    public String toString() {
	return "BookVOV1{" + "key=" + key + ", author=" + author + ", launchDate=" + launchDate + ", price=" + price + ", title=" + title + '}';
    }

    
}
