/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.service.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.binding.BindingResult;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

/**
 *
 * @author Luis Miguel & Gerard Pascual
 */
@Controller
@Path("Cart")
public class CartController {
    @Inject UserServiceImpl service;
    @Inject Models models;
    @Inject HttpSession session;
    @Inject BindingResult bindingResult;
    
    @GET
    public String Cart(){
        
        String url = "Cart";
        session.setAttribute("originalURL", url );
        return"cart.jsp";
    }

}
