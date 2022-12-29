package br.com.erudio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Person implements Serializable {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private String address;
  private String gender;

  public Person() {
  }

  public Person(Long id, String firstName, String lastName, String address, String gender) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.gender = gender;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 17 * hash + Objects.hashCode(this.id);
    hash = 17 * hash + Objects.hashCode(this.firstName);
    hash = 17 * hash + Objects.hashCode(this.lastName);
    hash = 17 * hash + Objects.hashCode(this.address);
    hash = 17 * hash + Objects.hashCode(this.gender);
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
    final Person other = (Person) obj;
    if (!Objects.equals(this.firstName, other.firstName)) {
      return false;
    }
    if (!Objects.equals(this.lastName, other.lastName)) {
      return false;
    }
    if (!Objects.equals(this.address, other.address)) {
      return false;
    }
    if (!Objects.equals(this.gender, other.gender)) {
      return false;
    }
    return Objects.equals(this.id, other.id);
  }
  
}
