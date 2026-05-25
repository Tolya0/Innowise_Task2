package org.kurylin.task2.reader.impl;

import org.kurylin.task2.exception.CustomException;
import org.kurylin.task2.reader.TextReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextReaderImpl implements TextReader {
    private static final Logger logger = LoggerFactory.getLogger(TextReaderImpl.class);

    @Override
    public String readText(String filePath) throws CustomException {
        if (filePath == null) {
            throw new CustomException("Path is null");
        }
        logger.info("Reading: {}", filePath);
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new CustomException("File not found");
        }
        try {
            return Files.readString(path);
        } catch (IOException e) {
            throw new CustomException("Read error");
        }
    }
}
