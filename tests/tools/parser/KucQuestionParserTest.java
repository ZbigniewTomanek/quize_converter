package tools.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.model.Answer;
import tools.model.Question;
import tools.model.RawQuestion;

import java.util.ArrayList;
import java.util.List;

class KucQuestionParserTest {
    private String question1 = "QQ10000\n" +
            "1.\tW jakim zakresie mieści się faza harmoniczniej n jeśli współczynnik Fn posiada część rzeczywistą mniejszą od 0 i urojoną większą od 0?\n" +
            "\t(a) Faza mieści się w przedziale od pi/2 do pi\n" +
            "\t(b) Faza będzie odpowiadać kątowi z 4 ćwiartki\n" +
            "\t(c) Faza wyniesie n * wartość harmonicznej bazowej\n" +
            "\t(d) Faza jest zawsze większa od 0\n" +
            "\t(e) Określenie zakresu fazy nie jest możliwe i zależy od sygnału wejściowego f(t)";

    private String question2 = "QQ100011\n" +
            "29.\tJakie wartości będą posiadać współczynniki trygonometrycznego szeregu Fouriera dla funkcji f(t) parzystej?\n" +
            "\t(a) Nie można okreslic wartości współczynnika a0 dla podanych wyżej danych\n" +
            "\t(b) Wszystkie współczynniki an będą różne od 0\n" +
            "\t(c) Współczynniki zawsze zależą od przesunięcia fazowego i nie można ich określić\n" +
            "\t(d) Wszystkie współczynniki an będą zerami\n" +
            "\t(e) Wszystkie współczynniki bn będą zerami\n" +
            "\t(f) Nie można okreslic wartości współczynnika an. Współczynniki te będą zależeć od kształtu sygnału wejściowego f(t)";

    private String question3 = "X000010\n" +
            "[GT-1]    Powszechnie znaną wartość potencjału spoczynkowego komórki o ładunku netto ŁAN płynu zwenątrzkom PZK i wewnatrzkom PWK można wyrazić:  \n" +
            "ŁAN PZK <0 i PWK<0\n" +
            "ŁAN PZK >0 i PWK <0\n" +
            "ŁAN PZK ≈ 0 i ŁAN PWK < 0\n" +
            "ŁAN PZK ≈ 0 i ŁAN PWK > 0 DLACZEGO?\n" +
            "ŁAN PZK > 0 i ŁAN PWK ≈ 0";

    private String question4 = "QQ1sdasadasd00011\n" +
            "29.\tJakie wartości będą posiadaćsadasda współczynniki trygonometrycznego szeregu Fouriera dla funkcji f(t) parzystej?";

    private QuestionParser parser = new KucQuestionParser();
    private Question question1Parsed;
    private List<String> imageNames1 = new ArrayList<>();

    @BeforeEach
    void init() {
        imageNames1.add("000.jpg");
        imageNames1.add("001.jpg");
        imageNames1.add("004.png");
        imageNames1.add("14.png");


        String question = "1.\tW jakim zakresie mieści się faza harmoniczniej n jeśli współczynnik Fn posiada część rzeczywistą mniejszą od 0 i urojoną większą od 0?";
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("(a) Faza mieści się w przedziale od pi/2 do pi", "true", ""));
        answers.add(new Answer("(b) Faza będzie odpowiadać kątowi z 4 ćwiartki", "false", ""));
        answers.add(new Answer("(c) Faza wyniesie n * wartość harmonicznej bazowej", "false", ""));
        answers.add(new Answer("(d) Faza jest zawsze większa od 0", "false", ""));
        answers.add(new Answer("(e) Określenie zakresu fazy nie jest możliwe i zależy od sygnału wejściowego f(t)", "false", ""));
        question1Parsed = new Question("001.txt", "001.jpg", question, answers);
    }

    @Test
    void rightKindOfQuestion() {
        assert parser.doesFitToQuestion(question1);
    }

    @Test
    void wrongKindOfQuestion() {
        assert !parser.doesFitToQuestion(question3);
    }

    @Test
    void brokenQuestion() {
        assert !parser.doesFitToQuestion(question4);
    }

    @Test
    void parseSuccess() {
        Question q = parser.parseQuestion(new RawQuestion(question1, "001.txt"), imageNames1);
        assert q.equals(question1Parsed);
    }

    @Test
    void parseFailure() {
        Question q = parser.parseQuestion(new RawQuestion(question4, "001.txt"), imageNames1);
        assert q == null;
    }

    @Test
    void parseFailureWrongFormat() {
        Question q = parser.parseQuestion(new RawQuestion(question3, "001.txt"), imageNames1);
        assert q == null;
    }
}
