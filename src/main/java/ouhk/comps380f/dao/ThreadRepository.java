package ouhk.comps380f.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.ThreadEntry;

public interface ThreadRepository  extends JpaRepository<ThreadEntry, Integer> {
    
    public List<ThreadEntry> readEntriesByTYPE(String type);

}
