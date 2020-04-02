package ouhk.comps380f.model;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_ROLES")
public class UserRoleEntry {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer USER_ROLE_ID;
    private String USERNAME;
    private String ROLE;

    public UserRoleEntry(){}
    
    public UserRoleEntry(String USERNAME, String ROLE) {
        this.USERNAME = USERNAME;
        this.ROLE = ROLE;
    }
    
    public Integer getUSER_ROLE_ID() {
        return USER_ROLE_ID;
    }

    public void setUSER_ROLE_ID(Integer USER_ROLE_ID) {
        this.USER_ROLE_ID = USER_ROLE_ID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getROLE() {
        return ROLE;
    }

    public void setROLE(String ROLE) {
        this.ROLE = ROLE;
    }
    
    
    
}
