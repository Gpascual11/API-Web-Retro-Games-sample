/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

/**
 *
 * @author Luis Miguel & Gerard Pascual
 */
@Controller
@Path("RentProceed")
public class RentProceedController {
    
    @Inject HttpSession session;

    @GET
    public String showForm() {
        session.removeAttribute("RentList");
        session.removeAttribute("TotalPrice");
        return "rentproceed.jsp";  
    } 
}
