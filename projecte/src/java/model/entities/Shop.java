/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The class represents a Shop entity in the model.
 * Authors: Luis Miguel Soriano and Gerard Pascual
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="Shop.findAll", 
        query="SELECT c.id FROM Shop c "
                + "ORDER BY c.id")
})
public class Shop {

    // Unique identifier for the shop
    @Id
    @SequenceGenerator(name="Shop_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Shop_Gen")
    private Long id;
    
    // Name of the shop
    private String name;

    // Address of the shop
    private String address;

     
    // List of video games associated with this shop
    @OneToMany(mappedBy = "shop")
    private List<ShopVideogame> videoGames;

    // Default constructor required by JPA
    public Shop() {
    }

    public Long getId() {
        return id;
    }
    // Accessor methods for the shop name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Accessor methods for the shop address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
