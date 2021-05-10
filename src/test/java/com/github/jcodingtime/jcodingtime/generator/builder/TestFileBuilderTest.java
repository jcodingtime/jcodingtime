package com.github.jcodingtime.jcodingtime.generator.builder;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Unit tests for TestFileBuilder.
 */
public class TestFileBuilderTest {
    private TestBuilder testBuilder;
    private TestFileBuilder testFileBuilder;
    private ArrayList<String> describeMethodNames;
    private ArrayList<String> typeMethods;
    private ArrayList<String> parameters;
    private ArrayList<String> outputs;
    private ArrayList<String> inputs;
    private ArrayList<String> firstArray;

    private ArrayList<ArrayList<String>> limits;
    private String className;
    private File directory;
    private File file;

    @BeforeEach
    void init() {
        describeMethodNames = new ArrayList<String>(Arrays.asList("multiplyTwoNumbers", "sumTwoNumbers", "setAge"));
        typeMethods = new ArrayList<String>(Arrays.asList("int", "int", "int"));
        parameters = new ArrayList<String>(Arrays.asList("int age"));
        outputs = new ArrayList<String>(Arrays.asList("(25)", "(4)"));
        inputs = new ArrayList<String>(Arrays.asList("(5,5)", "(2,2)"));
        firstArray = new ArrayList<String>(Arrays.asList("0", "130"));
        limits = new ArrayList<ArrayList<String>>(Arrays.asList(firstArray));
        className = "Example";

        testBuilder = new TestFileBuilder(describeMethodNames, typeMethods, parameters, outputs,inputs, limits, className);
        testBuilder.generate();
        testBuilder.generateFile();
        directory = new File("src/test/java/jct/");
        file = new File("src/test/java/jct/"+className+"Test.java");

        testFileBuilder = new TestFileBuilder(describeMethodNames, typeMethods, parameters, outputs,inputs, limits, className);
    }

    @Test
    void verifyStringGenerateIsNotEmpty() {
        assertThat(testBuilder.generate()).isNotEmpty();
    }

    @Test
    void validateFields() {
        ArrayList<String> describeMethodNames = new ArrayList<String>(Arrays.asList("multiplyTwoNumbers", "sumTwoNumbers", "setAge"));
        ArrayList<String> typeMethods = new ArrayList<String>(Arrays.asList("int", "int", "int"));
        ArrayList<String> parameters = new ArrayList<String>(Arrays.asList("int age"));
        ArrayList<String> outputs = new ArrayList<String>(Arrays.asList("(25)", "(4)"));
        ArrayList<String> inputs = new ArrayList<String>(Arrays.asList("(5,5)", "(2,2)"));
        ArrayList<String> firstArray = new ArrayList<String>(Arrays.asList("0", "130"));
        ArrayList<ArrayList<String>> limits = new ArrayList<ArrayList<String>>(Arrays.asList(firstArray));

        assertThat(testFileBuilder.getMethodNames()).isEqualTo(describeMethodNames);
        assertThat(testFileBuilder.getTypeMethods()).isEqualTo(typeMethods);
        assertThat(testFileBuilder.getParameters()).isEqualTo(parameters);
        assertThat(testFileBuilder.getOutputs()).isEqualTo(outputs);
        assertThat(testFileBuilder.getInputs()).isEqualTo(inputs);
        assertThat(testFileBuilder.getLimits()).isEqualTo(limits);
    }

    @Test
    void verifyExistFile() {
        assertThat(directory.exists()).isEqualTo(true);
        assertThat(file.exists()).isEqualTo(true);
    }

    @AfterEach
    void teardown() {
        file.delete();
        directory.delete();
    }
}
