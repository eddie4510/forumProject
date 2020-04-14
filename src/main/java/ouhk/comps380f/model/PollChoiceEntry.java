
package ouhk.comps380f.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "POLLCHOICE")
public class PollChoiceEntry implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="CHOICE_ID")
    private int pollChoiceId;
    
    private String choice;
    
    @Column(name ="POLL_ID")
    private int pollId;
    
    public PollChoiceEntry() {}

    public PollChoiceEntry(String choice, int pollId) {
        this.choice = choice;
        this.pollId = pollId;
    }

    public int getPollChoiceId() {
        return pollChoiceId;
    }

    public void setPollChoiceId(int pollChoiceId) {
        this.pollChoiceId = pollChoiceId;
    }

    public int getPollId() {
        return pollId;
    }

    public void setPollId(int pollId) {
        this.pollId = pollId;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }
    
    
}
