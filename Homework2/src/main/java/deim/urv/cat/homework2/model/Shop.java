package deim.urv.cat.homework2.model;

import jakarta.enterprise.context.RequestScoped;
import java.util.List;

/**
 * The class represents a Shop entity in the model.
 * @author Luis Miguel & Gerard Pascual
 */
@RequestScoped
public class Shop {

    // Unique identifier for the shop
    private Long id;
    
    // Name of the shop
    private String name;

    // Address of the shop
    private String address;

     
    // List of video games associated with this shop
    private List<ShopVideogame> videoGames;

    // Default constructor required by JPA
    public Shop() {
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<ShopVideogame> getVideoGames() {
        return videoGames;
    }

    public void setVideoGames(List<ShopVideogame> videoGames) {
        this.videoGames = videoGames;
    }

    
    
}
