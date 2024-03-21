/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.RentalRequest;
import deim.urv.cat.homework2.model.Videogame;
import deim.urv.cat.homework2.service.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.security.CsrfProtected;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import java.util.List;
import java.util.logging.Logger;


/**
 *
 * @author Luis Miguel & Gerard Pascual
 */
@Controller
@Path("Rent")
public class RentController {
    @Inject UserServiceImpl service;
    @Inject Models models;
    @Inject Logger log;
    @Inject HttpSession session;
    @Inject BindingResult bindingResult;
    
    @POST
    @UriRef("Rent")
    @CsrfProtected
    public String createRent() {

       List<Videogame> rentList = (List<Videogame>) session.getAttribute("RentList");
       RentalRequest rrq = null;
       List<Long> gamesId = null;
       List<Long> shopId = null;
       
       Long num = 1L;              
       for (Videogame v : rentList) {
           gamesId.add(v.getId());
           shopId.add(num);
       }
       
       rrq.setCustomer(num);
       rrq.setGameIDs(gamesId);
       rrq.setStoreIDs(shopId);


       service.createRent(rrq);
       
       return "rentproceed.jsp";
        
    }
  
}
