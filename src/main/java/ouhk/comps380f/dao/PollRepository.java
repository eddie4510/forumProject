
package ouhk.comps380f.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.PollEntry;

public interface PollRepository extends JpaRepository<PollEntry, Integer>{
   
}
