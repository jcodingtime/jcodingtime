package jcodingtime.java.generator.builder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//import org.apache.log4j.Logger;

public class TestMethodBuilder extends TestBuilder {

	private ArrayList<String> methodNames;
	private ArrayList<String> typeMethods;
	private ArrayList<String> paramenters;
	private ArrayList<String> outputs;
	private ArrayList<String> inputs;
	private ArrayList<ArrayList<String>> limits;
	private StringBuffer stringBuffer;
	private BufferedWriter bufferedWriter;

	// final static Logger logger = Logger.getLogger(TestMethodBuilder.class);

	public TestMethodBuilder(ArrayList<String> methodNames, ArrayList<String> typeMethods,
			ArrayList<String> paramenters, ArrayList<String> outputs, ArrayList<String> inputs,
			ArrayList<ArrayList<String>> limits) {
		this.methodNames = methodNames;
		this.typeMethods = typeMethods;
		this.paramenters = paramenters;
		this.outputs = outputs;
		this.inputs = inputs;
		this.limits = limits;
	}

	@Override
	public String generate() {

		// logger.info("The generation of method test was started.");

		stringBuffer = new StringBuffer();

		stringBuffer.append("import org.junit.Test;\n");

		stringBuffer.append("import static org.junit.Assert.assertEquals;\n\n");

		stringBuffer.append("public class TestExample {\n");

		for (int i = 0; i < inputs.size(); i++) {
			stringBuffer.append("\r@Test");
			
			String output = outputs.get(i);
			
			String input = inputs.get(i);

			output = output.replace("\n", "").replace("\r", "");
			input = input.replace("\n", "").replace("\r", "");

			stringBuffer.append("\rpublic " + typeMethods.get(i) + " testCase"
					+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1)
					+ "()" + " {" + "\n\texpected = " + output + "\n\tobtained = " + methodNames.get(i) + input
					+ "\n\tassertEquals(expected, obtained);" + "\n}");
		}
		
		for (int i = 0; i < limits.size(); i++) {

			for (int j = 0; j < limits.get(i).size(); j++) {
				String limit = limits.get(i).get(j);
				int limitNumber = Integer.parseInt(limit);


				if (j == 0) {
					limitNumber -= 1;

					stringBuffer.append("\npublic void testCase1"
							+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1) + "()"
							+ " {" + "\n\t"+ typeMethods.get(i) + " = " + limitNumber + ";" + "\n\tboolean " + "valid = " + "false;"
							+ "\n\tassertNotEquals(true, valid);" + "\n}");


					limitNumber += 1;
					stringBuffer.append("\npublic void testCase2"
							+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1) + "()"
							+ " {" + "\n\t"+ typeMethods.get(i) + " = " + limitNumber + ";" + "\n\tboolean " + "valid = " + "true;"
							+ "\n\tassertEquals(true, valid);" + "\n}");
					
					limitNumber += 1;
					stringBuffer.append("\npublic void testCase3"
							+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1) + "()"
							+ " {" + "\n\t"+ typeMethods.get(i) + " = " + limitNumber + ";" + "\n\tboolean " + "valid = " + "true;"
							+ "\n\tassertEquals(true, valid);" + "\n}");

				}

				if (j == 1) {
					limitNumber -= 1;
					stringBuffer.append("\npublic void testCase4"
							+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1) + "()"
							+ " {" + "\n\t"+ typeMethods.get(i) + " = " + limitNumber + ";" + "\n\tboolean " + "valid = " + "true;"
							+ "\n\tassertEquals(true, valid);" + "\n}");

					limitNumber += 1;
					stringBuffer.append("\npublic void testCase5"
							+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1) + "()"
							+ " {" + "\n\t"+ typeMethods.get(i) + " = " + limitNumber + ";" + "\n\tboolean " + "valid = " + "true;"
							+ "\n\tassertEquals(true, valid);" + "\n}");

					limitNumber += 1;
					stringBuffer.append("\npublic void testCase6"
							+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1) + "()"
							+ " {" + "\n\t"+ typeMethods.get(i) + " = " + limitNumber + ";" + "\n\tboolean " + "valid = " + "false;"
							+ "\n\tassertNotEquals(true, valid);" + "\n}");
				}
			}
		}

		stringBuffer.append("\n}");
		setStringBuffer(stringBuffer);
		return stringBuffer.toString();
	}

	public void generateFile() {

		try {
			// Specify the file name and path here
			File file = new File("src/jcodingtime/java/example/output/TestExample.java");

			/*
			 * This logic will make sure that the file gets created if it is not present at
			 * the specified location
			 */
			if (!file.exists()) {
				file.createNewFile();
			}
		

			FileWriter fw = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fw);
			bufferedWriter.write(stringBuffer.toString());
			bufferedWriter.close();
			System.out.println("File written Successfully");
		} catch (IOException e) {
			// logger.error("Could not create this file", e);
		}
	}

	public StringBuffer getStringBuffer() {
		return stringBuffer;
	}

	public void setBufferedWriter(BufferedWriter bufferedWriter) {
		this.bufferedWriter = bufferedWriter;
	}

}