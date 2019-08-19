package tools.model;

public class Metadata {
    private String testName;
    private String localPath;
    private String authors;
    private int numOfQuestions;

    public Metadata(String testName, String localPath, String authors) {
        this.testName = testName;
        this.localPath = localPath;
        this.authors = authors;
        this.numOfQuestions = -1;
    }

    public Metadata() {
        this.testName = "";
        this.localPath = "";
        this.authors = "";
        this.numOfQuestions = 0;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public int getNumOfQuestions() {
        return numOfQuestions;
    }

    public void setNumOfQuestions(int numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
    }
}
