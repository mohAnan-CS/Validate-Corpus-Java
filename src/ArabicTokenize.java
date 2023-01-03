import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArabicTokenize {

    public static final Pattern TOKEN_PATTERN = Pattern.compile("\\p{IsArabic}+");

    public static List<String> tokenize(String text) {

        List<String> tokens = new ArrayList<>();
        Matcher matcher = TOKEN_PATTERN.matcher(text);

        while (matcher.find()) {

            tokens.add(matcher.group());

        }

        return tokens;

    }

}
