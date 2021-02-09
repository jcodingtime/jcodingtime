package jcodingtime.java.generator.builder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//import org.apache.log4j.Logger;

public class TestMethodBuilder extends TestBuilder {

    private String methodName;
    private String typeMethod;
    private String paramenters;
    private String output;
    private String input;
    private StringBuffer stringBuffer;
    private BufferedWriter bufferedWriter;

    //final static Logger logger = Logger.getLogger(TestMethodBuilder.class);


    public TestMethodBuilder(String methodName, String typeMethod, String paramenters, String output, String input){
        this.methodName = methodName;
        this.typeMethod = typeMethod;
        this.paramenters = paramenters;
        this.output = output;
        this.input = input;
    }

    @Override
    public String generate() {

    	//   logger.info("The generation of method test was started.");

        output = output.replace("\n", "").replace("\r", "");
        input = input.replace("\n", "").replace("\r", "");

        stringBuffer = new StringBuffer();
        
        stringBuffer.append("import org.junit.Test;\n");
        
        stringBuffer.append("import static org.junit.Assert.assertEquals;\n\n");
        
        stringBuffer.append("public class TestExample {\n");
        
        stringBuffer.append("\r@Test");
        stringBuffer.append("\n\rpublic "+ typeMethod + " test" +
                methodName.toUpperCase().substring(0,1) +
                methodName.substring(1) + " " + paramenters + " {" +
                "\n\texpected = " + output +
                "\n\tobtained = "+ methodName + input +
                "\n\tassertEquals(expected, obtained);" +
                "\n}");
        
        stringBuffer.append("}");
        
        return stringBuffer.toString();
    }
    
    public void generateFile() {

    	
    	try {
    		//Specify the file name and path here
    		 File file = new File("src/jcodingtime/java/example/output/TestExample.java");

    		 /* This logic will make sure that the file 
    		  * gets created if it is not present at the
    		  * specified location*/
    		  if (!file.exists()) {
    		     file.createNewFile();
    		  }
    		  

    		  FileWriter fw = new FileWriter(file);
    		  bufferedWriter = new BufferedWriter(fw);
    		  bufferedWriter.write(stringBuffer.toString());
    		  bufferedWriter.close();
    		  System.out.println("File written Successfully");
        }
        catch(IOException e) {
        	//logger.error("Could not create this file", e);
    	}
    }
    
    public StringBuffer getStringBuffer() {
        return stringBuffer;
    }

	public void setBufferedWriter(BufferedWriter bufferedWriter) {
		this.bufferedWriter = bufferedWriter;
	}

}