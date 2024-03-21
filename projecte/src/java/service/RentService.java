/*
 * This service manages operations related to video game rentals.
 * It provides RESTful services to retrieve, create, and obtain detailed information about rentals.
 * It also offers methods to get all rentals or a specific rental.
 */
package service;

import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entities.Rent;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import model.dto.RentalRequest;
import model.entities.Customer;
import model.entities.Shop;
import model.entities.ShopVideogame;
import model.entities.Videogame;

@Stateless
@Path("/rest/api/v1/rental")
public class RentService extends AbstractFacade<Rent> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    // Constructor to initialize the service with the correct entity class
    public RentService() {
        super(Rent.class);
    }

    /*
     * REST method to create a new rental and return details of the created rental.
     * Only accessible by authenticated users (via @Secured annotation).
     * Captures possible errors and manages them by returning an appropriate HTTP status code with a descriptive message.
     */
    @POST
    @Secured
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response createRental(
        RentalRequest rrq
    ){
        Long customer = rrq.getCustomer();
        List<Long> gameIDs = rrq.getGameIDs();
        List<Long> storeIDs = rrq.getStoreIDs();
        Customer c = em.find(Customer.class, customer);
        
        if (c == null){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("UserID '" + customer + "' does not exist.\n")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        if (gameIDs.size() != storeIDs.size()){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("StoreIDs.size '" + storeIDs.size() + "' differs from GameIDs.size '" + gameIDs.size() + "'.\n")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
        if (gameIDs.size() < 1){
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("StoreIDs/GameIDs.size must be greater than 0\n")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
 
        Rent r = new Rent();
        r.setCustomer(c);
        ShopVideogame sg;
        double totalPrice = 0;

        for (int i = 0; i < gameIDs.size(); i++){
            Videogame g = em.find(Videogame.class, gameIDs.get(i));
            Shop s = em.find(Shop.class, storeIDs.get(i));
            if (g != null && s != null) {
                try {
                    sg = em.createNamedQuery("ShopVideogame.find", ShopVideogame.class)
                        .setParameter("shop", s)
                        .setParameter("game", g)
                        .getSingleResult();  // Find the stock between game and store

                    if (sg.getStock() > 0){
                        totalPrice = totalPrice + g.getPriceRent();
                        sg.decreaseStock();
                        em.merge(sg);   // Update stock
                        r.addGame(g);   // Add game to rental list
                    }
                    else {
                        // No stock
                        return Response.status(Response.Status.BAD_REQUEST)
                            .entity("No stock of game with ID '" + g.getId() + "' in store " + s.getId() + "'.\n")
                            .type(MediaType.TEXT_PLAIN)
                            .build();
                    }   
                }
                catch (NoResultException e){
                    return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Game with ID '" + gameIDs.get(i) + "' not found in store with ID '" + storeIDs.get(i) + ".\n")
                        .type(MediaType.TEXT_PLAIN)
                        .build();
                }
            }
            else {
                if (g == null && s == null)
                return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Game with ID '" + gameIDs.get(i) + "' not found.\nStore with ID '" + storeIDs.get(i) + "' not found.\n")
                    .type(MediaType.TEXT_PLAIN)
                    .build();
                
                if (g == null)
                        return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Game with ID '" + gameIDs.get(i) + "' not found.\n").type(MediaType.TEXT_PLAIN).build();
                
                if (s == null)
                        return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Store with ID '" + storeIDs.get(i) + "' not found.\n").type(MediaType.TEXT_PLAIN).build();
            }

        }
        r.setTotalPrice(totalPrice);
        r.setReturnDate(LocalDate.now().plusWeeks(1));
        em.persist(r);
        return Response.ok()
                 .entity(r)
                 .type(MediaType.APPLICATION_JSON)
                 .build();
    }

    /*
     * REST method to get a specific rental by ID.
     * Only accessible by authenticated users (via @Secured annotation).
     */
    @GET
    @Secured
    @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") long id) {
        return Response.ok().entity(super.find(id)).build();
    }

    // Method to provide the necessary EntityManager for interaction with the persistence layer
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
