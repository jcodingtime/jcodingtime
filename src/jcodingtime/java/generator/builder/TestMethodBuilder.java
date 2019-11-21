package generator.builder;

import org.apache.log4j.Logger;

public class TestMethodBuilder extends TestBuilder {

    private String methodName;
    private String typeMethod;
    private StringBuffer stringBuffer;
    private String paramenters;
    private String output;
    private String input;

    final static Logger logger = Logger.getLogger(TestMethodBuilder.class);


    public TestMethodBuilder(String methodName, String typeMethod, String paramenters, String output, String input){
        super(methodName);
        this.methodName = methodName;
        this.typeMethod = typeMethod;
        this.paramenters = paramenters;
        this.output = output;
        this.input = input;
    }

    public String generate() {

        logger.info("The generation of method test was started.");

        output = output.replace("\n", "").replace("\r", "");
        input = input.replace("\n", "").replace("\r", "");

        stringBuffer = new StringBuffer();

        stringBuffer.append("@Test");
        stringBuffer.append("\npublic "+ typeMethod + " test" +
                methodName.toUpperCase().substring(0,1) +
                methodName.substring(1) + " " + paramenters + " {" +
                "\n\tresult = " + output + ";"+
                "\n\texpected = "+ methodName + input + ";"+
                "\n\tassertEquals(result, expected);" +
                "\n}");
        return stringBuffer.toString();
    }

}