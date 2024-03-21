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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import java.util.List;

/**
 * The class represents a GameType entity in the model.
 * Authors: Luis Miguel Soriano and Gerard Pascual
 */
@Entity
public class GameType {

    // Automatically generated identifier for the game type
    @Id
    @SequenceGenerator(name="GameType_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GameType_Gen")
    private Long id;

    // Type of the game
    private String type;

    // List of video games associated with this game type (prevent JSON serialization)
    @JsonbTransient
    @OneToMany(mappedBy = "type")
    private List<Videogame> videogameList;

    // Default constructor required by JPA
    public GameType() {
    }

    /**
     * Constructor that allows initializing the game type when creating an instance.
     * @param GameType The type of the game
     */
    public GameType(String GameType) {
        this.type = GameType;
    }

    // Accessor method for the game type
    public String getType() {
        return type;
    }

    public void setType(String GameType) {
        this.type = GameType;
    }

    // Accessor method for the list of video games associated with this type
    public List<Videogame> getVideogameList() {
        return videogameList;
    }

    public void setVideogameList(List<Videogame> videogameList) {
        this.videogameList = videogameList;
    }
}
