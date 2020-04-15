
package ouhk.comps380f.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.UserRoleEntry;
public interface UserRoleRepository extends JpaRepository<UserRoleEntry, Integer>{
    public List<UserRoleEntry> findByUSERNAME(String USERNAME); 

}
