
package ouhk.comps380f.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.AttachmentEntry;

public interface AttachmentRepository extends JpaRepository<AttachmentEntry, Integer>{
    public AttachmentEntry findByPostIdAndFILENAME(int postId, String filename);
    public AttachmentEntry findByPostId(int postId);
    
}
