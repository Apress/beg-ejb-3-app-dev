package com.apress.ejb3.ch04.tableperclass;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

/*
 * Person:  An abstract entity, and the root of a TABLE_PER_CLASS hierarchy
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "TYPE")
@NamedQuery(name = "Person.findAll", query = "select o from Person o")
@SequenceGenerator(name = "PersonIdGenerator", 
                   sequenceName = "CH04_TPC_PERSON_SEQ", initialValue = 100, 
                   allocationSize = 20)
@Table(name = "CH04_TPC_PERSON")
public abstract class Person
  implements Serializable
{
  @Column(name = "FIRST_NAME")
  private String firstName;
  @Id
  @Column(nullable = false)
  @GeneratedValue(generator = "PersonIdGenerator")
  private Long id;
  @Column(name = "LAST_NAME")
  private String lastName;
  @Version
  private Long version;
  @OneToOne(cascade = { CascadeType.ALL })
  @JoinColumn(name = "HOME_ADDRESS", referencedColumnName = "ID")
  private Address homeAddress;

  public Person() {
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
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

  public Address getHomeAddress() {
    return homeAddress;
  }

  public void setHomeAddress(Address homeAddress) {
    this.homeAddress = homeAddress;
  }
}
