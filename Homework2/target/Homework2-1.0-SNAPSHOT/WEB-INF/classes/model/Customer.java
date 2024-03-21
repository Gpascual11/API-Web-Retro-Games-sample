package deim.urv.cat.homework2.model;

import jakarta.enterprise.context.RequestScoped;
import java.io.Serializable;
import java.util.List;
/**
 * The class represents a Customer entity in the model.
 * @author Luis Miguel & Gerard Pascual
 */
@RequestScoped
public class Customer implements Serializable {

    
    //Customer's id
    private Long id;
    
    // Email address of the customer
    private String email;
    
    //Customer's RentList
    private List<Rent> rentList;
    
    private Credentials credentials;

    // Default constructor required by JPA
    public Customer() {
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }


    // setters and getters 
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Rent> getRentList() {
        return rentList;
    }

    public void setRentList(List<Rent> rentList) {
        this.rentList = rentList;
    }

}
