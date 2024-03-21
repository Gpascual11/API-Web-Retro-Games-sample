/*
 * This service manages operations related to video games.
 * It provides RESTful services to retrieve, create, update, and delete video games.
 * It also offers methods to get all video games, a specific video game, and filtered lists.
 */
package service;

import authn.Secured;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entities.Videogame;
import jakarta.persistence.TypedQuery;
import jakarta.validation.Valid;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.xml.bind.annotation.XmlRootElement;
import model.entities.Console;

@Stateless
@XmlRootElement
@Path("/rest/api/v1/game")
public class VideogameService extends AbstractFacade<Videogame> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    // Constructor to initialize the service with the correct entity class
    public VideogameService() {
        super(Videogame.class);
    }

    /*
     * REST method to get a list of video games with filtering options.
     * Accepts optional parameters for type and console for filtering.
     * Returns an HTTP response with a list of video games in JSON/XML format.
     * In case incorrect parameters are provided, returns an HTTP 400 Bad Request
     * with an error message indicating which parameter is incorrect.
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAll(
            @DefaultValue("") @QueryParam("type") String type,
            @DefaultValue("") @QueryParam("console") String console
    ) {
        List<Videogame> list = null;
        String response1 = null;
        String response2;

        if (!type.equals("") && !console.equals("")) {
            response1 = validateConsole(console);
            response2 = validateType(type);

            if (response1 == null)
                response1 = response2;
            else if (response2 != null)
                response1 = response1 + response2;

            if (response1 == null){    
                list = em.createNamedQuery("Videogame.findByTypeConsole", Videogame.class)
                        .setParameter("type", type)
                        .setParameter("console", console)
                        .getResultList();
            }
        } 
        else if (!type.equals("")) {
            response1 = validateType(type);
            if (response1 == null){
                list = em.createNamedQuery("Videogame.findByType", Videogame.class)
                    .setParameter("type", type)
                    .getResultList();
            }
        } 
        else if (!console.equals("")) {
            response1 = validateConsole(console);
            if (response1 == null){
                list = em.createNamedQuery("Videogame.findByConsole", Videogame.class)
                        .setParameter("console", console)
                        .getResultList();
            }
        } 
        else {
            list = em.createNamedQuery("Videogame.findAll", Videogame.class)
                        .getResultList();
            if (list.isEmpty())
                return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (response1 == null) {
            return Response.ok(list).type(MediaType.APPLICATION_JSON).build();
        } 
        else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response1)
                    .type(MediaType.TEXT_PLAIN)
                    .build();
        }
    }

    private String validateType(String type) {
        try {
            em.createNamedQuery("Videogame.findByType", Videogame.class)
                    .setParameter("type", type)
                    .getSingleResult();
            return null;
        } catch (NoResultException e) {
            return ("Invalid parameter game.type: '" + type + "'.\n");
        }
        catch (NonUniqueResultException e) {
            return null;
        }
    }

    private String validateConsole(String console) {
        try {
            em.createNamedQuery("Videogame.findByConsole", Videogame.class)
                    .setParameter("console", console)
                    .getSingleResult();
            return null;
        } catch (NoResultException e) {
            return ("Invalid parameter game.console: '" + console + "'.\n");
        } catch (NonUniqueResultException e) {
            return null;
        }
    }

    /*
     * REST method to create a new video game.
     * Only accessible by authenticated users (via @Secured annotation).
     * Captures possible errors and manages them by returning an appropriate HTTP status code with a descriptive message.
     */
    @POST
    @Secured
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response createVideogame(@Valid Videogame entity) {
        
        // Check if a video game with this name already exists
        if (isVideogameExists(entity.getName(), entity.getConsole().getConsole())) {
            throw new WebApplicationException("The video game with this name already exists", Response.Status.CONFLICT);
        }

        // Continue with the logic to create the video game
        super.create(entity);
        // Return the response with the status CREATED and the entity of the created video game
        return Response.status(Response.Status.CREATED).entity(entity).build();
    }

    private boolean isVideogameExists(String name, String console) {
        try {
            TypedQuery<Videogame> query;
            
            if (console == null) {
                query = em.createNamedQuery("Videogame.findByName", Videogame.class)
                        .setParameter("name", name);
            } else {
                query = em.createNamedQuery("Videogame.findByNameConsole", Videogame.class)
                        .setParameter("console", console)
                        .setParameter("name", name);
            }

            query.getSingleResult();
            return true; // If a result is found, the name and console already exist
        } catch (NoResultException e) {
            return false; // If no result is found, the name and console don't exist
        } catch (NonUniqueResultException e) {
            // Handle non-unique result if needed (multiple games with the same name and console)
            return true;
        }
    }

    // Afegeix aquest mètode a la classe VideogameService
    public Videogame getVideoGameById(Long id) {
        return em.find(Videogame.class, id);
    }

    

    // Method to provide the necessary EntityManager for interaction with the persistence layer
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
