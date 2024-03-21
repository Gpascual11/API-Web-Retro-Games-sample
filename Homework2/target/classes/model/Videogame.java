package deim.urv.cat.homework2.model;

import jakarta.enterprise.context.RequestScoped;
import java.io.Serializable;

/**
 * The class represents a Videogame entity in the model.
 * @author Luis Miguel & Gerard Pascual
 */
@RequestScoped
public class Videogame implements Serializable {
    
    //Videogame's id
    private Long id;
    
    // Name of the videogame
    private String name;

    // Availability status of the videogame
    private Boolean available;

    // Rental price of the videogame (must be positive)
    private Double priceRent;

    // Description of the videogame
    private String description;
    
    // Type of the videogame 
    private GameType type;
    
    // Console associated with this videogame 
    private Console console;


    // Default constructor required by JPA
    public Videogame() {
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Double getPriceRent() {
        return priceRent;
    }

    public void setPriceRent(Double priceRent) {
        this.priceRent = priceRent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GameType getType() {
        return type;
    }

    public void setType(GameType type) {
        this.type = type;
    }

    public Console getConsole() {
        return console;
    }
    
    public String getConsoleName() {
        return console.getConsole();
    }

    public void setConsole(Console console) {
        this.console = console;
    }

}
