package jct;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExampleTest {
	@Test	
	public void testCaseMultiplyTwoNumbers() {
		int expected = (25);
		int obtained = multiplyTwoNumbers(5,5);
		assertEquals(expected, obtained);
	}

	@Test	
	public void testCaseSumTwoNumbers() {
		int expected = (4);
		int obtained = sumTwoNumbers(2,2);
		assertEquals(expected, obtained);
	}

	@Test
	public void testCase1MultiplyTwoNumbers() {
		int age = -1;
		boolean valid = false;
		if (age >= 0 && age <= 130){
			valid = true;
		} else {
			valid = false;
		}
		assertNotEquals(true, valid);
	}

	@Test
	public void testCase2MultiplyTwoNumbers() {
		int age = 0;
		boolean valid = false;
		if (age >= 0 && age <= 130){
			valid = true;
	} else {
			valid = false;
		}
		assertEquals(true, valid);
	}

	@Test
	public void testCase3MultiplyTwoNumbers() {
		int age = 1;
		boolean valid = false;
		if (age >= 0 && age <= 130){
			valid = true;
		} else {
			valid = false;
		}
		assertEquals(true, valid);
	}

	@Test
	public void testCase4MultiplyTwoNumbers() {
		int age = 129;
		boolean valid = false;
		if (age >= 0 && age <= 130){
			valid = true;
		} else {
			valid = false;
		}
		assertEquals(true, valid);
	}

	@Test
	public void testCase5MultiplyTwoNumbers() {
		int age = 130;
		boolean valid = false;
		if (age >= 0 && age <= 130){
			valid = true;
		} else {
			valid = false;
		}
		assertEquals(true, valid);
	}

	@Test
	public void testCase6MultiplyTwoNumbers() {
		int age = 131;
		boolean valid = false;
		if (age >= 0 && age <= 130){
			valid = true;
		} else {
			valid = false;
		}
		assertNotEquals(true, valid);
	}

}