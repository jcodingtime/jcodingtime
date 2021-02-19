package jcodingtime.java.verifier.collector;

import jcodingtime.java.generator.builder.TestBuilder;
import jcodingtime.java.generator.builder.TestMethodBuilder;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    // final static Logger logger = Logger.getLogger(InputData.class);

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
                "@input "+ getInput() + "\n" + ";" +
                "@output "+ getOutput() + "\n" + ";" +
                "public static " + getMethod() + "("+ getParameters() +")";
        return inputData;
    }

    /**
     * Splice the data to distribuite values for input, output and method name
     */
    public void spliteData(){

    	//logger.info("The capturing of input, output and method name was started.");

        String describeInput = "";
        String describeOutput = "";
        String describeMethodName = "";
        String typeMethod = "";

        String input = getSource();
        
       
        
        Pattern pJCodingTime = Pattern.compile("@JCodingTime(.*?\n.*?@Input)");
        Matcher mJCodingTime = pJCodingTime.matcher(input);
        
        String paramenters = "";

        
        if(mJCodingTime.find()) {
        	

        	//get string range between @Input and @Output
            Pattern pInput = Pattern.compile("@Input(.*?\n.*?.*?@Output)");
            Matcher mInput = pInput.matcher(input);

            if (mInput.find()) {
            	describeInput = mInput.group().subSequence(6, mInput.group().length() - 7).toString().trim();
            	
            	//get numbers of the range
	            Pattern p = Pattern.compile("\\d+");
	            Matcher m = p.matcher(describeInput); //ex: 5 5
	            
	            ArrayList<String> inputs = new ArrayList<String>();
	            int i = 0;
	            
	            while(m.find()) {
	            	inputs.add(m.group());
	            }
	            
	            //treatment for function format
	            ArrayList<String> tokens = new ArrayList<String>();
	            
	            tokens.add("(");
	            
	            for (int j = 0; j < inputs.size(); j++) {
	            	tokens.add(inputs.get(j));
				}
	            tokens.add(")");
	            
	            StringBuffer sb = new StringBuffer();
	            
	            int g = 0;
	            for (String s : tokens) {
	               sb.append(s);
	               if(g != 0 && g != tokens.size() -1 && g != tokens.size() -2) {
	            	   sb.append(",");  
	               }
	               g++;
	            }
	            
	            setInput(sb.toString()); //ex: (5,5)
            }
            
            //get string range between @Output and public
            Pattern pOutput = Pattern.compile("@Output(.*?\n.*?public)");
            Matcher mOutput = pOutput.matcher(input);

            if (mOutput.find())
                describeOutput = mOutput.group().subSequence(7, mOutput.group().length() - 7).toString();
          
            //get numbers of the range
            Pattern p = Pattern.compile("\\d+");
            Matcher m = p.matcher(describeOutput); //ex: 5 5
            
            ArrayList<String> output = new ArrayList<String>();
            
            while(m.find()) {
            	output.add(m.group());
            }
            
            StringBuffer sb = new StringBuffer();
            
            int g = 0;
            for (String s : output) {
               sb.append(s);
               if(g != 0 && g != output.size() -1 && g != output.size() -2) {
            	   sb.append(",");  
               }
               g++;
            }
            
            setOutput(sb.toString());

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

                describeMethodName = mMethodName.group().subSequence(0, mMethodName.group().length() - 1).toString();

                
                if (describeMethodName.contains("int")) {
                    describeMethodName = mMethodName.group().subSequence(4, mMethodName.group().length() - 1).toString();
                    typeMethod = "int";
                } else if (describeMethodName.contains("char")) {
                    describeMethodName = mMethodName.group().subSequence(5, mMethodName.group().length() - 1).toString();
                    typeMethod = "char";
                } else if (describeMethodName.contains("void")) {
                    describeMethodName = mMethodName.group().subSequence(5, mMethodName.group().length() - 1).toString();
                    typeMethod = "void";
                } else if (describeMethodName.contains("double")) {
                    describeMethodName = mMethodName.group().subSequence(7, mMethodName.group().length() - 1).toString();
                    typeMethod = "double";
                } else {
                    describeMethodName = mMethodName.group().subSequence(8, mMethodName.group().length() - 1).toString();
                    typeMethod = "boolean";
                }
                
                setMethod(describeMethodName);
            }
        } else {
        	//logger.error("Error, not found expression @JcodingTime");
        }
        TestBuilder testMethodBuilder = new TestMethodBuilder(describeMethodName, typeMethod, paramenters, getOutput(), getInput());
        testMethodBuilder.generate();
        System.out.println(testMethodBuilder.getStringBuffer().toString());
        testMethodBuilder.generateFile();
    }
}
