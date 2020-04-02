package ouhk.comps380f.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "THREADS")
public class ThreadEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer THREAD_ID;
    private String TYPE;
    private String TITLE;

    public ThreadEntry() {
    }

    public ThreadEntry(String TYPE, String TITLE) {
        this.TYPE = TYPE;
        this.TITLE = TITLE;
    }

    public Integer getTHREAD_ID() {
        return THREAD_ID;
    }

    public void setTHREAD_ID(Integer THREAD_ID) {
        this.THREAD_ID = THREAD_ID;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

}
