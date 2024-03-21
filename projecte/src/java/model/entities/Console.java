/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.CascadeType;
import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.util.List;

/**
 * The class represents a console entity in the model.
 * @author Luis Miguel Soriano and Gerard Pascual
 */
@Entity
public class Console implements Serializable {

    // Unique identifier for the console
    private static final long serialVersionUID = 1L;

    // Automatically generated identifier for the console
    @Id
    @SequenceGenerator(name="Console_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Console_Gen") 
    private Long id;

    // Name of the console
    private String console;

    // List of video games associated with this console (prevents circular serialization)
    @JsonbTransient
    @OneToMany(mappedBy = "console", cascade = CascadeType.ALL)
    private List<Videogame> videogameList;

    // Default constructor required by JPA
    public Console() {
    }

    /**
     * Constructor that allows initializing the name of the console when creating an instance.
     * @param name The name of the console
     */
    public Console(String name) {
        this.console = name;
    }

    // Accessor methods for the console name
    public String getConsole() {
        return console;
    }

    public void setConsole(String name) {
        this.console = name;
    }

    // Accessor methods for the list of associated video games
    public List<Videogame> getVideogameList() {
        return videogameList;
    }

    public void setVideogameList(List<Videogame> videogameList) {
        this.videogameList = videogameList;
    }
}
