
package ouhk.comps380f.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.VoteEntry;


public interface VoteRepository extends JpaRepository<VoteEntry, Integer>{
    
}
