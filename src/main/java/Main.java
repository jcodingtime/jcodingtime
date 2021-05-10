import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

import com.github.jcodingtime.verifier.collector.InputData;

/**
 * JCodingTime is a open-source project for generation unit tests for Java applications using specifics annotations.
 * This project was developed for graduation of Software Engineering in University of Brasilia.
 */
public class Main {
	public static void main(String[] args) throws IOException {

		File file = null;

		if (args.length > 0) {
			file = FileUtils.getFile(args[0]);
		} else {
			String path = "src/main/java/com/github/jcodingtime/example/ExampleService.java";
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
