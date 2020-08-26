package example.input;

import annotations.Input;
import annotations.JCodingTime;
import annotations.Output;

public class Example {
	
	@JCodingTime
	@Input(firstParam=5, secondParam=5)
	@Output(result=25)
	public static int multiplyTwoNumbers(int firstParameter, int secondParameter) {
		return firstParameter * secondParameter;
	}
	
}
