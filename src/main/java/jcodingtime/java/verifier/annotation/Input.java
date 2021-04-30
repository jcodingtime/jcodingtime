package jcodingtime.java.verifier.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Input annotation accountable for be interpreted by parser using the firstParam and secondParam
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Input {
	/**
	 * First param used for entry of data
	 * @return
	 */
	int firstParam();
	/**
	 * Second param used for entry of data
	 * @return
	 */
	int secondParam();
}
