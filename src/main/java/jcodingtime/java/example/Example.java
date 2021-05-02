package jcodingtime.java.example;

import jcodingtime.java.verifier.annotation.Input;
import jcodingtime.java.verifier.annotation.JCodingTime;
import jcodingtime.java.verifier.annotation.LimitValue;
import jcodingtime.java.verifier.annotation.Output;

import java.math.BigDecimal;

/**
 * Example Class source for generate unit tests
 */
public class Example {

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

	@JCodingTime
	@LimitValue(innerBoundary = 0, upperBoundary = 9999)
	public void setTotalPrice(BigDecimal totalPrice) {}
}
