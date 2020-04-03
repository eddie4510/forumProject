
package ouhk.comps380f.model;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ATTACHMENT")
public class AttachmentEntry implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ATTACH_ID;
    private String FILENAME;
    private String CONTENT_TYPE;
    
    @Basic(fetch=FetchType.LAZY)
    @Lob
    private byte[] CONTENT;
    
    @Column(name="POST_ID",insertable=false, updatable=false)
    private Integer postId; 
    
    @ManyToOne
    @JoinColumn(name="POST_ID")
    private PostEntry post;

    public Integer getATTACH_ID() {
        return ATTACH_ID;
    }

    public void setATTACH_ID(Integer ATTACH_ID) {
        this.ATTACH_ID = ATTACH_ID;
    }

    public String getFILENAME() {
        return FILENAME;
    }

    public void setFILENAME(String FILENAME) {
        this.FILENAME = FILENAME;
    }

    public String getCONTENT_TYPE() {
        return CONTENT_TYPE;
    }

    public void setCONTENT_TYPE(String CONTENT_TYPE) {
        this.CONTENT_TYPE = CONTENT_TYPE;
    }

    public byte[] getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(byte[] CONTENT) {
        this.CONTENT = CONTENT;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public PostEntry getPost() {
        return post;
    }

    public void setPost(PostEntry post) {
        this.post = post;
    }
    
    
    
    
}
