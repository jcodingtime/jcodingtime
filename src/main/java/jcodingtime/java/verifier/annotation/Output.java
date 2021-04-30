package jcodingtime.java.verifier.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Output annotation accountable for be interpreted by parser
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Output {
	/**
	 * Result expected based of entries in input annotation
	 * @return
	 */
	int result();
}
