package jcodingtime.java.example.input;

import jcodingtime.java.annotations.Input;
import jcodingtime.java.annotations.JCodingTime;
import jcodingtime.java.annotations.Output;

public class Example {
	
	@JCodingTime
	@Input(firstParam=5, secondParam=5)
	@Output(result=25)
	public static int multiplyTwoNumbers(int firstParameter, int secondParameter) {
		return firstParameter * secondParameter;
	}
	
}
