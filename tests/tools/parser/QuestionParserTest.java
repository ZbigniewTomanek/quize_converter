package tools.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class QuestionParserTest {

    private List<String> imageNames1 = new ArrayList<>();
    private List<String> imageNames2 = new ArrayList<>();

    private String questionW4 = "X00011\n" +
            "12.[GT-1] Z podanych zjawisk wybrać te, do ilościowego opisu których stosujemy funkcję ekspoNencjalną:        \n" +
            "absorpcja promieniowania alfa w tkance\n" +
            "absorpcja promieniowania pozytonów w nerce\n" +
            "absorpcja elektronów w tkance mięsniowej\n" +
            "Absorpcja fali ultradźwiękowej w wątrobie\n" +
            "Absorpcja fali elektromagnetycznej w wodzie ";

    private String questionKuc = "QQ01100\n" +
            "4.\tZ ilu członów musi się składać szereg Fouriera, aby błąd przybliżenia dowolnego sygnału f(t) przez ten szereg był równy 0?\n" +
            "\t(a) Liczba członów zależy od tego czy szereg będzie w postaci wykładniczej, czy trygonometrycznej.\n" +
            "\t(b) Liczba n jest nieskończona\n" +
            "\t(c) Liczba n musi odpowiadać wymiarowi przestrzeni sygnałów\n" +
            "\t(d) Aby wyliczyć liczbę harmonicznych należy policzyć błąd średniokwadratowy, gdyż błąd średni może się znosić w przedziałach dodatnich i ujemnych\n" +
            "\t(e) Liczba harmonicznych (członów szeregu), dla których błąd przybliżenia wynosi n i zależy od charakterystyki amplitudowej";

    private String questionNonsense = "QQ01100\nlksdals" +
            "dsasdadasdasd";


    @BeforeEach
    void init() {
        imageNames1.add("000.jpg");
        imageNames1.add("001.jpg");
        imageNames1.add("001.png");
        imageNames1.add("004.png");
        imageNames1.add("14.png");

        imageNames2.add("xsxs.jpg");
        imageNames2.add("001A.jpg");
        imageNames2.add("2B.jpg");
        imageNames2.add("111C.png");
    }

    @Test
    void remove0sTest() {
        String data = "001.jpg";
        String res = QuestionParser.remove0s(data);
        assert(res.equals("1.jpg"));

        data = "xdscdcd54120";
        res = QuestionParser.remove0s(data);
        assert(res.equals("xdscdcd54120"));

        data = "";
        res = QuestionParser.remove0s(data);
        assert res.equals("");
    }

    @Test
    void findQuestionImageSuccess() {
        String id = "004.txt";
        String imageName = QuestionParser.getQuestionImageName(id, imageNames1);
        assert imageName.equals("004.png");
    }

    @Test
    void findQuestionImageSuccessNoZeros() {
        String id = "014.txt";
        String imageName = QuestionParser.getQuestionImageName(id, imageNames1);
        assert imageName.equals("14.png");
    }

    @Test
    void findQuestionImageFailure() {
        String id = "015.txt";
        String imageName = QuestionParser.getQuestionImageName(id, imageNames1);
        assert imageName.equals("");
    }

    @Test
    void findQuestionImageDuplicate() {
        String id = "001.txt";
        String imageName = QuestionParser.getQuestionImageName(id, imageNames1);
        assert imageName.equals("001.png") || imageName.equals("001.jpg");
    }

    @Test
    void findAnswerImageNameSuccess() {
        String id = "001.txt";
        int index = 0;
        String imageName = QuestionParser.getAnswerImageName(id, index, imageNames2);
        assert imageName.equals("001A.jpg");
    }

    @Test
    void findAnswerImageNameSuccessNoZeros() {
        String id = "002.txt";
        int index = 1;
        String imageName = QuestionParser.getAnswerImageName(id, index, imageNames2);
        assert imageName.equals("2B.jpg");
    }

    @Test
    void findAnswerImageNameFailure() {
        String id = "069.txt";
        int index = 0;
        String imageName = QuestionParser.getAnswerImageName(id, index, imageNames2);
        assert imageName.equals("");
    }

    @Test
    void sublistTest() {
        Integer[] ints = {1, 2, 3, 4};
        List<Integer> lint = Arrays.asList(ints);

        assert lint.subList(1, lint.size()).size() == 3;
    }

    @Test
    void matchParserSuccess() {
        assert QuestionParser.matchParser(questionKuc) instanceof KucQuestionParser;
        assert QuestionParser.matchParser(questionW4) instanceof W4QuestionParser;
    }

    @Test
    void matchParserFailure() {
        assert QuestionParser.matchParser(questionNonsense) == null;
    }
}
