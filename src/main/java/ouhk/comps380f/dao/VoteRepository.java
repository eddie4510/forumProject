
package ouhk.comps380f.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.VoteEntry;


public interface VoteRepository extends JpaRepository<VoteEntry, Integer>{
    
    long countByChoiceId(int choiceId);
   
    List<VoteEntry> readEntriesByUsername(String usernname);
}
