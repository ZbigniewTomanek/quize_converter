package tools;

import com.google.gson.Gson;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import tools.exceptions.UnparsableTestException;
import tools.exceptions.WrongDirectoryException;
import tools.model.Metadata;
import tools.model.Question;
import tools.model.RawQuestion;
import tools.parser.QuestionParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static tools.Constants.*;
import static tools.TestConverterUtils.*;

public class TestConverter {
    private File root;
    private String encoding;

    public TestConverter(String totalPath, String encoding) {
        this.encoding = encoding;
        root = new File(totalPath);
    }

    public void convertToZip(Metadata metadata)
            throws ZipException, IOException, UnparsableTestException, WrongDirectoryException {

        List<File> filesToZip = new ArrayList<>();
        List<File> allFiles;
        Gson gson = new Gson();

        //gather all files from root folder and it's subdirectories
        allFiles = getAllFiles(root);

        //create test.json
        List<Question> test = convertQuestions(allFiles);
        String testJson = gson.toJson(test);
        File testFile = new File(root.toPath()+"/"+TEST_FILENAME);
        writeToFile(testFile, testJson);

        //create metadata.json
        metadata.setNumOfQuestions(test.size()); //update the number of questions after they were parsed
        String metadataJson = gson.toJson(metadata);
        File metadataFile = new File(root.toPath()+"/"+METADATA_FILENAME);
        writeToFile(metadataFile, metadataJson);

        //gather all the image files
        List<File> imagesFiles = getAllImageFiles(allFiles);

        //add files to list of files that will be zipped
        filesToZip.add(metadataFile);
        filesToZip.add(testFile);
        filesToZip.addAll(imagesFiles);

        //create file of the final zippedFile
        File zippedFile = new File(root.toPath()+"/"+metadata.getTestName()+ZIP);

        //delete old archive, if exists
        if (zippedFile.delete())
            System.out.println("Previous test zippedFile was deleted");

        //create new archive
        new ZipFile(zippedFile).addFiles(filesToZip);

        //delete all temporary files
        metadataFile.delete();
        testFile.delete();
    }

    private List<Question> convertQuestions(List<File> allFiles) throws UnparsableTestException, WrongDirectoryException {
        if (!root.exists()) {
            throw new WrongDirectoryException("Folder on path " + root.getPath() + " does not exist!");
        }

        if (allFiles.isEmpty()) {
            throw new WrongDirectoryException("Folder is empty!");
        }

        List<RawQuestion> rawQuestions = loadQuestions(allFiles, encoding);
        List<String> photoNames = getAllImagesNames(allFiles);

        if (rawQuestions.isEmpty()) {
            throw new WrongDirectoryException("No questions in directory!");
        }

        QuestionParser parser =
                QuestionParser.matchParser(rawQuestions.get(0).getQuestionText());

        if (parser == null) {
            throw new UnparsableTestException("No fitting parser found!");
        }

        List<Question> questions = new ArrayList<>();

        for (RawQuestion rq: rawQuestions) {
            Question q = parser.parseQuestion(rq, photoNames);

            if (q == null) {
                System.err.println("Question '" + rawQuestions + "' couldn't be parsed");
            } else {
                questions.add(q);
            }
        }

        if (questions.isEmpty()) {
            throw new WrongDirectoryException("None of questions was parsed!");
        }

        return questions;
    }

}
