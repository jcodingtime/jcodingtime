package jcodingtime.java;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import jcodingtime.java.annotations.Domain;
import jcodingtime.java.annotations.JCodingTime;
import jcodingtime.java.verifier.collector.InputData;

public class Main {

	// final static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws IOException {

		File file = null;
		
		if (args.length > 0) {
			file = FileUtils.getFile(args[0]);
		} else {
//			String path = "/home/adailson/Documentos/myApp/src/main/java/com/mycompany/myapp/service/UserService.java";
			String path = "/home/adailson/Documentos/tcc/tcc-jcodingtime/src/main/java/jcodingtime/java/example/input/Address.java";
//			String path = "/home/adailson/Documentos/tcc/tcc-jcodingtime/src/main/java/jcodingtime/java/example/input/ExampleService.java";
			file = FileUtils.getFile(path);
		}
		
		
		File tmpDir = FileUtils.getTempDirectory();

		// copy file to temp directory
		FileUtils.copyFileToDirectory(file, tmpDir);

		// create a new file
		File newTempFile = FileUtils.getFile(tmpDir, file.getName());

		// get the content
		String source = FileUtils.readFileToString(newTempFile, Charset.defaultCharset());

		InputData inputData = new InputData();

		inputData.setSource(source);
		inputData.spliteData();
	}
}
