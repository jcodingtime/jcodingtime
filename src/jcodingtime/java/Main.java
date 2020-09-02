import verifier.collector.InputData;
import verifier.parser.ParseException;
import verifier.parser.Parser;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.commons.io.FileUtils;

import example.input.Example;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    final static Logger logger = Logger.getLogger(Main.class);
    
    public static void main(String[] args) {
    	String path = "src/jcodingtime/java/example/input/Example.java";
        
        try {
//          String source = "@JCodingTime\n" + 
//          		"	@Input (firstParam=5, secondParam=5)\n" + 
//          		"	@Output(result=25)\n" + 
//          		"	public static int multiplyTwoNumbers(int firstParameter, int secondParameter) {\n" + 
//          		"		return firstParameter * secondParameter;\n" + 
//          		"	}	";
      	
      	
      	File file = FileUtils.getFile(path);
      	
      	String source = FileUtils.readFileToString(file);
      	
      	
      InputData inputData = new InputData();

      inputData.setSource(source);
      inputData.spliteData();

//      InputStream targetStream = new ByteArrayInputStream(source.getBytes());
//      Parser parser = new Parser(targetStream);
//      parser.Program();
      	
      }
      catch(IOException e) {
      	logger.error("Could not open this file", e);
      }
    }
}
