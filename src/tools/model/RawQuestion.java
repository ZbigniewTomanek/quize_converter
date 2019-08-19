package tools.model;

public class RawQuestion {
    private String questionText;
    private String id;

    public RawQuestion(String questionText, String id) {
        this.questionText = questionText;
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RawQuestion) {
            RawQuestion rq = (RawQuestion) obj;
            return rq.id.equals(this.id) && rq.questionText.equals(this.questionText);
        }
        else {
            return super.equals(obj);
        }
    }

    @Override
    public String toString() {
        return id+"\t"+questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
