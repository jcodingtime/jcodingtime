package com.github.jcodingtime.verifier.collector;
import static org.assertj.core.api.Assertions.*;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Unit tests for InputData.
 */
public class InputDataTest {
    private InputData inputData;
    private String source;
    private File file;

    @BeforeEach
    void init() throws IOException {
        String path = "src/main/java/com.github.jcodingtime/java/example/Example.java";
        file = FileUtils.getFile(path);
        File tmpDir = FileUtils.getTempDirectory();
        FileUtils.copyFileToDirectory(file, tmpDir);
        File newTempFile = FileUtils.getFile(tmpDir, file.getName());
        source = FileUtils.readFileToString(newTempFile, Charset.defaultCharset());
        inputData = new InputData();
    }

    @Test
    void verifyFields() {
        inputData.setSource(source);
        assertThat(inputData.getSource()).isEqualTo(source);
    }
}
