package org.kurylin.task2.reader;

import org.kurylin.task2.exception.CustomException;

public interface TextReader {
    String readText(String filePath) throws CustomException;
}
