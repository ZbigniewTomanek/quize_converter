package tools.parser;

import tools.model.Question;
import tools.model.RawQuestion;

import java.util.List;
import java.util.stream.Collectors;

import static tools.Constants.*;

public interface QuestionParser {
    Boolean doesFitToQuestion(String question);
    Question parseQuestion(RawQuestion question, List<String> imageNames);

    QuestionParser[] parsers = { new W4QuestionParser(), new KucQuestionParser() };

    static QuestionParser matchParser(String question) {
        for (QuestionParser p: parsers) {
            if (p.doesFitToQuestion(question))
                return p;
        }

        return null;
    }

    static String remove0s(String id) {
        int point = 0;
        if (!id.isEmpty()) {
            for (int i = 0; i < id.length(); i++) {
                point = i;
                if (id.charAt(i) != '0') {
                    break;
                }
            }
        }

        return id.substring(point);
    }

    /**
     * @param id Given id of question, eg. 001.txt
     * @param index Absolute index of answer
     * @param imageNames List with names of all images in test directories
     * @return Name of image matched with answer
     */
    static String getAnswerImageName(String id, int index, List<String> imageNames) {
        StringBuilder sb = new StringBuilder();
        sb.append(id.replace(TXT, ""));
        sb.append((char) (65 + index));

        String template = sb.toString();

        List<String> filteredNames =
                imageNames.stream().filter(i -> i.equals(template+JPG) || i.equals(template+PNG)).collect(Collectors.toList());

        if (!filteredNames.isEmpty())
            return filteredNames.get(0);

        String shortTemplate = remove0s(template);

        filteredNames =
                imageNames.stream().filter(i -> i.equals(shortTemplate+JPG) || i.equals(shortTemplate+PNG)).collect(Collectors.toList());

        if (!filteredNames.isEmpty())
            return filteredNames.get(0);
        else
            return "";

    }

    static String getQuestionImageName(String id, List<String> imageNames) {
        String imageNameTemplate;

        /*
         At first we try to find photo with zeros
         For example when id = "001.txt" we're searching for "001.jpg" and "001.png"
         */

        String imgNameJpg = id.replace("txt", "jpg");
        String imgNamePng = id.replace("txt", "png");

        List<String> filteredNames =
                imageNames.stream().filter(i -> i.equals(imgNameJpg) || i.equals(imgNamePng)).collect(Collectors.toList());

        if (!filteredNames.isEmpty()) {
            return filteredNames.get(0);
        }

        /*
         If nothing was found we try to search for name with removed 0s at the beginning
         For example when id = "001.txt" we're searching for "1.jpg" and "1.png"
         */
        imageNameTemplate = remove0s(id);

        String imgNameJpgS = imageNameTemplate.replace("txt", "jpg");
        String imgNamePngS = imageNameTemplate.replace("txt", "png");


        filteredNames =
                imageNames.stream().filter(i -> i.equals(imgNameJpgS) || i.equals(imgNamePngS)).collect(Collectors.toList());

        if (!filteredNames.isEmpty())
            return filteredNames.get(0);
        else
            return "";
    }
}
