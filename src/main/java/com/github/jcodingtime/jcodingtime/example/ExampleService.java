package com.github.jcodingtime.jcodingtime.example;

import com.github.jcodingtime.jcodingtime.verifier.annotation.Input;
import com.github.jcodingtime.jcodingtime.verifier.annotation.JCodingTime;
import com.github.jcodingtime.jcodingtime.verifier.annotation.Output;

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
	@Input(firstParam="100", secondParam="20")
	@Output(result="80")
	public BigDecimal applyDiscount(BigDecimal totalValue, Integer percentualDiscount) {
		return totalValue.subtract(totalValue.multiply(BigDecimal.valueOf(percentualDiscount / 100)));
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
	@Input(firstParam="20", secondParam="10")
	@Output(result="200")
	public BigDecimal calculateTotalValue(BigDecimal price, Integer quantity) {
		return price.multiply(BigDecimal.valueOf(quantity));
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

	@JCodingTime
	@Input(firstParam= "cosmetic")
	@Output(result="true")
	public Boolean verifyCosmeticCategory(String category){
		if(category.equals("category")){
			return true;
		}
		return false;
	}

	@JCodingTime
	@Input(firstParam="1")
	@Output(result="true")
	public Boolean verifyPaymentType(Integer paymentMethod) {
		if(paymentMethod.equals(0)) {
			return false;
		}
		return true;
	}
}
