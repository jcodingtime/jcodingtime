import verifier.enter.InputData;
import verifier.parser.ParseException;
import verifier.parser.Parser;

import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        try
        {
            String source = "@jcodingtime" + "\n" +
                            "@input (5, 5)" + "\n" +
                            "@output 25" + "\n" +
                            "public static int multiplyTwoNumbers (int firstParameter, int secondParameter)";
            InputData inputData = new InputData();

            inputData.setSource(source);
            inputData.spliteData();

            InputStream targetStream = new ByteArrayInputStream(source.getBytes());
            Parser parser = new Parser(targetStream);
            parser.Program();
        }
        catch(ParseException e)
        {
            logger.error("Could not parser this file", e);
        }
    }
}
