package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.controller.User;
import deim.urv.cat.homework2.controller.UserForm;
import deim.urv.cat.homework2.model.Customer;
import deim.urv.cat.homework2.model.RentalRequest;
import deim.urv.cat.homework2.model.Videogame;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
        
public class UserServiceImpl implements UserService {
    private final WebTarget webTargetUser;
    private final WebTarget webTargetVideogame;
    private final WebTarget webTargetCustomer;
    private final WebTarget webTargetRent;
    
    private String credentials = null;
    
    private final jakarta.ws.rs.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/projecte/webresources/rest/api"; // adreça base homework1
    private static final String BASE_URI_VIDEOGAME = "http://localhost:8080/projecte/webresources/rest/api/v1/game"; // adreça base homework1 PER VIDEOGAME
    private static final String BASE_URI_CUSTOMER = "http://localhost:8080/projecte/webresources/rest/api/v1/customer"; // adreça base homework1 PER CUSTOMER
    private static final String BASE_URI_RENT = "http://localhost:8080/projecte/webresources/rest/api/v1/rent"; // adreça base homework1 PER RENT


    public UserServiceImpl() {
        client = jakarta.ws.rs.client.ClientBuilder.newClient();
        webTargetUser = client.target(BASE_URI).path("user");
        webTargetVideogame = client.target(BASE_URI_VIDEOGAME);
        webTargetCustomer = client.target(BASE_URI_CUSTOMER);
        webTargetRent = client.target(BASE_URI_RENT);
    }
    
    @Override
    public User findUserByEmail(String email){
        Response response = webTargetUser.path(email)
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (response.getStatus() == 200) {
            return response.readEntity(User.class);
        }
        return null;
    }

    @Override
    public boolean addUser(UserForm user) {
       Response response = webTargetUser.request(MediaType.APPLICATION_JSON)
               .post(Entity.entity(user, MediaType.APPLICATION_JSON), 
                    Response.class);
     return response.getStatus() == 201;
    }
    

    //Methods from VideoGameService
    
    //GET VIDEOGAME
    public List<Videogame> findVideogames(String type, String console) {
        // URL construction
         WebTarget targetWithParams = webTargetVideogame
                 .queryParam("type", type)
                 .queryParam("console", console);

         // GET Request
         Response response = targetWithParams.request(MediaType.APPLICATION_JSON).get();

         if (response.getStatus() == Response.Status.OK.getStatusCode()) {
             return response.readEntity(new GenericType<List<Videogame>>() {});
         } else {
             return Collections.emptyList();
         }
    }
    
    //POST VIDEOGAME
     public boolean createVideogame(Videogame videogame) {
        // URL construction
        WebTarget target = webTargetVideogame.path("createVideogame");

        // POST request
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(videogame, MediaType.APPLICATION_JSON));

        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
            return true; 
        } else if (response.getStatus() == Response.Status.CONFLICT.getStatusCode()) {
            System.out.println("This Videogame is alredy created (same name)");
            return false;
        } else {
            System.out.println("ERROR bad request " + response.getStatus());
            return false;
        }
    }
     
     
     //Methods from Customer
     
    //GET ALL CUSTOMERS
    public List<Customer> findAllCustomers() {
        // URL construction
        WebTarget target = webTargetCustomer;

        Response response = target.request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return response.readEntity(new GenericType<List<Customer>>() {});
        } else {
            return Collections.emptyList();
        }
    }
    
     
    //GET CUSTOMERS BY ID
    
    public Customer findCustomerId(@PathParam("id") Long id) {
        // Construir la URL para obtener un cliente por ID
        WebTarget target = webTargetCustomer.path("{id}")
                .resolveTemplate("id", id);

        // Realizar la solicitud GET
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            // Parsear la respuesta a un cliente individual y retornarlo
            Customer customer = response.readEntity(Customer.class);
            return customer;
        } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            // Manejar el caso en que el cliente no se encuentre
            System.out.println("User not found with ID: " + id);
            return null; // O manejar de otra manera según tus necesidades
        } else {
            // Manejar otros códigos de estado según sea necesario
            System.out.println("Error fetching user. Status code: " + response.getStatus());
            return null;
        }
    }

    
    //Get customer password/ username
    public Long getCredentials(String username, String password) {
        // URL construction
        WebTarget target = webTargetCustomer
                .path("/credentials")
                .queryParam("username", username)
                .queryParam("password", password);

        // GET request
        Response response = target.request(MediaType.APPLICATION_JSON).get();

        // Check the response status
        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
            return response.readEntity(Long.class);  // Credentials found, return the ID
        } else if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
            System.out.println("Credentials not found");
            return null; // Credentials not found, return null
        } else {
            System.out.println("Error retrieving credentials. Status: " + response.getStatus());
            return null; // Return null
        }
    }

    
    //Methods from RentService
    
    //POST RENT
    public boolean createRent(RentalRequest rrq) {
        // URL construction     
        WebTarget target = webTargetRent.path("createRental");     
        
        // POST request
        Response response = target.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(rrq, MediaType.APPLICATION_JSON));

        if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
            return true; 
        } else if (response.getStatus() == Response.Status.CONFLICT.getStatusCode()) {
            System.out.println("This Rent is already created (same name)");
            return false;
        } else {
            System.out.println("ERROR bad request " + response.getStatus());
            return false;
        }
    }
     
     
     //Methods for credentials
     
     public void Authorization(String username, String password){
        String credentials = username + ":" + password;
        this.credentials = Base64.getEncoder().encodeToString(credentials.getBytes());
    }
     
  
    @Override
    public Customer find(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 
 
}
