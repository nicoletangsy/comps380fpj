package ouhk.comps380f.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Users implements Serializable {

    @Id
    private String username;
    
    @Column(insertable = false, updatable = false)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> roles = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PollResponse> response = new ArrayList<>();

    public Users() {
    }

    public Users(String username, String password, String[] roles) {
        this.username = username;
        this.password = password;
        for (String role : roles) {
            this.roles.add(new UserRole(this, role));
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public List<PollResponse> getResponse() {
        return response;
    }

    public void setResponse(List<PollResponse> response) {
        this.response = response;
    }
    
    public void deleteResponse(PollResponse oneResponse) {
        oneResponse.setUser(null);
        this.response.remove(response);
    }
}