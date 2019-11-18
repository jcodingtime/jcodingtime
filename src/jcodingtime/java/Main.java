import verifier.enter.InputData;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        try
        {
            String source =
                    "@scenario multiply two numbers" + "\n" +
                            "@input 5, 5" + "\n" +
                            "@output 25" + "\n" +
                            "public static boolean multiplyTwoNumbers (int firstParameter, int secondParameter)";
            InputData inputData = new InputData();

            inputData.setSource(source);

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
