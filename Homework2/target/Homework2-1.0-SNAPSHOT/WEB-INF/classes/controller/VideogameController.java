package deim.urv.cat.homework2.controller;

import deim.urv.cat.homework2.model.Videogame;
import deim.urv.cat.homework2.service.UserService;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import java.util.ArrayList;

import java.util.List;

@Controller
@Path("videogames")
public class VideogameController {

    @Inject UserService service;
    @Inject Models models;
    @Context HttpServletRequest request;
    @Inject HttpSession session;
    
    

    @GET
    @UriRef("ListVideogames")
    public String getVideogames(@QueryParam("type") String type, @QueryParam("console") String console) {
        // Retrieve the list of videogames from the service
        List<Videogame> videogames = service.findVideogames(type, console);

        models.put("videogames", videogames);
        request.setAttribute("videogames", videogames);
        String url = "videogames";
        session.setAttribute("originalURL", url );

        return "getvideogames.jsp";
    }
    
    
    @GET
    @Path("details/{name}")
    public String getDetailsVideogame(@PathParam("name") String name) {
        List<Videogame> videogames = service.findVideogames("","");
        Videogame target = new Videogame();

        for(Videogame videogame: videogames){
            if(videogame.getName().equals(name)){
                target = videogame; 
            }
        }
        
        models.put("videogame", target);
        request.setAttribute("videogame", target);
        
        return "detailsVideogame.jsp";
    }
    
    @POST
    @UriRef("addCart")
    public String addToCart(@FormParam("name") String name){
        List<Videogame> videogames = service.findVideogames("","");
        Videogame target = new Videogame();
        Double totalPrice = 0.0;
        
        for(Videogame videogame: videogames){
            if(videogame.getName().equals(name)){
                target = videogame; 
            }
        }
        models.put("videogame", target);
        session.setAttribute("videogame", target);
        
        List<Videogame> RentList = (List<Videogame>) session.getAttribute("RentList");
        if(RentList == null){
            RentList = new ArrayList<Videogame>();
            RentList.add(target);
            for(Videogame v : RentList){
             totalPrice += v.getPriceRent(); 
            }
        }
        else{
            RentList.add(target);
            for(Videogame v : RentList){
             totalPrice += v.getPriceRent(); 
            }
        }
        
        session.setAttribute("TotalPrice", totalPrice);
        session.setAttribute("RentList", RentList);
        
        String url = "Cart";
        session.setAttribute("originalURL", url );
        
        return "cart.jsp";
    }
}
