package tools.model;

public class Answer {
    private String answerText;
    private String isTrue;
    private String imageName;

    public Answer(String answerText, String isTrue, String imageName) {
        this.answerText = answerText;
        this.isTrue = isTrue;
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(answerText);
        sb.append(" /");
        sb.append(isTrue);
        sb.append("/, image: ");
        sb.append(imageName);
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Answer) {
            Answer a = (Answer) obj;
            return a.answerText.equals(this.answerText)
                    && a.imageName.equals(this.imageName)
                    && a.isTrue.equals(this.isTrue);
        }
        return super.equals(obj);
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getIsTrue() {
        return isTrue;
    }

    public void setIsTrue(String isTrue) {
        this.isTrue = isTrue;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
