/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package deim.urv.cat.homework2.model;

import jakarta.enterprise.context.RequestScoped;
import java.time.LocalDate;


/**
 * The class represents a Rent entity in the model.
 * @author Luis Miguel & Gerard Pascual
 */
@RequestScoped
public class Rent {
     
    //Rent's id
    private Long id;
    
    // Date when the rented items are expected to be returned
    private LocalDate returnDate;
    
    //Total price of all the videogames in the rent
    private double totalPrice;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }  


    
    
}
