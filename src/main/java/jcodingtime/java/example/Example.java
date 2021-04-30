package jcodingtime.java.example;

import jcodingtime.java.verifier.annotation.Input;
import jcodingtime.java.verifier.annotation.JCodingTime;
import jcodingtime.java.verifier.annotation.LimitValue;
import jcodingtime.java.verifier.annotation.Output;

/**
 * Example Class source for generate unit tests
 */
public class Example {

	/**
	 * This is a example for use the JCodingTime annotations for traditional option
	 * @param firstParameter
	 * @param secondParameter
	 * @return
	 */
	@JCodingTime
	@Input(firstParam=5, secondParam=5)
	@Output(result=25)
	public static int multiplyTwoNumbers(int firstParameter, int secondParameter) {
		return firstParameter * secondParameter;
	}

	/**
	 * This is a example for use the JCodingTime annotations for traditional option
	 * @param firstParameter
	 * @param secondParameter
	 * @return
	 */
	@JCodingTime
	@Input(firstParam=2, secondParam=2)
	@Output(result=4)
	public static int sumTwoNumbers(int firstParameter, int secondParameter) {
		return firstParameter + secondParameter;
	}

	/**
	 * This is a example for use the JCodingTime annotations for limit value option
	 * @param age
	 * @return
	 */
	@JCodingTime
	@LimitValue(innerBoundary=0, upperBoundary=130)
	public static int setAge(int age) {
		return age;
	}

}
