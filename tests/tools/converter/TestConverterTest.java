package tools.converter;

import org.junit.jupiter.api.Test;
import tools.TestConverter;
import tools.model.Metadata;

import java.io.File;

import static tools.Constants.UTF8;
import static tools.Constants.WIN1250;

class TestConverterTest {
    private final String w4testPath = "C:\\Users\\zbigi\\Downloads\\Programy\\testsConverter\\sampleW4Test";
    private final String kucTestPath = "C:\\Users\\zbigi\\Downloads\\Programy\\testsConverter\\sampleKucTest";

    @Test
    void convertW4Test() {
        TestConverter tc = new TestConverter(w4testPath, WIN1250);
        Metadata md = new Metadata("Kolosek", "PWR/W4/PPS", "Andrzej");

        try {
            tc.convertToZip(md);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File f = new File(w4testPath +"/kolosek.zip");

        assert f.exists();
        f.delete();
    }

    @Test
    void convertKucTest() {
        TestConverter tc = new TestConverter(kucTestPath, UTF8);
        Metadata md = new Metadata("teleinf", "PWR/W78/PPS", "Andrzej");

        try {
            tc.convertToZip(md);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File f = new File(kucTestPath +"/teleinf.zip");

        assert f.exists();
        f.delete();
    }
}
