/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

import java.util.List;

/**
 *
 * @author Luis Miguel & Gerard Pascual
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
