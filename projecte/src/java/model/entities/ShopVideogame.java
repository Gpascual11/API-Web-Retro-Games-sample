/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 *
 * @author Luis Miguel Soriano i Gerard Pascual
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="ShopVideogame.find", 
        query="SELECT sg FROM ShopVideogame sg "
                + "WHERE sg.shop = :shop "
                + "AND sg.game = :game")
})
@Table(name = "VIDEOGAME_SHOP")
public class ShopVideogame {
    
    @Id @Column(name = "SHOP_VIDEOGAME")
    @SequenceGenerator(name="SHOP_VIDEOGAME_GEN", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHOP_VIDEOGAME_GEN") 
    private Long id;

    private int stock;

    @ManyToOne
    private Videogame game;

    @ManyToOne
    private Shop shop;

    public Long getGameId() {
        return game.getId();
    }
    
    public Long getShopId() {
        return shop.getId();
    }
    
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

    public void decreaseStock() {
        this.stock--;
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
