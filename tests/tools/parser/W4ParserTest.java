package tools.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools.model.Answer;
import tools.model.Question;
import tools.model.RawQuestion;

import java.util.ArrayList;
import java.util.List;

public class W4ParserTest {
    private String question1 = "X00011\n" +
            "12.[GT-1] Z podanych zjawisk wybrać te, do ilościowego opisu których stosujemy funkcję ekspoNencjalną:        \n" +
            "absorpcja promieniowania alfa w tkance\n" +
            "absorpcja promieniowania pozytonów w nerce\n" +
            "absorpcja elektronów w tkance mięsniowej\n" +
            "Absorpcja fali ultradźwiękowej w wątrobie\n" +
            "Absorpcja fali elektromagnetycznej w wodzie ";

    private String question2 = "QQ01100\n" +
            "4.\tZ ilu członów musi się składać szereg Fouriera, aby błąd przybliżenia dowolnego sygnału f(t) przez ten szereg był równy 0?\n" +
            "\t(a) Liczba członów zależy od tego czy szereg będzie w postaci wykładniczej, czy trygonometrycznej.\n" +
            "\t(b) Liczba n jest nieskończona\n" +
            "\t(c) Liczba n musi odpowiadać wymiarowi przestrzeni sygnałów\n" +
            "\t(d) Aby wyliczyć liczbę harmonicznych należy policzyć błąd średniokwadratowy, gdyż błąd średni może się znosić w przedziałach dodatnich i ujemnych\n" +
            "\t(e) Liczba harmonicznych (członów szeregu), dla których błąd przybliżenia wynosi n i zależy od charakterystyki amplitudowej";

    private String question3 = "X010001\n" +
            "Odpowiedź impulsowa opisuje:\n" +
            "odpowiedź systemu na skok jednostkowy\n" +
            "odpowiedź systemu na impuls Kronecker'a\n" +
            "odpowiedź systemu na sygnał sinusoidalny o zadanej częstotliwości\n" +
            "odpowiedź systemu na impuls Gibs'a\n" +
            "odpowiedź systemu na funkcję grzebieniową\n" +
            "odpowiedź systemu na impuls Dirac'a";

    private String question4 = "X010\n" +
            "Odpowiedź impulsowa opisuje:\n" +
            "odpowiedź systemu na skok jednostkowy\n" +
            "odpowiedź systemu na impuls Kronecker'a\n" +
            "odpowiedź systemu na sygnał sinusoidalny o zadanej częstotliwości\n" +
            "odpowiedź systemu na impuls Gibs'a\n" +
            "odpowiedź systemu na funkcję grzebieniową\n" +
            "odpowiedź systemu na impuls Dirac'a";


    private QuestionParser parser = new W4QuestionParser();
    private Question question1Parsed;
    private List<String> imageNames1 = new ArrayList<>();

    @BeforeEach
    void init() {
        imageNames1.add("000.jpg");
        imageNames1.add("001.jpg");
        imageNames1.add("004.png");
        imageNames1.add("14.png");


        String question = "Odpowiedź impulsowa opisuje:";
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("odpowiedź systemu na skok jednostkowy", "false", ""));
        answers.add(new Answer("odpowiedź systemu na impuls Kronecker'a", "true", ""));
        answers.add(new Answer("odpowiedź systemu na sygnał sinusoidalny o zadanej częstotliwości", "false", ""));
        answers.add(new Answer("odpowiedź systemu na impuls Gibs'a", "false", ""));
        answers.add(new Answer("odpowiedź systemu na funkcję grzebieniową", "false", ""));
        answers.add(new Answer("odpowiedź systemu na impuls Dirac'a", "true", ""));
        question1Parsed = new Question("001.txt", "001.jpg", question, answers);
    }

    @Test
    void rightKindOfQuestion() {
        assert parser.doesFitToQuestion(question1);
    }

    @Test
    void wrongKindOfQuestion() {
        assert !parser.doesFitToQuestion(question2);
    }

    @Test
    void parseSuccess() {
        Question q = parser.parseQuestion(new RawQuestion(question3, "001.txt"), imageNames1);
        assert q.equals(question1Parsed);
    }

    @Test
    void parseFailure() {
        Question q = parser.parseQuestion(new RawQuestion(question4, "001.txt"), imageNames1);
        assert q == null;
    }

    @Test
    void parseFailureWrongFormat() {
        Question q = parser.parseQuestion(new RawQuestion(question2, "001.txt"), imageNames1);
        assert q == null;
    }
}
