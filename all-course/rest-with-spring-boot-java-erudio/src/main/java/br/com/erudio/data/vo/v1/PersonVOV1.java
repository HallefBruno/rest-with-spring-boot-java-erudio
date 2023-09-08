package br.com.erudio.data.vo.v1;

//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Objects;
import lombok.ToString;
//import org.dozer.Mapping;
import org.springframework.hateoas.RepresentationModel;

//@JsonPropertyOrder(value = {})
@ToString(callSuper = true)
public class PersonVOV1 extends RepresentationModel<PersonVOV1> implements Serializable {
  
  //@Mapping("id")
  //@JsonProperty(value = "id")
  private Long id;
  private String firstName;
  private String lastName;
  private String address;
  //@JsonIgnore
  private String gender;

  public PersonVOV1() {
  }

  public PersonVOV1(Long id, String firstName, String lastName, String address, String gender) {
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
    int hash = 5;
    hash = 59 * hash + Objects.hashCode(this.id);
    hash = 59 * hash + Objects.hashCode(this.firstName);
    hash = 59 * hash + Objects.hashCode(this.lastName);
    hash = 59 * hash + Objects.hashCode(this.address);
    hash = 59 * hash + Objects.hashCode(this.gender);
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
    final PersonVOV1 other = (PersonVOV1) obj;
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
