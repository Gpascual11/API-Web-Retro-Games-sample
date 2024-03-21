/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The class represents a Rent entity in the model.
 * Authors: Luis Miguel Soriano and Gerard Pascual
 */
@XmlRootElement
@Entity
public class Rent {
    
    // Automatically generated identifier for the rent
    @Id
    @SequenceGenerator(name="Rent_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Rent_Gen") 
    private Long id;
    
    // Date when the rented items are expected to be returned
    private LocalDate returnDate;
    
    // List of video games associated with this rent
    @JsonbTransient
    @ManyToMany
    private List<Videogame> videoGameList;
    
    // Customer associated with this rent (prevent JSON serialization)
    @JsonbTransient
    @ManyToOne 
    private Customer customer;
    
    //Total price of all the videogames in the rent
    private double totalPrice;
    
    // Default constructor required by JPA
    public Rent() {
        videoGameList = new ArrayList();
    }
    
    // Accessor methods for the rent ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Accessor methods for the return date
    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    // Accessor methods for the list of video games associated with this rent
    public List<Videogame> getVideoGameList() {
        return videoGameList;
    }

    public void setVideoGameList(List<Videogame> videoGameList) {
        this.videoGameList = videoGameList;
    }

    // Accessor methods for the customer associated with this rent
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Calculate and return the total price of the rented items
    public double getTotalPrice() {
        if (videoGameList == null || videoGameList.isEmpty()) {
            this.totalPrice = 0.0;
            return 0.0; // If there are no video games, the total price is zero.
        }

        double totalPriceRent = 0.0;
        for (Videogame videoGame : videoGameList) {
            totalPriceRent += videoGame.getPriceRent();
        }
            this.totalPrice = totalPriceRent;
            return totalPriceRent;
    }

    // Generate and return a unique receipt ID based on customer information and timestamp
    public String getReceiptId() {
        if (customer != null && customer.getCredentials() != null) {
            return "Receipt_" + customer.getCredentials().getUsername() + "_" + new Date().getTime();
        } else {
            // Handling if customer or their credentials are null
            return "Receipt_UnknownUser_" + new Date().getTime();
        }
    }
    
    public void addGame(Videogame game) {
        videoGameList.add(game);
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
}
