package deim.urv.cat.homework2.model;

import jakarta.enterprise.context.RequestScoped;
import java.io.Serializable;

/**
 * The class represents a console entity in the model.
 * @author Luis Miguel & Gerard Pascual
 */
@RequestScoped
public class Console implements Serializable {


    //identifier for the console
    private Long id;

    // Name of the console
    private String console;


    // Default constructor required by JPA
    public Console() {
    
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConsole() {
        return console;
    }
    

    public void setConsole(String console) {
        this.console = console;
    } 

    @Override
    public String toString() {
        return "Console{" + "id=" + id + ", console=" + console + '}';
    }
  
}
