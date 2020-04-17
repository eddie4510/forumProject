
package ouhk.comps380f.dao;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ouhk.comps380f.model.PostEntry;


public interface PostRepository extends JpaRepository<PostEntry, Integer> {
    public List<PostEntry> readEntriesByThreadId(int threadId);
    public List<PostEntry> readEntriesByUSERNAME(String USERNAME);
    

    /*@Query("select THREAD_ID from Posts p where p.USERNAME = ?1",nativeQuery = true)
    public int finduserthreadByUSERNAME(String USERNAME);
    //public List<PostEntry> findByPostIdAndFILENAME(int postId, String filename);*/
}
