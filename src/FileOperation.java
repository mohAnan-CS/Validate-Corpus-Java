import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOperation {

    //Read data from file csv file , normalizing a clear arabic data and return arrayList of data
    public static ArrayList<String> readFile(String fileName){

        ArrayList<String> arrayListCorpus = new ArrayList<>();

        try {

            File file = new File(fileName.concat(".csv"));
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {

                String data = scanner.nextLine();
                List<String> string = ArabicTokenize.tokenize(data);
                String sentence = convertList(string);

                if (!sentence.trim().equalsIgnoreCase("")){

                    sentence = StopWordRemover.removeStopWords(sentence);
                    sentence = Normalization.normalize(sentence);
                    arrayListCorpus.add(sentence);

                }

            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return arrayListCorpus ;
    }

    public static String convertList(List<String> stringList){

        String sentence = "" ;
        for (String s : stringList) {
            sentence += s.trim() + " ";
        }
        return sentence.trim();

    }


}
