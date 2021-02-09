package jcodingtime.java;

import jcodingtime.java.verifier.collector.InputData;
import jcodingtime.java.verifier.parser.ParseException;
import jcodingtime.java.verifier.parser.Parser;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.nio.charset.Charset;

//import org.apache.log4j.Logger;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    //final static Logger logger = Logger.getLogger(Main.class);
    
    public static void main(String[] args) throws IOException {

    	File file = FileUtils.getFile("/tcc-jcodingtime/src/main/java/jcodingtime/java/example/input/Example.java");

        File tmpDir = FileUtils.getTempDirectory();

        System.out.println(tmpDir.getName());

        //copy file to temp directory
        FileUtils.copyFileToDirectory(file, tmpDir);

        //create a new file
        File newTempFile = FileUtils.getFile(tmpDir, file.getName());

        //get the content
        String source = FileUtils.readFileToString(newTempFile, Charset.defaultCharset());

        //print the content
        System.out.println(source);
        
 
      	
      InputData inputData = new InputData();

      inputData.setSource(source);
      inputData.spliteData();
    }
}
