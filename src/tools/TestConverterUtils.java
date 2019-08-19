package tools;

import tools.model.RawQuestion;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static tools.Constants.*;

public class TestConverterUtils {
    public static void writeToFile(File file, String content) throws IOException {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write(content);
        } catch (IOException e) {
            throw e;
        }
    }

    public static List<RawQuestion> loadQuestions(List<File> files, String encoding) {
        List<File> questionFilesList =
                files.stream().filter(f -> f.getName().contains(TXT)).collect(Collectors.toList());

        List<RawQuestion> questionsList = new ArrayList<>();

        for (File f: questionFilesList) {
            try {
                String content = new String( Files.readAllBytes( f.toPath() ), encoding );
                RawQuestion rq = new RawQuestion(content, f.getName());
                questionsList.add(rq);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return questionsList;
    }

    public static List<String> getAllImagesNames(List<File> files) {
        List<String> namesList = files.stream().map(File::getName).collect(Collectors.toList());
        return namesList.stream().filter(n -> n.contains(PNG) || n.contains(JPG)).collect(Collectors.toList());
    }

    public static List<File> getAllImageFiles(List<File> files) {
        return files.stream().filter(f -> f.getName().contains(PNG) || f.getName().contains(JPG))
                .collect(Collectors.toList());
    }

    public static List<File> getAllFiles(File parentDir) {
        List<File> allFiles = new ArrayList<>();
        getAllFilesHelper(parentDir, allFiles);

        return allFiles;
    }

    private static void getAllFilesHelper(File curDir, List<File> allFiles) {
        File[] filesList = curDir.listFiles();

        assert filesList != null;

        for(File f : filesList){
            if(f.isDirectory())
                getAllFilesHelper(f, allFiles);

            if(f.isFile()){
                allFiles.add(f);
            }
        }
    }
}
