package org.kurylin.task2.app;

import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.entity.TextComponentType;
import org.kurylin.task2.exception.CustomException;
import org.kurylin.task2.factory.TextComponentFactory;
import org.kurylin.task2.factory.impl.TextComponentFactoryImpl;
import org.kurylin.task2.parser.AbstractTextParser;
import org.kurylin.task2.parser.TextParser;
import org.kurylin.task2.reader.TextReader;
import org.kurylin.task2.reader.impl.TextReaderImpl;
import org.kurylin.task2.service.SortService;
import org.kurylin.task2.service.TextCountService;
import org.kurylin.task2.service.impl.SortServiceImpl;
import org.kurylin.task2.service.impl.TextCountServiceImpl;
import org.kurylin.task2.task.impl.Task1MaxSentencesWithSameWords;
import org.kurylin.task2.task.impl.Task2SortSentencesByLetterCount;
import org.kurylin.task2.task.impl.Task3SwapFirstLastLexemeInSentences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Application starting");
        
        String filePath = "src/main/resources/sample.txt";
        
        try {
            TextReader reader = new TextReaderImpl();
            TextComponentFactory factory = new TextComponentFactoryImpl();
            AbstractTextParser parser = new TextParser(factory);
            TextCountService countService = new TextCountServiceImpl();
            SortService sortService = new SortServiceImpl();

            String content = reader.readText(filePath);
            System.out.println("--- Original Text File Content ---");
            System.out.println(content);
            System.out.println("----------------------------------\n");

            TextComponent document = factory.createComposite(TextComponentType.TEXT);
            parser.parse(content, document);

            System.out.println("--- Restored Text (toString) ---");
            System.out.println(document.toString());
            System.out.println("--------------------------------\n");

            int lettersCount = countService.countLetters(document);
            int symbolsCount = countService.countSymbols(document);
            System.out.println("--- Character Statistics ---");
            System.out.println("Total Letters: " + lettersCount);
            System.out.println("Total Symbols (non-whitespace): " + symbolsCount);
            System.out.println("----------------------------\n");

            Task1MaxSentencesWithSameWords task1 = new Task1MaxSentencesWithSameWords();
            int task1Result = task1.execute(document);
            System.out.println("--- Task 1 Result ---");
            System.out.println("Max sentences with the same word: " + task1Result);
            System.out.println("---------------------\n");

            char searchLetter = 'a';
            Task2SortSentencesByLetterCount task2 = new Task2SortSentencesByLetterCount(sortService, searchLetter);
            List<TextComponent> sortedSentences = task2.execute(document);
            System.out.println("--- Task 2 Result (Sorted by letter '" + searchLetter + "') ---");
            for (int i = 0; i < sortedSentences.size(); i++) {
                System.out.println(sortedSentences.get(i).toString().trim());
            }
            System.out.println("---------------------\n");

            Task3SwapFirstLastLexemeInSentences task3 = new Task3SwapFirstLastLexemeInSentences();
            task3.execute(document);
            System.out.println("--- Task 3 Result (Swapped first/last lexemes) ---");
            System.out.println(document.toString());
            System.out.println("---------------------\n");

        } catch (CustomException e) {
            logger.error("Error occurred during text processing", e);
        }
        
        logger.info("Application finished");
    }
}
