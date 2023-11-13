import java.util.regex.Pattern;

public class YearValidator implements Validator{

    private static final Pattern p = Pattern.compile("200[2-9]|201[0-9]|202[0-1]");

    public Pattern getPattern() {
        return p;
    }
}
