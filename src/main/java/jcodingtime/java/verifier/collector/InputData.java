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
 * InputData This class must receive parser input data
 */
public class InputData {

	private ArrayList<String> inputs;
	private ArrayList<String> outputs;
	private ArrayList<String> methods;
	private ArrayList<String> parameters;
	private ArrayList<ArrayList<String>> limits;
	private String source;

	// final static Logger logger = Logger.getLogger(InputData.class);

	// constructor
	public InputData() {
	}

	// constructor
	public InputData(ArrayList<String> inputs, ArrayList<String> outputs, ArrayList<String> methods,
			ArrayList<String> parameters, ArrayList<ArrayList<String>> limits, String source) {
		this.inputs = inputs;
		this.outputs = outputs;
		this.methods = methods;
		this.parameters = parameters;
		this.limits = limits;
		this.source = source;
	}

	public ArrayList<String> getInputs() {
		return inputs;
	}

	public void setInputs(ArrayList<String> inputs) {
		this.inputs = inputs;
	}

	public ArrayList<String> getOutputs() {
		return outputs;
	}

	public void setOutputs(ArrayList<String> outputs) {
		this.outputs = outputs;
	}

	public ArrayList<String> getMethods() {
		return methods;
	}

	public void setMethods(ArrayList<String> methods) {
		this.methods = methods;
	}

