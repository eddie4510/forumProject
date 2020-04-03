package ouhk.comps380f.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "POSTS")
public class PostEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer POST_ID;
    private String USERNAME;
    @Column(name="THREAD_ID")
    private Integer threadId;
    private Integer THREAD_SEQ;
    private String CONTENT;
    
    @OneToMany(mappedBy="post", fetch=FetchType.EAGER, cascade=CascadeType.ALL,orphanRemoval=true)
    @Fetch(FetchMode.SUBSELECT)
    private List<AttachmentEntry> attachments=new ArrayList<>();
    
    public PostEntry() {
    }

    public PostEntry(String USERNAME, Integer threadId, Integer THREAD_SEQ, String CONTENT) {
        this.USERNAME = USERNAME;
        this.threadId= threadId;
        this.THREAD_SEQ = THREAD_SEQ;
        this.CONTENT = CONTENT;
    }

    
    
    public Integer getPOST_ID() {
        return POST_ID;
    }

    public void setPOST_ID(Integer POST_ID) {
        this.POST_ID = POST_ID;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public Integer getThreadId() {
        return threadId;
    }

    public void setThreadId(Integer threadId) {
        this.threadId = threadId;
    }

    public Integer getTHREAD_SEQ() {
        return THREAD_SEQ;
    }

    public void setTHREAD_SEQ(Integer THREAD_SEQ) {
        this.THREAD_SEQ = THREAD_SEQ;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public List<AttachmentEntry> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentEntry> attachments) {
        this.attachments = attachments;
    }
    
    public void deleteAttachment(AttachmentEntry attachment){
        attachment.setPost(null);
        this.attachments.remove(attachment);
    }

}
