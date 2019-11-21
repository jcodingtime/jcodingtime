package verifier.enter;

import generator.builder.TestBuilder;
import generator.builder.TestMethodBuilder;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * InputData
 * This class must receive parser input data
 */
public class InputData {

    private String scenario;
    private String input;
    private String output;
    private String method;
    private String parameters;
    private String source;

    final static Logger logger = Logger.getLogger(InputData.class);

    //constructor
    public InputData() {
    }

    //constructor
    public InputData(String scenario, String input, String output, String method, String parameters, String source) {
        this.scenario = scenario;
        this.input = input;
        this.output = output;
        this.method = method;
        this.parameters = parameters;
        this.source = source;
    }

    public String getScenario() {
        return scenario;
    }
    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getInput() {
        return input;
    }
    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }
    public void setOutput(String output) {
        this.output = output;
    }

    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }

    public String getParameters() {
        return parameters;
    }
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Unifies the data
     * @return inputData
     */
    private String buildData(){
        String inputData = "@scenario " + getScenario() + "\n" +
                "@input "+ getInput() + "\n" +
                "@output "+ getOutput() + "\n" +
                "public static " + getMethod() + "("+ getParameters() +")";
        return inputData;
    }

    /**
     * Splice the data to distribuite values for input, output and method name
     */
    public void spliteData(){

        logger.info("The capturing of input, output and method name was stated.");

        String describeInput = "";
        String describeOutput = "";
        String describeMethodName = "";
        String typeMethod = "";

        String input = getSource();

        Pattern pJCodingTime = Pattern.compile("@jcodingtime(.*?\n@input)");
        Matcher mJCodingTime = pJCodingTime.matcher(input);

        String paramenters = "";

        if(mJCodingTime.find()) {

            Pattern pInput = Pattern.compile("@input(.*?\n@output)");
            Matcher mInput = pInput.matcher(input);

            if (mInput.find())
                describeInput = mInput.group().subSequence(7, mInput.group().length() - 7).toString();
            setInput(describeInput);

            Pattern pOutput = Pattern.compile("@output(.*?\npublic)");
            Matcher mOutput = pOutput.matcher(input);

            if (mOutput.find())
                describeOutput = mOutput.group().subSequence(8, mOutput.group().length() - 7).toString();
            setOutput(describeOutput);

            Pattern pMethodName = Pattern.compile("(int|float|char|void|double|boolean)(.*?\\()");
            Matcher mMethodName = pMethodName.matcher(input);

            if (mMethodName.find()) {
                Pattern pParams = Pattern.compile("(int|float|char|void|double|boolean)(.*?\\))");
                Matcher mParams= pParams.matcher(input);
                if(mParams.find()){
                    String fullMethod = mParams.group().subSequence(0, mParams.group().length()).toString();
                    String spliteFullMethod[] = fullMethod.split("\\(");
                    paramenters = "(" + spliteFullMethod[1];
                }

                describeMethodName = mMethodName.group().subSequence(0, mMethodName.group().length() - 6).toString();

                if (describeMethodName.contains("int")) {
                    describeMethodName = mMethodName.group().subSequence(4, mMethodName.group().length() - 2).toString();
                    typeMethod = "int";
                } else if (describeMethodName.contains("char")) {
                    describeMethodName = mMethodName.group().subSequence(5, mMethodName.group().length() - 2).toString();
                    typeMethod = "char";
                } else if (describeMethodName.contains("void")) {
                    describeMethodName = mMethodName.group().subSequence(5, mMethodName.group().length() - 2).toString();
                    typeMethod = "void";
                } else if (describeMethodName.contains("double")) {
                    describeMethodName = mMethodName.group().subSequence(7, mMethodName.group().length() - 2).toString();
                    typeMethod = "double";
                } else {
                    describeMethodName = mMethodName.group().subSequence(8, mMethodName.group().length() - 2).toString();
                    typeMethod = "boolean";
                }
                setMethod(describeMethodName);
            }
        } else {
            logger.error("Error, not found expression @jcodingtime");
        }
        TestBuilder testMethodBuilder = new TestMethodBuilder(describeMethodName, typeMethod, paramenters, getOutput(), getInput());
        System.out.println(testMethodBuilder.generate());
    }
}
