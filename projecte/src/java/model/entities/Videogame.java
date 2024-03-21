/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * The class represents a Videogame entity in the model.
 * Authors: Luis Miguel Soriano & Gerard Pascual
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="Videogame.findAll", 
        query="SELECT g FROM Videogame g "
                + "ORDER BY g.name"),
    
    @NamedQuery(name="Videogame.findByType", 
        query="SELECT g FROM Videogame g "
                + "WHERE g.type.type = :type "
                + "ORDER BY g.name"),

    @NamedQuery(name="Videogame.findByConsole", 
        query="SELECT g FROM Videogame g "
                + "WHERE g.console.console = :console "
                + "ORDER BY g.name"),

    @NamedQuery(name="Videogame.findByTypeConsole", 
        query="SELECT g FROM Videogame g "
                + "WHERE g.type.type = :type "
                + "AND g.console.console = :console "
                + "ORDER BY g.name"),
    
    @NamedQuery(name = "Videogame.findByNameConsole", 
        query ="SELECT g FROM Videogame g "
                + "WHERE g.name = :name "
                + "AND g.console.console = :console "),
    
    @NamedQuery(name = "Videogame.findByName", 
        query ="SELECT g FROM Videogame g "
                + "WHERE g.console.console = :console "
                + "AND g.name = :name "),
})
public class Videogame implements Serializable {
    
    private static final long serialVersionUID = 1L;

    // Unique identifier for the videogame
    @Id
    @SequenceGenerator(name="VideoGame_gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VideoGame_gen")
    private Long id;

    // Name of the videogame (cannot be null)
    @NotNull
    private String name;

    // Availability status of the videogame
    @NotNull
    private Boolean available;

    // Rental price of the videogame (must be positive)
    @Positive
    private Double priceRent;

    // Description of the videogame
    private String description;

    // List of rents associated with this videogame
    @JsonbTransient
    @ManyToMany
    @JoinTable(
        name = "videogame_rent",
        joinColumns = @JoinColumn(name = "videogame_id"),
        inverseJoinColumns = @JoinColumn(name = "rent_id")
    )
    private List<Rent> rentList;

    // List of shops where this videogame is available
    @JsonbTransient
    @OneToMany (mappedBy = "game")
    private List<ShopVideogame> shopLists;

    // Type of the videogame (many-to-one relationship)
    @ManyToOne
    private GameType type;

    // Console associated with this videogame (many-to-one relationship)
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Console console;

    // Default constructor required by JPA
    public Videogame() {
    }

    // Accessor methods for the videogame type
    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    // Accessor methods for the videogame console
    public Console getConsole() {
        return console;
    }
    
    public String getConsoleName() {
        return console.toString();
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    // Accessor methods for the videogame ID
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    // Accessor methods for the videogame name
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    // Accessor methods for the videogame availability status
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Boolean getAvailable() {
        return this.available;
    }

    // Accessor methods for the videogame rental price
    public void setPriceRent(Double priceRent) {
        this.priceRent = priceRent;
    }

    public double getPriceRent() {
        return this.priceRent;
    }
    
    // Accessor methods for the videogame description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Accessor methods for the list of rents associated with this videogame
    public List<Rent> getRentList() {
        return rentList;
    }

    public void setRentList(List<Rent> rentList) {
        this.rentList = rentList;
    }

    public List<ShopVideogame> getShopLists() {
        return shopLists;
    }

    public void setShopLists(List<ShopVideogame> shopLists) {
        this.shopLists = shopLists;
    }

}
