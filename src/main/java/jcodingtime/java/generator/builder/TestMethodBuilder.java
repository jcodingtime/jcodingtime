package jcodingtime.java.generator.builder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import jcodingtime.java.exceptions.InputEmptyException;

//import org.apache.log4j.Logger;

public class TestMethodBuilder extends TestBuilder {

	private ArrayList<String> methodNames;
	private ArrayList<String> typeMethods;
	private ArrayList<String> outputs;
	private ArrayList<String> inputs;
	private ArrayList<ArrayList<String>> limits;
	private ArrayList<String> parameters;
	private StringBuffer stringBuffer;
	private BufferedWriter bufferedWriter;

	// final static Logger logger = Logger.getLogger(TestMethodBuilder.class);

	public TestMethodBuilder(ArrayList<String> methodNames, ArrayList<String> typeMethods,
			ArrayList<String> parameters, ArrayList<String> outputs, ArrayList<String> inputs,
			ArrayList<ArrayList<String>> limits) {
		this.methodNames = methodNames;
		this.typeMethods = typeMethods;
		this.outputs = outputs;
		this.inputs = inputs;
		this.limits = limits;
		this.parameters = parameters;
	}

	@Override
	public String generate() {
		if (inputs != null | limits != null) {

			stringBuffer = new StringBuffer();
			stringBuffer.append("import org.junit.Test;\n");
			stringBuffer.append("import static org.junit.Assert.assertEquals;\n\n");
			stringBuffer.append("public class TestExample {\n");

			if (inputs != null) {
				for (int i = 0; i < inputs.size(); i++) {
					stringBuffer.append("\r@Test");

					String output = outputs.get(i);

					String input = inputs.get(i);

					output = output.replace("\n", "").replace("\r", "");
					input = input.replace("\n", "").replace("\r", "");

					stringBuffer.append("\rpublic " + typeMethods.get(i) + " testCase"
							+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1) + "()"
							+ " {" + "\n\texpected = " + output + "\n\tobtained = " + methodNames.get(i) + input
							+ "\n\tassertEquals(expected, obtained);" + "\n}");
				}
			} else {
				// nothing TODO
			}

			if (limits != null) {
				for (int i = 0; i < limits.size(); i++) {
					for (int j = 0; j < limits.get(i).size(); j++) {
						String limit = limits.get(i).get(j);
						int limitNumber = Integer.parseInt(limit);
						int innerBoundary = Integer.parseInt(limits.get(i).get(0));
						int upperBoundary = Integer.parseInt(limits.get(i).get(1));
						
						String[] parts = parameters.get(i).split("\\s+");
						String onlyVariable = parts[1];
						
						System.out.println("ANTES");
						System.out.println(innerBoundary);
						System.out.println(upperBoundary);
						System.out.println("DEPOIS");
						if (j == 0) {
							limitNumber -= 1;

							stringBuffer
									.append("\npublic void testCase1" + methodNames.get(i).toUpperCase().substring(0, 1)
											+ methodNames.get(i).substring(1) + "()" + " {" + "\n\t"
											+ parameters.get(i) + " = " + limitNumber + ";" + "\n\tboolean "
											+ "valid = " + "false;" 
											+ "\n\tif\s("+ onlyVariable +"\s>=\s" + innerBoundary
											+ "\s&&\s" + onlyVariable + "\s<=\s" + upperBoundary + "){" + "\n\t\tvalid = true;" 
											+ "\n\t} else {" 
											+ "\n\t\tvalid = false;" 
											+ "\n\t}" 
											+ "\n\tassertNotEquals(true, valid);" + "\n}");

							limitNumber += 1;
							stringBuffer.append("\npublic void testCase2"
									+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1)
									+ "()" + " {" + "\n\t" + parameters.get(i) + " = " + limitNumber + ";"
									+ "\n\tboolean " + "valid = " + "false;"
									+ "\n\tif\s("+ onlyVariable +"\s>=\s" + innerBoundary
									+ "\s&&\s" + onlyVariable + "\s<=\s" + upperBoundary + "){" + "\n\t\tvalid = true;" 
									+ "\n\t} else {" 
									+ "\n\t\tvalid = false;" 
									+ "\n\t}"
									
									+ "\n\tassertEquals(true, valid);" + "\n}");

							limitNumber += 1;
							stringBuffer.append("\npublic void testCase3"
									+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1)
									+ "()" + " {" + "\n\t" + parameters.get(i) + " = " + limitNumber + ";"
									+ "\n\tboolean " + "valid = " + "false;" 
									+ "\n\tif\s("+ onlyVariable +"\s>=\s" + innerBoundary
									+ "\s&&\s" + onlyVariable + "\s<=\s" + upperBoundary + "){" + "\n\t\tvalid = true;" 
									+ "\n\t} else {" 
									+ "\n\t\tvalid = false;" 
									+ "\n\t}"
									+ "\n\tassertEquals(true, valid);" + "\n}");

						}

						if (j == 1) {
							limitNumber -= 1;
							stringBuffer.append("\npublic void testCase4"
									+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1)
									+ "()" + " {" + "\n\t" + parameters.get(i) + " = " + limitNumber + ";"
									+ "\n\tboolean " + "valid = " + "false;"
									+ "\n\tif\s("+ onlyVariable +"\s>=\s" + innerBoundary
									+ "\s&&\s" + onlyVariable + "\s<=\s" + upperBoundary + "){" + "\n\t\tvalid = true;" 
									+ "\n\t} else {" 
									+ "\n\t\tvalid = false;" 
									+ "\n\t}"
									+ "\n\tassertEquals(true, valid);" + "\n}");

							limitNumber += 1;
							stringBuffer.append("\npublic void testCase5"
									+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1)
									+ "()" + " {" + "\n\t" + parameters.get(i) + " = " + limitNumber + ";"
									+ "\n\tboolean " + "valid = " + "false;"
									+ "\n\tif\s("+ onlyVariable +"\s>=\s" + innerBoundary
									+ "\s&&\s" + onlyVariable + "\s<=\s" + upperBoundary + "){" + "\n\t\tvalid = true;" 
									+ "\n\t} else {" 
									+ "\n\t\tvalid = false;" 
									+ "\n\t}"
									+ "\n\tassertEquals(true, valid);" + "\n}");

							limitNumber += 1;
							stringBuffer
									.append("\npublic void testCase6" + methodNames.get(i).toUpperCase().substring(0, 1)
											+ methodNames.get(i).substring(1) + "()" + " {" + "\n\t"
											+ parameters.get(i) + " = " + limitNumber + ";" + "\n\tboolean "
											+ "valid = " + "false;"
											+ "\n\tif\s("+ onlyVariable +"\s>=\s" + innerBoundary
											+ "\s&&\s" + onlyVariable + "\s<=\s" + upperBoundary + "){" + "\n\t\tvalid = true;" 
											+ "\n\t} else {" 
											+ "\n\t\tvalid = false;" 
											+ "\n\t}"
											
											+ "\n\tassertNotEquals(true, valid);" + "\n}");
						}
					}
				}
			} else {
				// nothing TODO
			}

			stringBuffer.append("\n}");
			return stringBuffer.toString();
		} else {
			return "";
		}
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