package deim.urv.cat.homework2.model;

import jakarta.enterprise.context.RequestScoped;


/**
 *
 * @author Luis Miguel & Gerard Pascual
 */
@RequestScoped
public class ShopVideogame {
    
    
    private Long id;

    private int stock;

   
    private Videogame game;

    
    private Shop shop;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Videogame getGame() {
        return game;
    }

    public void setGame(Videogame game) {
        this.game = game;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

   

}
