import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) throws IOException {

        StopWordRemover.readStopWordsFromCSV("stopwords");
        ArrayList<String> arrayListCorpus = FileOperation.readFile("retweets");
        printArrayList(arrayListCorpus);
        storeCorpusFile(arrayListCorpus);

    }


    //Print All content of arrayListCorpus
    private static void printArrayList(ArrayList<String> arrayList){

        for (String s : arrayList) System.out.println(s);

    }

    //Store final arabic corpus to csv file
    public static void storeCorpusFile(ArrayList<String> arrayList) throws IOException {

        Path output = Paths.get("normalize_corpus.csv");

        try {

            Files.write(output, arrayList);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
