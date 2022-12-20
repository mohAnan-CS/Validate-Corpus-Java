import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) throws IOException {

        ArrayList<String> arrayListCorpus = readFile("C:\\Users\\twitter\\IdeaProjects\\ValidateCorpus\\src\\retweets");
        ArrayList<String> arrayListFinalCorpus = removeSpaces(arrayListCorpus);
        printArrayList(arrayListFinalCorpus);
        storeCorpusFile(arrayListFinalCorpus);

    }

    //Print All content of arrayListCorpus
    private static void printArrayList(ArrayList<String> arrayList){
        for (String s : arrayList) System.out.println(s);
    }

    //Remove retweets that content is empty
    private static ArrayList<String> removeSpaces(ArrayList<String> arrayListCorpus) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String listCorpus : arrayListCorpus)
            if (!listCorpus.trim().equalsIgnoreCase(""))
                arrayList.add(listCorpus);

        return arrayList;
    }

    //Remove All un arabic word
    public static String findArabicString(String s) {
        StringBuilder finalValue = new StringBuilder();

        if (null != s) {
            for (int i = 0; i < s.length();) {
                int c = s.codePointAt(i);
                // 32 is unicode for white space
                if ((c >= 0x0600 && c <= 0x06E0) || c == 32)
                    finalValue.append((char) c);
                i += Character.charCount(c);
            }
        }

        return finalValue.toString();
    }

    //Read data from file csv file , normalizing a clear arabic data and return arrayList of data
    public static ArrayList<String> readFile(String fileName){

        ArrayList<String> arrayListCorpus = new ArrayList<>();

        try {
            File file = new File(fileName.concat(".csv"));
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                data = findArabicString(data);
                data = data.replace('أ' , 'ا');
                data = data.replace('ة' , 'ه');
                data = data.replace("إ", "ا");
                data = data.replace("؟", "");
                data = data.replace("%", "");
                data = normalize(data);
                data = data.trim();
                arrayListCorpus.add(data);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return arrayListCorpus ;
    }

    //Remove Al-Tashkeel from arabic word and symbols
    public static String normalize(String input){

        //Remove honorific sign
        input=input.replaceAll("\u0610", "");//ARABIC SIGN SALLALLAHOU ALAYHE WA SALLAM
        input=input.replaceAll("\u0611", "");//ARABIC SIGN ALAYHE ASSALLAM
        input=input.replaceAll("\u0612", "");//ARABIC SIGN RAHMATULLAH ALAYHE
        input=input.replaceAll("\u0613", "");//ARABIC SIGN RADI ALLAHOU ANHU
        input=input.replaceAll("\u0614", "");//ARABIC SIGN TAKHALLUS

        //Remove koranic anotation
        input=input.replaceAll("\u0615", "");//ARABIC SMALL HIGH TAH
        input=input.replaceAll("\u0616", "");//ARABIC SMALL HIGH LIGATURE ALEF WITH LAM WITH YEH
        input=input.replaceAll("\u0617", "");//ARABIC SMALL HIGH ZAIN
        input=input.replaceAll("\u0618", "");//ARABIC SMALL FATHA
        input=input.replaceAll("\u0619", "");//ARABIC SMALL DAMMA
        input=input.replaceAll("\u061A", "");//ARABIC SMALL KASRA
        input=input.replaceAll("\u06D6", "");//ARABIC SMALL HIGH LIGATURE SAD WITH LAM WITH ALEF MAKSURA
        input=input.replaceAll("\u06D7", "");//ARABIC SMALL HIGH LIGATURE QAF WITH LAM WITH ALEF MAKSURA
        input=input.replaceAll("\u06D8", "");//ARABIC SMALL HIGH MEEM INITIAL FORM
        input=input.replaceAll("\u06D9", "");//ARABIC SMALL HIGH LAM ALEF
        input=input.replaceAll("\u06DA", "");//ARABIC SMALL HIGH JEEM
        input=input.replaceAll("\u06DB", "");//ARABIC SMALL HIGH THREE DOTS
        input=input.replaceAll("\u06DC", "");//ARABIC SMALL HIGH SEEN
        input=input.replaceAll("\u06DD", "");//ARABIC END OF AYAH
        input=input.replaceAll("\u06DE", "");//ARABIC START OF RUB EL HIZB
        input=input.replaceAll("\u06DF", "");//ARABIC SMALL HIGH ROUNDED ZERO
        input=input.replaceAll("\u06E0", "");//ARABIC SMALL HIGH UPRIGHT RECTANGULAR ZERO
        input=input.replaceAll("\u06E1", "");//ARABIC SMALL HIGH DOTLESS HEAD OF KHAH
        input=input.replaceAll("\u06E2", "");//ARABIC SMALL HIGH MEEM ISOLATED FORM
        input=input.replaceAll("\u06E3", "");//ARABIC SMALL LOW SEEN
        input=input.replaceAll("\u06E4", "");//ARABIC SMALL HIGH MADDA
        input=input.replaceAll("\u06E5", "");//ARABIC SMALL WAW
        input=input.replaceAll("\u06E6", "");//ARABIC SMALL YEH
        input=input.replaceAll("\u06E7", "");//ARABIC SMALL HIGH YEH
        input=input.replaceAll("\u06E8", "");//ARABIC SMALL HIGH NOON
        input=input.replaceAll("\u06E9", "");//ARABIC PLACE OF SAJDAH
        input=input.replaceAll("\u06EA", "");//ARABIC EMPTY CENTRE LOW STOP
        input=input.replaceAll("\u06EB", "");//ARABIC EMPTY CENTRE HIGH STOP
        input=input.replaceAll("\u06EC", "");//ARABIC ROUNDED HIGH STOP WITH FILLED CENTRE
        input=input.replaceAll("\u06ED", "");//ARABIC SMALL LOW MEEM

        //Remove tatweel
        input=input.replaceAll("\u0640", "");

        //Remove tashkeel
        input=input.replaceAll("\u064B", "");//ARABIC FATHATAN
        input=input.replaceAll("\u064C", "");//ARABIC DAMMATAN
        input=input.replaceAll("\u064D", "");//ARABIC KASRATAN
        input=input.replaceAll("\u064E", "");//ARABIC FATHA
        input=input.replaceAll("\u064F", "");//ARABIC DAMMA
        input=input.replaceAll("\u0650", "");//ARABIC KASRA
        input=input.replaceAll("\u0651", "");//ARABIC SHADDA
        input=input.replaceAll("\u0652", "");//ARABIC SUKUN
        input=input.replaceAll("\u0653", "");//ARABIC MADDAH ABOVE
        input=input.replaceAll("\u0654", "");//ARABIC HAMZA ABOVE
        input=input.replaceAll("\u0655", "");//ARABIC HAMZA BELOW
        input=input.replaceAll("\u0656", "");//ARABIC SUBSCRIPT ALEF
        input=input.replaceAll("\u0657", "");//ARABIC INVERTED DAMMA
        input=input.replaceAll("\u0658", "");//ARABIC MARK NOON GHUNNA
        input=input.replaceAll("\u0659", "");//ARABIC ZWARAKAY
        input=input.replaceAll("\u065A", "");//ARABIC VOWEL SIGN SMALL V ABOVE
        input=input.replaceAll("\u065B", "");//ARABIC VOWEL SIGN INVERTED SMALL V ABOVE
        input=input.replaceAll("\u065C", "");//ARABIC VOWEL SIGN DOT BELOW
        input=input.replaceAll("\u065D", "");//ARABIC REVERSED DAMMA
        input=input.replaceAll("\u065E", "");//ARABIC FATHA WITH TWO DOTS
        input=input.replaceAll("\u065F", "");//ARABIC WAVY HAMZA BELOW
        input=input.replaceAll("\u0670", "");//ARABIC LETTER SUPERSCRIPT ALEF

        return input;
    }

    //Store final arabic corpus to csv file
    public static void storeCorpusFile(ArrayList<String> arrayList) throws IOException {

        Path output = Paths.get("src/corpus.csv");
        try {
            Files.write(output, arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
