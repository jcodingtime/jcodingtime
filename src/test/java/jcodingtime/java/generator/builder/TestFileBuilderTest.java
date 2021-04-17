package jcodingtime.java.generator.builder;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Unit tests for TestFileBuilder.
 */
public class TestFileBuilderTest {
    TestBuilder testBuilder;
    TestFileBuilder testFileBuilder;
    ArrayList<String> describeMethodNames = new ArrayList<>(Arrays.asList("multiplyTwoNumbers", "sumTwoNumbers", "setAge"));
    ArrayList<String> typeMethods = new ArrayList<>(Arrays.asList("int", "int", "int"));
    ArrayList<String> parameters = new ArrayList<>(Arrays.asList("int age"));
    ArrayList<String> outputs = new ArrayList<>(Arrays.asList("(25)", "(4)"));
    ArrayList<String> inputs = new ArrayList<>(Arrays.asList("(5,5)", "(2,2)"));
    ArrayList<String> firstArray = new ArrayList<>(Arrays.asList("0", "130"));
    ArrayList<ArrayList<String>> limits = new ArrayList<ArrayList<String>>(Arrays.asList(firstArray));
    String className = "Example";

    @Test
    void verifyStringGenerateIsNotEmpty() {
        testBuilder = new TestFileBuilder(describeMethodNames, typeMethods, parameters, outputs,inputs, limits, className);
        assertThat(testBuilder.generate()).isNotEmpty();
    }

    @Test
    void validateFields() {
        testFileBuilder = new TestFileBuilder(describeMethodNames, typeMethods, parameters, outputs,inputs, limits, className);
        ArrayList<String> describeMethodNames = new ArrayList<>(Arrays.asList("multiplyTwoNumbers", "sumTwoNumbers", "setAge"));
        ArrayList<String> typeMethods = new ArrayList<>(Arrays.asList("int", "int", "int"));
        ArrayList<String> parameters = new ArrayList<>(Arrays.asList("int age"));
        ArrayList<String> outputs = new ArrayList<>(Arrays.asList("(25)", "(4)"));
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("(5,5)", "(2,2)"));
        ArrayList<String> firstArray = new ArrayList<>(Arrays.asList("0", "130"));
        ArrayList<ArrayList<String>> limits = new ArrayList<ArrayList<String>>(Arrays.asList(firstArray));

        assertThat(testFileBuilder.getMethodNames()).isEqualTo(describeMethodNames);
        assertThat(testFileBuilder.getTypeMethods()).isEqualTo(typeMethods);
        assertThat(testFileBuilder.getOutputs()).isEqualTo(outputs);
        assertThat(testFileBuilder.getInputs()).isEqualTo(inputs);
        assertThat(testFileBuilder.getLimits()).isEqualTo(limits);
    }

    @Test
    void verifyGenerateFile() {
        testBuilder = new TestFileBuilder(describeMethodNames, typeMethods, parameters, outputs,inputs, limits, className);
        testBuilder.generate();
        testBuilder.generateFile();
        File directory = new File("src/test/java/jct/");
        assertThat(directory.exists()).isEqualTo(true);
        File file = new File("src/test/java/jct/"+className+"Test.java");
        assertThat(file.exists()).isEqualTo(true);
        file.delete();
        directory.delete();
    }
}
