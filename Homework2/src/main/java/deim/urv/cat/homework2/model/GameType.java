package deim.urv.cat.homework2.model;

import jakarta.enterprise.context.RequestScoped;

/**
 * The class represents a GameType entity in the model.
 * @author Luis Miguel & Gerard Pascual
 */
@RequestScoped
public class GameType {

    //identifier for the game type
    private Long id;

    // Type of the game
    private String type;


    // Default constructor required by JPA
    public GameType() {
    }

    
    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GameType{" + "id=" + id + ", type=" + type + '}';
    }

    
}
