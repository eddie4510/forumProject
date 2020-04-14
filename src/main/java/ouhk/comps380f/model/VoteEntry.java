
package ouhk.comps380f.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VOTES")
public class VoteEntry implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="VOTE_ID")
    private int voteId;
    
    private String username;
    private int Choice_ID;
    
    public VoteEntry(){}

    public VoteEntry(String username, int Choice_ID) {
        this.username = username;
        this.Choice_ID = Choice_ID;
    }

    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getChoice_ID() {
        return Choice_ID;
    }

    public void setChoice_ID(int Choice_ID) {
        this.Choice_ID = Choice_ID;
    }
    
    
}