	public ArrayList<String> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<String> parameters) {
		this.parameters = parameters;
	}

	public ArrayList<ArrayList<String>> getLimits() {
		return limits;
	}

	public void setLimits(ArrayList<ArrayList<String>> limits) {
		this.limits = limits;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	private ArrayList<String> matchesOfString(String pattern, String matcher) {
		ArrayList<String> arrMatchers = new ArrayList<String>();
		Matcher mjct = Pattern.compile(pattern).matcher(matcher);
		while (mjct.find()) {
			arrMatchers.add(mjct.group());
		}
		return arrMatchers;
	}

	private ArrayList<String> matchesPatterns(String firstPattern, String secondPattern, String thirdPattern,
			String matcher) {
		Matcher mjct = Pattern.compile(firstPattern).matcher(matcher);
		ArrayList<String> arrMatchers = new ArrayList<String>();

		while (mjct.find()) {
			Matcher mjctsecond = Pattern.compile(secondPattern).matcher(mjct.group());
			while (mjctsecond.find()) {
				Matcher mjctfinal = Pattern.compile(thirdPattern).matcher(mjctsecond.group());
				while (mjctfinal.find()) {
					arrMatchers.add(mjctfinal.group());
				}

			}
		}

		return arrMatchers;
	}

	/**
	 * Method for build inputs or outputs
	 */
	public ArrayList<String> buildData(ArrayList<String> matchers) {
		ArrayList<ArrayList<String>> tokensTmp = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> tokens = new ArrayList<ArrayList<String>>();
		ArrayList<String> dataToSet = new ArrayList<String>();
		ArrayList<String> describe = new ArrayList<String>();

		if (matchers.size() > 0) {
			for (int i = 0; i < matchers.size(); i++) {
				ArrayList<String> inputToken = new ArrayList<String>();
				ArrayList<String> token = new ArrayList<String>();

				describe.add(matchers.get(i).toString().trim());

				// get numbers of the range
				Pattern p = Pattern.compile("\\d+");
				Matcher m = p.matcher(describe.get(i)); // ex: 5 5

				while (m.find()) {
					inputToken.add(m.group());
				}

				tokensTmp.add(inputToken);

				// treatment for function format
				token.add("(");

				for (int j = 0; j < inputToken.size(); j++) {
					token.add(tokensTmp.get(i).get(j));
				}
				token.add(")");

				tokens.add(token);

				StringBuffer sb = new StringBuffer();

				int g = 0;
				for (String s : tokens.get(i)) {
					sb.append(s);
					if (g != 0 && g != tokens.get(i).size() - 1 && g != tokens.get(i).size() - 2) {
						sb.append(",");
					}
					g++;
				}
				dataToSet.add(sb.toString());
			}
			return dataToSet;
		} else {
			return null;
		}
	}

	/**
	 * Method for build limits cases
	 */
	public ArrayList<ArrayList<String>> buildLimits(ArrayList<String> matchers) {
		ArrayList<ArrayList<String>> tokensTmp = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> tokens = new ArrayList<ArrayList<String>>();
		ArrayList<String> dataToSet = new ArrayList<String>();
		ArrayList<String> describe = new ArrayList<String>();

		if (matchers.size() > 0) {
			for (int i = 0; i < matchers.size(); i++) {
				ArrayList<String> inputToken = new ArrayList<String>();
				ArrayList<String> token = new ArrayList<String>();

				describe.add(matchers.get(i).toString().trim());

				// get numbers of the range
				Pattern p = Pattern.compile("\\d+");
				Matcher m = p.matcher(describe.get(i)); // ex: 5 5

				while (m.find()) {
					inputToken.add(m.group());
				}

				tokensTmp.add(inputToken);

				// treatment for function format
				for (int j = 0; j < inputToken.size(); j++) {
					token.add(tokensTmp.get(i).get(j));
				}

				tokens.add(token);
			}
			return tokens;
		} else {
			return null;
		}
	}

	/**
	 * Splice the data to distribuite values for input, output and method name
	 */
	public void spliteData() {

		// logger.info("The capturing of input, output and method name was started.");

		String source = getSource();

		ArrayList<String> matchersJcodingTime = this.matchesOfString("@JCodingTime(.*?\n.*?@Input)", source);

		ArrayList<String> matchersJCTLimitValue = this.matchesOfString("@JCodingTime(.*?\n.*?@LimitValue)", source);

		ArrayList<String> matchersOnlyTypeMethod = this.matchesPatterns("@LimitValue(.*?\n.*?\n)", "public(.*?\n)",
				"(\\w+\\s\\w+\\()", source);
		ArrayList<String> describeMethodNames = new ArrayList<String>();
		ArrayList<String> typeMethods = new ArrayList<String>();
		ArrayList<String> matchersMethods = this.matchesPatterns("@Output(.*?\n.*?\n)", "public(.*?\n)",
				"(\\w+\\s\\w+\\()", source);

		if (matchersJcodingTime.size() > 0) {

			// logical for get only inputs
			// get string range between @Input and @Output
			ArrayList<String> matchersInputOutput = this.matchesOfString("@Input(.*?\n.*?.*?@Output)", source);

			try {
				ArrayList<String> inputsToSet = buildData(matchersInputOutput);
				if (inputsToSet == null) {
					throw new NullPointerException();
				} else {
					setInputs(inputsToSet); // ex: (5,5)
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				System.out.print("Undeclared @Input");
			}

			// logical for get only outpus
			// get string range between @Output and public
			ArrayList<String> matchersOutputPublic = this.matchesOfString("@Output(.*?\n.*?public)", source);

			ArrayList<ArrayList<String>> outputTokens = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<String>> tokensTmp = new ArrayList<ArrayList<String>>();
			ArrayList<String> outputstoSet = new ArrayList<String>();

			try {
				ArrayList<String> outputsToSet = buildData(matchersOutputPublic);
				if (outputsToSet == null) {
					throw new NullPointerException();
				} else {
					setOutputs(outputsToSet);
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				System.out.print("Undeclared @Output");
			}

			if (matchersMethods.size() > 0) {

				for (int i = 0; i < matchersMethods.size(); i++) {
					String[] parts = matchersMethods.get(i).split("\\s+");

					typeMethods.add(parts[0]);
					describeMethodNames.add(parts[1].replace("(", ""));
				}
			}
		} else {
			// Nothing TODO
		}

		if (matchersJCTLimitValue.size() > 0) {
			ArrayList<String> matchersLimitValuePublic = this.matchesOfString("@LimitValue(.*?\n.*?public)", source);

			ArrayList<ArrayList<String>> limitsToSet = buildLimits(matchersLimitValuePublic);

			if (limitsToSet == null) {
				throw new NullPointerException();
			} else {
				setLimits(limitsToSet);
			}
			if (matchersOnlyTypeMethod.size() > 0) {
				for (int i = 0; i < matchersOnlyTypeMethod.size(); i++) {
					String[] parts = matchersOnlyTypeMethod.get(i).split("\\s+");

					typeMethods.add(parts[0]);
					describeMethodNames.add(parts[1].replace("(", ""));
				}
			}
		} else {
			// Nothing TODO
		}

		TestBuilder testMethodBuilder = new TestMethodBuilder(describeMethodNames, typeMethods, parameters,
				getOutputs(), getInputs(), limits);
		testMethodBuilder.generate();
//		System.out.println(testMethodBuilder.getStringBuffer().toString());
		testMethodBuilder.generateFile();

	}
}
