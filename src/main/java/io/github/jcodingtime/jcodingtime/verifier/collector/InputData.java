package io.github.jcodingtime.jcodingtime.verifier.collector;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.jcodingtime.jcodingtime.example.exceptions.MatchNotExistException;
import io.github.jcodingtime.jcodingtime.generator.builder.TestBuilder;
import io.github.jcodingtime.jcodingtime.generator.builder.TestFileBuilder;

/**
 * This class must receive the source file and treats the data
 */
public class InputData {

	/**
	 * Inputs extrated for data treatment
	 */
	private ArrayList<String> inputs;
	/**
	 * Output extrated for data treatment
	 */
	private ArrayList<String> outputs;
	/**
	 * Methods extrated for data treatment
	 */
	private ArrayList<String> methods;
	/**
	 * Parameters extrated for data treatment
	 */
	private ArrayList<String> parameters;
	/**
	 * Limits extrated for data treatment
	 */
	private ArrayList<ArrayList<String>> limits;
	/**
	 * Source file in string type
	 */
	private String source;
	/**
	 * Class name
	 */
	private String className;

	/**
	 * Empty constructor
	 */
	public InputData() {
	}

	/**
	 * Completed constructor
	 */
	public InputData(ArrayList<String> inputs, ArrayList<String> outputs, ArrayList<String> methods,
			ArrayList<String> parameters, ArrayList<ArrayList<String>> limits, String source, String className) {
		this.inputs = inputs;
		this.outputs = outputs;
		this.methods = methods;
		this.parameters = parameters;
		this.limits = limits;
		this.source = source;
		this.className = className;
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * Method for extract of string relevant parts of content based of pattern
	 * @param pattern
	 * @param matcher
	 * @return
	 */
	private ArrayList<String> matchesOfString(String pattern, String matcher) {
		ArrayList<String> arrMatchers = new ArrayList<String>();
		Matcher mjct = Pattern.compile(pattern).matcher(matcher);
		while (mjct.find()) {
			arrMatchers.add(mjct.group());
		}
		return arrMatchers;
	}

	/**
	 * Method for extract parts of parts of content based of patterns
	 * @param firstPattern
	 * @param secondPattern
	 * @param thirdPattern
	 * @param matcher
	 * @return
	 */
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
	 * Method for build inputs or outputs entries
	 */
	private ArrayList<String> buildData(ArrayList<String> matchers) {
		ArrayList<ArrayList<String>> tokensTmp = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> tokens = new ArrayList<ArrayList<String>>();
		ArrayList<String> dataToSet = new ArrayList<String>();
		ArrayList<String> describe = new ArrayList<String>();

		if (matchers.size() > 0) {
			for (int i = 0; i < matchers.size(); i++) {
				ArrayList<String> inputToken = new ArrayList<String>();
				ArrayList<String> token = new ArrayList<String>();

				describe.add(matchers.get(i).toString().trim());

				//booleans verification
				if(describe.get(i).contains("true")) {
					inputToken.add("true");
				} else if (describe.get(i).contains("false")) {
					inputToken.add("false");
				}

				// get only numbers of the range
				//strings verification
				Pattern ps = Pattern.compile("\"[A-z]+\"");
				Matcher ms = ps.matcher(describe.get(i));
				while (ms.find() && !describe.get(i).contains("true") && !describe.get(i).contains("false")) {
					inputToken.add(ms.group());
				}

				if(!ms.find() && !describe.get(i).contains("true") && !describe.get(i).contains("false")){
					Pattern p = Pattern.compile("\\d+");
					Matcher m = p.matcher(describe.get(i));
					while (m.find()) {
						inputToken.add(m.group());
					}
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
	 * Method for build limits cases entries
	 */
	private ArrayList<ArrayList<String>> buildLimits(ArrayList<String> matchers) {
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
		String source = getSource();
		/**
		 * Verification for @JCodingTime, @Input and @Output annotations
		 */
		ArrayList<String> matchersJcodingTime = this.matchesOfString("@JCodingTime(.*?\n.*?@Input)", source);


		ArrayList<String> matchersJCTLimitValue = this.matchesOfString("@JCodingTime(.*?\n.*?@LimitValue)", source);

		ArrayList<String> matchersOnlyTypeMethod = this.matchesPatterns("@LimitValue(.*?\n.*?\n)", "public(.*?\n)",
				"(\\w+\\s\\w+\\()", source);
		ArrayList<String> matchersParams = this.matchesPatterns("@LimitValue(.*?\n.*?\n)", "public(.*?\n)",
				"(\\w+\\s\\w+\\))", source);
		ArrayList<String> describeMethodNames = new ArrayList<String>();
		ArrayList<String> typeMethods = new ArrayList<String>();
		ArrayList<String> matchersMethods = this.matchesPatterns("@Output(.*?\n.*?\n)", "public(.*?\n)",
				"(\\w+\\s\\w+\\()", source);

		/**
		 * Verification for className
		 */
		Matcher matcherDomain = Pattern.compile("class(.*?\n)").matcher(source);

		int index = 0;

		while (matcherDomain.find() && index == 0) {
			String[] parts = matcherDomain.group().split("\s");
			setClassName(parts[1]);
			index+=1;
		}

		try {

			if (matchersJcodingTime.size() > 0) {

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
			} else if (matchersJCTLimitValue.size() > 0) {
				ArrayList<String> matchersLimitValuePublic = this.matchesOfString("@LimitValue(.*?\n.*?public)",
						source);

				ArrayList<ArrayList<String>> limitsToSet = buildLimits(matchersLimitValuePublic);

				ArrayList<String> paramsTmp = new ArrayList<String>();

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
				if (matchersParams.size() > 0) {
					for (int i = 0; i < matchersParams.size(); i++) {
						paramsTmp.add(matchersParams.get(i).replace(")", ""));
					}
				}
				setParameters(paramsTmp);
			} else {
				throw new MatchNotExistException("Clause @JCodingTime not found.");
			}
		} catch (MatchNotExistException e) {
			System.out.println("Please, put the clause @JCodingTime inside the method for generate the tests.\n" + e);
		}

		TestBuilder testMethodBuilder = new TestFileBuilder(describeMethodNames, typeMethods, parameters,
				getOutputs(), getInputs(), limits, className);
		testMethodBuilder.generate();

		if (testMethodBuilder.getStringBuffer() != null) {
//			System.out.println(testMethodBuilder.getStringBuffer().toString());
			testMethodBuilder.generateFile();
		}

	}
}
