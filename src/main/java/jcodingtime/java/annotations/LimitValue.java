package jcodingtime.java.annotations;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface LimitValue {
	int innerBoundary();
	int upperBoundary();
}
