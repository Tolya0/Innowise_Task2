package org.kurylin.task2.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.entity.TextComponentType;
import org.kurylin.task2.factory.TextComponentFactory;
import org.kurylin.task2.factory.impl.TextComponentFactoryImpl;
import org.kurylin.task2.parser.AbstractTextParser;
import org.kurylin.task2.parser.TextParser;
import org.kurylin.task2.service.SortService;
import org.kurylin.task2.service.TextCountService;
import org.kurylin.task2.service.impl.SortServiceImpl;
import org.kurylin.task2.service.impl.TextCountServiceImpl;
import org.kurylin.task2.task.impl.Task1MaxSentencesWithSameWords;
import org.kurylin.task2.task.impl.Task2SortSentencesByLetterCount;
import org.kurylin.task2.task.impl.Task3SwapFirstLastLexemeInSentences;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TextTasksTest {
    private AbstractTextParser parser;
    private TextComponentFactory factory;
    private TextCountService countService;
    private SortService sortService;

    @BeforeEach
    public void setUp() {
        factory = new TextComponentFactoryImpl();
        parser = new TextParser(factory);
        countService = new TextCountServiceImpl();
        sortService = new SortServiceImpl();
    }

    @Test
    public void testCountLettersAndSymbols() {
        String testText = "Hello, world! 123.";
        TextComponent doc = factory.createComposite(TextComponentType.TEXT);
        parser.parse(testText, doc);
        
        assertEquals(10, countService.countLetters(doc));
        assertEquals(16, countService.countSymbols(doc));
    }

    @Test
    public void testTask1MaxSentencesWithSameWords() {
        String testText = "Java is fun. Java is powerful. Python is also fun. Java Java.";
        TextComponent doc = factory.createComposite(TextComponentType.TEXT);
        parser.parse(testText, doc);
        
        Task1MaxSentencesWithSameWords task1 = new Task1MaxSentencesWithSameWords();
        int result = task1.execute(doc);
        
        assertEquals(3, result);
    }

    @Test
    public void testTask2SortSentencesByLetterCount() {
        String testText = "Cat. Banana. Apple. Alphabet.";
        TextComponent doc = factory.createComposite(TextComponentType.TEXT);
        parser.parse(testText, doc);
        
        Task2SortSentencesByLetterCount task2 = new Task2SortSentencesByLetterCount(sortService, 'a');
        List<TextComponent> sorted = task2.execute(doc);
        
        assertEquals(4, sorted.size());
        
        int count0 = countLettersOfChar(sorted.get(0), 'a');
        int count1 = countLettersOfChar(sorted.get(1), 'a');
        int count2 = countLettersOfChar(sorted.get(2), 'a');
        int count3 = countLettersOfChar(sorted.get(3), 'a');
        
        assertTrue(count0 <= count1);
        assertTrue(count1 <= count2);
        assertTrue(count2 <= count3);
        
        assertEquals(3, count3);
    }

    @Test
    public void testTask3SwapFirstLastLexeme() {
        String testText = "Java is extremely cool. Programming language.";
        TextComponent doc = factory.createComposite(TextComponentType.TEXT);
        parser.parse(testText, doc);
        
        Task3SwapFirstLastLexemeInSentences task3 = new Task3SwapFirstLastLexemeInSentences();
        task3.execute(doc);
        
        String expected = "cool. is extremely Java language. Programming";
        assertEquals(expected, doc.toString());
    }

    private int countLettersOfChar(TextComponent sentence, char letter) {
        String s = sentence.toString().toLowerCase();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == letter) {
                count++;
            }
        }
        return count;
    }
}
