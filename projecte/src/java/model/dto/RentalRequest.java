/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

import java.util.List;

/**
 *
 * @author GerardP
 */
public class RentalRequest {
    
    private Long customer;
    
    private List<Long> gameIDs;
    
    private List<Long> storeIDs;
    
    public RentalRequest() {
        
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public List<Long> getGameIDs() {
        return gameIDs;
    }

    public void setGameIDs(List<Long> gameIDs) {
        this.gameIDs = gameIDs;
    }

    public List<Long> getStoreIDs() {
        return storeIDs;
    }

    public void setStoreIDs(List<Long> storeIDs) {
        this.storeIDs = storeIDs;
    }
    
    
    
}
