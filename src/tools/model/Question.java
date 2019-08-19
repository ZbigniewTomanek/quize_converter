package tools.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String id;
    private String imageName;
    private String question;
    private List<Answer> answers;

    public Question(String id, String imageName, String question, List<Answer> answers) {
        this.id = id;
        this.imageName = imageName;
        this.question = question;
        this.answers = answers;
    }

    public Question() {
        this.id = "";
        this.imageName = "";
        this.question = "";
        answers = new ArrayList<Answer>();
    }

    @Override
    public String toString() {
        return question + ":\n" + answers.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Question) {
            Question q = (Question) obj;
            return q.answers.equals(this.answers)
                    && q.question.equals(this.question)
                    && q.imageName.equals(this.imageName)
                    && q.id.equals(this.id);
        }

        return super.equals(obj);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
