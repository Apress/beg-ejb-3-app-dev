package com.apress.ejb3.ch04.mappedsuperclass;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

/*
 * Person:  An abstract non-entity mapped superclass
 */
@MappedSuperclass
@SequenceGenerator(name = "PersonIdGenerator", 
                   sequenceName = "CH04_MS_PERSON_SEQ", initialValue = 100, 
                   allocationSize = 20)
public abstract class Person
  implements Serializable
{
  @Column(name = "FIRST_NAME")
  private String firstName;
  @OneToOne(cascade = { CascadeType.ALL })
  @JoinColumn(name = "HOME_ADDRESS", referencedColumnName = "ID")
  private Address homeAddress;
  @Id
  @Column(nullable = false)
  @GeneratedValue(generator = "PersonIdGenerator")
  private Long id;
  @Column(name = "LAST_NAME")
  private String lastName;
  @Version
  private Long version;

  public Person() {
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public Address getHomeAddress() {
    return homeAddress;
  }

  public void setHomeAddress(Address homeAddress) {
    this.homeAddress = homeAddress;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
