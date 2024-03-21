package deim.urv.cat.homework2.service;

import deim.urv.cat.homework2.controller.User;
import deim.urv.cat.homework2.controller.UserForm;
import deim.urv.cat.homework2.model.Customer;
import deim.urv.cat.homework2.model.RentalRequest;
import deim.urv.cat.homework2.model.Videogame;
import jakarta.ws.rs.PathParam;
import java.util.List;

public interface UserService {
    
    public User findUserByEmail(String email);
    public boolean addUser(UserForm user);
    public List<Videogame> findVideogames(String type, String console);
    public boolean createVideogame(Videogame videogame);
    public List<Customer> findAllCustomers();
    public Customer find(@PathParam("id") Long id);
    public boolean createRent(RentalRequest rrq);
    
}
