/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.FormParam;
import java.io.Serializable;

/**
 *
 * @author Luis Miguel & Gerard Pascual
 */
@Named("user")
@RequestScoped
public class User implements Serializable{
    
    @MvcBinding
    @NotNull
    @FormParam("userName")
    private String username;
    
    @MvcBinding
    @NotNull
    @FormParam("password")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }   
}