package tools.converter;

import org.junit.jupiter.api.Test;
import tools.TestConverterUtils;
import tools.model.RawQuestion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static tools.Constants.WIN1250;

class TestConverterUtilsTest {
    private final String testPath = "C:\\Users\\zbigi\\Downloads\\Programy\\testsConverter\\sampleW4Test";

    @Test
    void writeToFileTest() {
        File f = new File(testPath+"/testFile.txt");
        String data = "Aba\ncaba\trapa";

        try {
            TestConverterUtils.writeToFile(f, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String content = "";
        try {
            content = new String(Files.readAllBytes(f.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert data.equals(content);
        f.delete();
    }

    @Test
    void getAllFilesTest() {
        List<File> allFiles = TestConverterUtils.getAllFiles(new File(testPath));
        assert allFiles.size() == 8;
    }

    @Test
    void loadQuestionsTest() {
        List<File> allFiles = TestConverterUtils.getAllFiles(new File(testPath));
        List<RawQuestion> q = TestConverterUtils.loadQuestions(allFiles, WIN1250);

        List<RawQuestion> rq = new ArrayList<>();
        rq.add(new RawQuestion("X11001\n" +
                "[img]006.png[/img]\n" +
                "[img]006A.png[/img]\n" +
                "na pewno są liniowo niezależne\n" +
                "na pewno są ortonormalne\n" +
                "[img]006D.png[/img]\n" +
                "na pewno są prostopadłe", "006.txt"));

        rq.add(new RawQuestion("X001000000\n" +
                "[img]015.png[/img]\n" +
                "[img]015A.png[/img]\n" +
                "[img]015B.png[/img]\n" +
                "[img]015C.png[/img]\n" +
                "[img]015D.png[/img]\n" +
                "[img]015E.png[/img]\n" +
                "[img]015F.png[/img]\n" +
                "[img]015G.png[/img]\n" +
                "[img]015H.png[/img]\n" +
                "[img]015I.png[/img]", "015.txt"));

        System.out.println(q);
        assert rq.equals(q);
    }

    @Test
    void getAllImagesNamesTest() {
        List<File> allFiles = TestConverterUtils.getAllFiles(new File(testPath));
        List<String> imageNames = TestConverterUtils.getAllImagesNames(allFiles);
        System.out.println(imageNames);

        assert imageNames.size() == 6;
    }

    @Test
    void getAllImageFilesTest() {
        List<File> allFiles = TestConverterUtils.getAllFiles(new File(testPath));
        List<File> imageNames = TestConverterUtils.getAllImageFiles(allFiles);
        System.out.println(imageNames);

        assert imageNames.size() == 6;
    }

}
