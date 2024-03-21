/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deim.urv.cat.homework2.model;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;
import java.io.Serializable;

/**
 *
 * @author Luis Miguel & Gerard Pascual
 */
@Named("customerForm")
@SessionScoped
public class CustomerForm implements Serializable {
    private static final long serialVersionUID = 1L;
        
    // JSR 303 validation
    @NotBlank
    @FormParam("userName")
    @MvcBinding
    @Size(min=2, max=30, message = "username must be between 2 and 30 characters")
    private String username;
    
    // JSR 303 validation
    @NotBlank
    @FormParam("password")
    @MvcBinding
    @Size(min=2, max=30, message = "Password must be between 2 and 30 characters")
    private String password;

    public String getUsername() {
        return fixNull(this.username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return fixNull(this.password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String fixNull(String in) {
        return (in == null) ? "" : in;
    }


}
