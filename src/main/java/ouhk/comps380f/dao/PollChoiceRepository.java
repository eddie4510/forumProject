
package ouhk.comps380f.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.PollChoiceEntry;


public interface PollChoiceRepository extends JpaRepository<PollChoiceEntry, Integer>{
    
 public List<PollChoiceEntry> readEntriesByPollId(int pollId);
    
}
