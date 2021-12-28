package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

import java.util.Date;
import java.util.List;

/**
 * Created by Herbert on 24.08.2015.
 */
public class Questions {

    private Date dateFor;
    private List<Question> easy;
    private List<Question> medium;
    private List<Question> hard;

    public Date getDateFor() {
        return dateFor;
    }

    public void setDateFor(Date dateFor) {
        this.dateFor = dateFor;
    }

    public List<Question> getEasy() {
        return easy;
    }

    public void setEasy(List<Question> easy) {
        this.easy = easy;
    }

    public List<Question> getMedium() {
        return medium;
    }

    public void setMedium(List<Question> medium) {
        this.medium = medium;
    }

    public List<Question> getHard() {
        return hard;
    }

    public void setHard(List<Question> hard) {
        this.hard = hard;
    }
}
