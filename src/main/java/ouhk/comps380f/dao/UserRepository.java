
package ouhk.comps380f.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.UserEntry;

public interface UserRepository extends JpaRepository<UserEntry, String>{
    public UserEntry findByUSERNAME(String USERNAME);
    
}
