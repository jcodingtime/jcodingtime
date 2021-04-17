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
	private String className;

	// final static Logger logger = Logger.getLogger(TestMethodBuilder.class);

	public TestMethodBuilder(ArrayList<String> methodNames, ArrayList<String> typeMethods,
			ArrayList<String> parameters, ArrayList<String> outputs, ArrayList<String> inputs,
			ArrayList<ArrayList<String>> limits, String className) {
		this.methodNames = methodNames;
		this.typeMethods = typeMethods;
		this.outputs = outputs;
		this.inputs = inputs;
		this.limits = limits;
		this.parameters = parameters;
		this.className = className;
	}

	@Override
	public String generate() {
		if (inputs != null | limits != null) {

			stringBuffer = new StringBuffer();
			
			stringBuffer.append("package jct;\n\n");
			stringBuffer.append("import org.junit.Test;\n");
			stringBuffer.append("import static org.junit.Assert.assertEquals;\n\n");
			stringBuffer.append("public class "+ className +"Test {\n");

			if (inputs != null) {
				for (int i = 0; i < inputs.size(); i++) {
					stringBuffer.append("\t@Test");

					String output = outputs.get(i);

					String input = inputs.get(i);

					output = output.replace("\n", "").replace("\r", "");
					input = input.replace("\n", "").replace("\r", "");

					stringBuffer.append("\t\n\tpublic void testCase"
							+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1) + "()"
							+ " {" + "\n\t\t"+ typeMethods.get(i)+ " expected = " + output + ";\n\t\t"+ typeMethods.get(i)+ " obtained = " + methodNames.get(i) + input
							+ ";\n\t\tassertEquals(expected, obtained);" + "\n\t}\n\n");
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
						if (j == 0) {
							stringBuffer.append("\n\t@Test");
							limitNumber -= 1;

							stringBuffer
									.append("\n\tpublic void testCase1" + methodNames.get(i).toUpperCase().substring(0, 1)
											+ methodNames.get(i).substring(1) + "()" + " {" + "\n\t\t"
											+ parameters.get(i) + " = " + limitNumber + ";" + "\n\t\tboolean "
											+ "valid = " + "false;" 
											+ "\n\t\tif\s("+ onlyVariable +"\s>=\s" + innerBoundary
											+ "\s&&\s" + onlyVariable + "\s<=\s" + upperBoundary + "){" + "\n\t\t\tvalid = true;" 
											+ "\n\t\t} else {" 
											+ "\n\t\t\tvalid = false;" 
											+ "\n\t\t}" 
											+ "\n\t\tassertNotEquals(true, valid);" + "\n\t}\n\n");

							stringBuffer.append("\t@Test");
							limitNumber += 1;
							stringBuffer.append("\n\tpublic void testCase2"
									+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1)
									+ "()" + " {" + "\n\t\t" + parameters.get(i) + " = " + limitNumber + ";"
									+ "\n\t\tboolean " + "valid = " + "false;"
									+ "\n\t\tif\s("+ onlyVariable +"\s>=\s" + innerBoundary
									+ "\s&&\s" + onlyVariable + "\s<=\s" + upperBoundary + "){" + "\n\t\t\tvalid = true;" 
									+ "\n\t} else {" 
									+ "\n\t\t\tvalid = false;" 
									+ "\n\t\t}"
									
									+ "\n\t\tassertEquals(true, valid);" + "\n\t}\n\n");

							stringBuffer.append("\t@Test");
							limitNumber += 1;
							stringBuffer.append("\n\tpublic void testCase3"
									+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1)
									+ "()" + " {" + "\n\t\t" + parameters.get(i) + " = " + limitNumber + ";"
									+ "\n\t\tboolean " + "valid = " + "false;" 
									+ "\n\t\tif\s("+ onlyVariable +"\s>=\s" + innerBoundary
									+ "\s&&\s" + onlyVariable + "\s<=\s" + upperBoundary + "){" + "\n\t\t\tvalid = true;" 
									+ "\n\t\t} else {" 
									+ "\n\t\t\tvalid = false;" 
									+ "\n\t\t}"
									+ "\n\t\tassertEquals(true, valid);" + "\n\t}\n\n");

						}

						if (j == 1) {
							stringBuffer.append("\t@Test");
							limitNumber -= 1;
							stringBuffer.append("\n\tpublic void testCase4"
									+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1)
									+ "()" + " {" + "\n\t\t" + parameters.get(i) + " = " + limitNumber + ";"
									+ "\n\t\tboolean " + "valid = " + "false;"
									+ "\n\t\tif\s("+ onlyVariable +"\s>=\s" + innerBoundary
									+ "\s&&\s" + onlyVariable + "\s<=\s" + upperBoundary + "){" + "\n\t\t\tvalid = true;" 
									+ "\n\t\t} else {" 
									+ "\n\t\t\tvalid = false;" 
									+ "\n\t\t}"
									+ "\n\t\tassertEquals(true, valid);" + "\n\t}\n\n");

							stringBuffer.append("\t@Test");
							limitNumber += 1;
							stringBuffer.append("\n\tpublic void testCase5"
									+ methodNames.get(i).toUpperCase().substring(0, 1) + methodNames.get(i).substring(1)
									+ "()" + " {" + "\n\t\t" + parameters.get(i) + " = " + limitNumber + ";"
									+ "\n\t\tboolean " + "valid = " + "false;"
									+ "\n\t\tif\s("+ onlyVariable +"\s>=\s" + innerBoundary
									+ "\s&&\s" + onlyVariable + "\s<=\s" + upperBoundary + "){" + "\n\t\t\tvalid = true;" 
									+ "\n\t\t} else {" 
									+ "\n\t\t\tvalid = false;" 
									+ "\n\t\t}"
									+ "\n\t\tassertEquals(true, valid);" + "\n\t}\n\n");

							stringBuffer.append("\t@Test");
							limitNumber += 1;
							stringBuffer
									.append("\n\tpublic void testCase6" + methodNames.get(i).toUpperCase().substring(0, 1)
											+ methodNames.get(i).substring(1) + "()" + " {" + "\n\t\t"
											+ parameters.get(i) + " = " + limitNumber + ";" 
											+ "\n\t\tboolean " + "valid = " + "false;"
											+ "\n\t\tif\s("+ onlyVariable +"\s>=\s" + innerBoundary
											+ "\s&&\s" + onlyVariable + "\s<=\s" + upperBoundary + "){" + "\n\t\t\tvalid = true;" 
											+ "\n\t\t} else {" 
											+ "\n\t\t\tvalid = false;" 
											+ "\n\t\t}"
											+ "\n\t\tassertNotEquals(true, valid);" + "\n\t}\n\n");
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
			// Create directory o jct
			File directory = new File("src/test/java/jct/");
			if(!directory.exists()) {
				directory.mkdir();
			}
			
			// Specify the file name
			File file = new File("src/test/java/jct/"+className+"Test.java");
			
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
			
			System.out.println("The test file was generated with success!\n" + "See the path: src/test/jct/");
		} catch (IOException e) {
			System.out.println("Could not create this file!\n" + e);
		}
	}

	public StringBuffer getStringBuffer() {
		return stringBuffer;
	}

	public void setBufferedWriter(BufferedWriter bufferedWriter) {
		this.bufferedWriter = bufferedWriter;
	}

}