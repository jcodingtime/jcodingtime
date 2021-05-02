package jcodingtime.java.example;

import jcodingtime.java.verifier.annotation.*;

import java.math.BigDecimal;

/**
 * Example Class source for generate unit tests
 */
public class ExampleService {

	/**
	 * This is a example for use the JCodingTime annotations for traditional option
	 * @param firstParameter
	 * @param secondParameter
	 * @return
	 */
	@JCodingTime
	@Input(firstParam="5", secondParam="5")
	@Output(result="25")
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
	@Input(firstParam="2")
	@Output(result="4")
	public static int sumTwoNumbers(int firstParameter, int secondParameter) {
		return firstParameter + secondParameter;
	}

	@JCodingTime
	@Input(firstParam= "Brazil", secondParam="18")
	@Output(result="true")
	public Boolean verifyCountryAndAge(String country, Integer age){
		if(country.equals("Brazil") && age >= 18){
			return true;
		}
		return false;
	}

	@JCodingTime
	@Input(firstParam= "Admin")
	@Output(result="true")
	public Boolean verifyAdminUser(String role){
		if(role.equals("Admin")){
			return true;
		}
		return false;
	}
}
