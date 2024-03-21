/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import authn.Credentials;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The class represents a Customer entity in the model.
 * Authors: Luis Miguel Soriano and Gerard Pascual
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="Customer.findAll", 
        query="SELECT c.id, c.email FROM Customer c "
                + "ORDER BY c.id"),
    @NamedQuery(name="Customer.findId", 
        query="SELECT c.id, c.email FROM Customer c WHERE c.id = :id"),
    @NamedQuery(name="Customer.findCredentials", 
        query="SELECT c FROM Customer c WHERE c.credentials.username = :username AND c.credentials.password = :password")
})
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    // Automatically generated identifier for the customer
    @Id
    @SequenceGenerator(name = "Customer_Gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Customer_Gen")
    private Long id;

    // Email address of the customer
    private String email;

    // Credentials associated with the customer (prevent JSON serialization)
    @OneToOne
    private Credentials credentials;

    // List of rental records associated with this customer
    @OneToMany(mappedBy = "customer")
    private List<Rent> rentList;

    // Default constructor required by JPA
    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public List<Rent> getRentList() {
        return rentList;
    }

    public void setRentList(List<Rent> rentList) {
        this.rentList = rentList;
    }

}
