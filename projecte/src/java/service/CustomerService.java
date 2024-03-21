/*
 * This class is responsible for managing operations related to customers.
 * It provides RESTful services to retrieve, modify, and delete customer information.
 * It also offers methods to get all customers or a specific customer.
 */
package service;

import authn.Credentials;
import authn.Secured;
import java.util.List;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.entities.Customer;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.QueryParam;

@Stateless
@Path("/rest/api/v1/customer")
public class CustomerService extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    // Constructor to initialize the service with the correct entity class
    public CustomerService() {
        super(Customer.class);
    }

    /*
     * REST method to get all customers.
     * Only accessible by authenticated users (via @Secured annotation).
     * Removes the password of each customer before returning the list to maintain confidentiality.
     */
    @GET
    @Path("/all")
    @Produces({ MediaType.APPLICATION_JSON})
    @Override
    public List<Customer> findAll() {
        List<Customer> customers = em.createNamedQuery("Customer.findAll", Customer.class)
                        .getResultList();
        return customers;
    }

    /*
     * REST method to get a specific customer by ID.
     * Only accessible by authenticated users (via @Secured annotation).
     * Removes the customer's password before returning it to maintain confidentiality.
     */
    @GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON})
    public Response find(@PathParam("id") Long id) {
       try {
            if (id <= 0) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid ID: " + id).build();
}
            List<Customer> customer = em.createNamedQuery("Customer.findId", Customer.class)
                    .setParameter("id", id)
                    .getResultList();

            if (customer != null) {
                return Response.ok().entity(customer).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("User not found with ID: " + id).build();
            }
        } catch (NoResultException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("User not found with ID: " + id).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving user").build();
        }
    }
    
    @GET
    @Path("/credentials")
    @Produces({ MediaType.APPLICATION_JSON })
    public Response findCredentials(
        @DefaultValue("") @QueryParam("username") String userName,
        @DefaultValue("") @QueryParam("password") String passWord) {

        try {
            Customer customer = em.createNamedQuery("Customer.findCredentials", Customer.class)
                    .setParameter("username", userName)
                    .setParameter("password", passWord)
                    .getSingleResult();

            return Response.ok().entity(customer.getId()).build();
        } catch (NoResultException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Credentials not found").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error retrieving credentials").build();
        }
    }


    /*
     * REST method to update a specific customer by ID.
     * Only accessible by authenticated users (via @Secured annotation).
     * Specifies the update of the customer's credentials, and other fields can be updated as needed.
     */
    @PUT
    @Secured
    @Path("{id}")
    @Consumes({ MediaType.APPLICATION_JSON})
    public Response edit(@PathParam("id") Long id, Customer entity) {
        try {
            if (entity == null) {
                return Response.status(Response.Status.BAD_REQUEST).entity("The JSON request is empty or malformed.").build();
            }

            Customer existingCustomer = super.find(id);
            if (existingCustomer != null) {
                // Update the email
                String newEmail = entity.getEmail();
                if (newEmail != null && !newEmail.trim().isEmpty()) {
                    existingCustomer.setEmail(newEmail);

                    // Update the username and password through credentials
                    Credentials existingCredentials = existingCustomer.getCredentials();

                    // Add a checkpoint to see if the credentials are null
                    if (existingCredentials == null) {
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Credentials are null.").build();
                    }

                    Credentials newCredentials = entity.getCredentials();

                    // Check if the new credentials are null
                    if (newCredentials != null) {
                        // Verify and update the username if provided
                        String newUsername = newCredentials.getUsername();

                        // Add a checkpoint to see if the username is null
                        if (newUsername != null && !newUsername.trim().isEmpty()) {
                            existingCredentials.setUsername(newUsername);
                        } else {
                            return Response.status(Response.Status.BAD_REQUEST).entity("Username is null or empty.").build();
                        }

                        // Verify and update the password if provided
                        String newPassword = newCredentials.getPassword();

                        // Add a checkpoint to see if the password is null
                        if (newPassword != null && !newPassword.trim().isEmpty()) {
                            existingCredentials.setPassword(newPassword);
                        } else {
                            return Response.status(Response.Status.BAD_REQUEST).entity("Password is null or empty.").build();
                        }
                    }

                    // Other fields can be updated as needed
                    super.edit(existingCustomer);
                    return Response.status(Response.Status.ACCEPTED).build();
                } else {
                    return Response.status(Response.Status.BAD_REQUEST).entity("The email field cannot be empty.").build();
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("User not found with ID: " + id).build();
            }
        } catch (Exception e) {
            // Add a checkpoint to see the stack trace
            e.printStackTrace();
            // Alternatively, you can add a log if you are using a logging system
            // LOG.error("Exception while processing the request: " + e.getMessage(), e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    // Method to provide the necessary EntityManager for interaction with the persistence layer
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
