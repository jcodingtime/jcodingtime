import verifier.enter.InputData;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        try
        {

            InputData inputData = new InputData();
            inputData.setScenario("multiply two numbers");
            inputData.setInput("5, 5");
            inputData.setOutput("25");
            inputData.setMethod("int multiplyNumbers");
            inputData.setParameters("int firstValue, int secondValue");

            String initialString = inputData.buildData();

            System.out.println(initialString);

            InputStream targetStream = new ByteArrayInputStream(initialString.getBytes());
            Parser parser = new Parser(targetStream);
            parser.Program();

        }
        catch(ParseException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
