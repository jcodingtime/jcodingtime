package jct;

import org.junit.jupiter.api.*;
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.*;
import com.mycompany.myapp.repository.ExampleRepository;
import com.mycompany.myapp.service.ExampleService;

public class ExampleServiceTest {

	@Autowired
	private ExampleRepository exampleRepository;
	private ExampleService exampleService;

	@BeforeEach
	public void setUp() {
		exampleService = new ExampleService(exampleRepository);
	}

	@Test
	public void testCaseMultiplyTwoNumbers() {
		int expected = (25);
		int obtained = exampleService.multiplyTwoNumbers(5,5);
		assertEquals(expected, obtained);
	}

	@Test
	public void testCaseSumTwoNumbers() {
		int expected = (4);
		int obtained = exampleService.sumTwoNumbers(2);
		assertEquals(expected, obtained);
	}

	@Test
	public void testCaseVerifyCountryAndAge() {
		Boolean expected = (true);
		Boolean obtained = exampleService.verifyCountryAndAge("Brazil", 18);
		assertEquals(expected, obtained);
	}

	@Test
	public void testCaseVerifyAdminUser() {
		Boolean expected = ();
		Boolean obtained = exampleService.verifyAdminUser();
		assertEquals(expected, obtained);
	}

	@AfterEach
	public void tearDown() {
	}

}
