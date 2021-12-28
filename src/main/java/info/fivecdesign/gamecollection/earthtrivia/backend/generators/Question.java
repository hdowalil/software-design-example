package info.fivecdesign.gamecollection.earthtrivia.backend.generators;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Herbert on 27.07.2015.
 */
public class Question {

    private String question;

    // in case there is an image to display
    private Integer resource2Display;

    // at index 0 is the correct answer, shuffle when you display!
    private List<String> answers = new ArrayList<String>(4);


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getAnswer(int index) {
        if (answers == null || index >= answers.size()) {
            return null;
        }
        return answers.get(index);
    }

    public boolean addAnswer(String answer) {
        if (answers.contains(answer)) {
            return false;
        } else {
            answers.add(answer);
            return true;
        }
    }

    public int getNumberOfAnswers() {
        return answers.size();
    }

    public Integer getResource2Display() {
        return resource2Display;
    }

    public void setResource2Display(Integer resource2Display) {
        this.resource2Display = resource2Display;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", resource2Display=" + resource2Display +
                ", answers=" + answers +
                '}';
    }
}
