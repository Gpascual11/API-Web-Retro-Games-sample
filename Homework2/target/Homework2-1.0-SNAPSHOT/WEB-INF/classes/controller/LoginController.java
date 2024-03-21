/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.CustomerForm;
import deim.urv.cat.homework2.model.Customer;
import deim.urv.cat.homework2.service.UserServiceImpl;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.security.CsrfProtected;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Luis Miguel & Gerard Pascual
 */
@Controller
@Path("login")
public class LoginController {
    
    @Inject UserServiceImpl service;
    @Inject Models models;
    @Inject Logger log;
    @Inject HttpSession session;
    @Inject BindingResult bindingResult;
    @Inject Customer customer;
    
    
    @GET
    public String showForm() {
        return "loginform.jsp"; 
    }
    
    
    @POST
    @UriRef("login")
    @CsrfProtected
    public String login(@Valid @BeanParam CustomerForm customerForm) {
        models.put("customerForm", customerForm);
        Long customerId = service.getCredentials(customerForm.getUsername(), customerForm.getPassword());

        if (customerId != null) {
            models.put("message", "Correct User");
            session.setAttribute("authenticated", true);
            String CustomerName = customerForm.getUsername();
            session.setAttribute("CustomerSession", CustomerName);
        } else {
            models.put("message", "Incorrect User");
            session.setAttribute("authenticated", false);
            return "loginform.jsp";
        }
        
        log.log(Level.INFO, "Redirecting to the original URL:");
        // Elimina la URL almacenada en la sesiÃ³n para evitar redireccionar a la misma pÃ¡gina en futuros logins
        String originalURL = (String) session.getAttribute("originalURL");
        System.out.println("URL " + originalURL);
        session.removeAttribute("originalURL");
        return "redirect:"+"/"+originalURL;
   
    }
  
}
