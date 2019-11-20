import verifier.enter.InputData;
import verifier.parser.ParseException;
import verifier.parser.Parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        try
        {
            String source =
                            "@jcodingtime" + "\n" +
                            "@input (5, 5)" + "\n" +
                            "@output 25" + "\n" +
                            "public static boolean multiplyTwoNumbers (int firstParameter, int secondParameter)";
            InputData inputData = new InputData();

            inputData.setSource(source);
            inputData.spliteData();

            InputStream targetStream = new ByteArrayInputStream(source.getBytes());
            Parser parser = new Parser(targetStream);
            parser.Program();
        }
        catch(ParseException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
