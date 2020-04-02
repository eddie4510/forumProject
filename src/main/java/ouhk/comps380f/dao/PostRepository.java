
package ouhk.comps380f.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.PostEntry;


public interface PostRepository extends JpaRepository<PostEntry, Integer> {
    public List<PostEntry> readEntriesByThreadId(int threadId);
}
