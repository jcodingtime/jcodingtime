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
	
	@JCodingTime
	@Input(firstParam=2, secondParam=2)
	@Output(result=4)
	public static int sumTwoNumbers(int firstParameter, int secondParameter) {
		return firstParameter + secondParameter;
	}
	
}
