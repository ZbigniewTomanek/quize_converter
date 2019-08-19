package tools.parser;

import tools.model.Answer;
import tools.model.Question;
import tools.model.RawQuestion;

import java.util.ArrayList;
import java.util.List;

public class W4QuestionParser implements QuestionParser {


    @Override
    public Boolean doesFitToQuestion(String question) {
        String[] lines = question.split("\n");

        if (lines.length < 3)
            return false;

        return  lines[0].charAt(0) == 'X';
    }

    @Override
    public Question parseQuestion(RawQuestion question, List<String> imageNames) {
        if (!doesFitToQuestion(question.getQuestionText()))
            return null;

        String questionText;
        String id = question.getId();
        String image = QuestionParser.getQuestionImageName(id, imageNames);
        List<Answer> answers = new ArrayList<>();

        //retrieve question and parse answers
        String[] lines = question.getQuestionText().split("\n");

        if (lines.length < 3)
            return null;

        String correctAnswers = lines[0].substring(1);

        if (!correctAnswers.contains("1")) {
            System.err.println("Question:\n" + question.getQuestionText() + "\ndoes not contain correct answer");
            return null;
        }

        questionText = lines[1];

        if (lines.length-2 > correctAnswers.length()) {
            System.err.println("More answers than characters in correctness string");
            return null;
        }

        for (int i = 2; i < lines.length; i++) {
            if (!lines[i].isEmpty()) {

                String answerText = lines[i];
                String answerImage = QuestionParser.getAnswerImageName(id, i-2, imageNames);
                String isTrue = (correctAnswers.charAt(i-2) == '1') ? "true" : "false";

                Answer a = new Answer(answerText, isTrue, answerImage);
                answers.add(a);
            }
        }
        return new Question(id, image, questionText, answers);
    }
}