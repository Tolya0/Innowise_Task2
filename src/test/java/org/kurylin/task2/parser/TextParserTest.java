package org.kurylin.task2.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.entity.TextComponentType;
import org.kurylin.task2.factory.TextComponentFactory;
import org.kurylin.task2.factory.impl.TextComponentFactoryImpl;

import static org.junit.jupiter.api.Assertions.*;

public class TextParserTest {
    private AbstractTextParser parser;
    private TextComponentFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new TextComponentFactoryImpl();
        parser = new TextParser(factory);
    }

    @Test
    public void testParseEmptyText() {
        TextComponent doc = factory.createComposite(TextComponentType.TEXT);
        parser.parse("", doc);
        assertEquals(TextComponentType.TEXT, doc.getType());
        assertTrue(doc.size() == 0);
    }

    @Test
    public void testParseSimpleTextStructure() {
        String sample = "Hello, world! This is a simple test.";
        TextComponent doc = factory.createComposite(TextComponentType.TEXT);
        parser.parse(sample, doc);

        assertEquals(TextComponentType.TEXT, doc.getType());
        assertTrue(doc.size() > 0);
        
        TextComponent paragraph = doc.get(0);
        assertEquals(TextComponentType.PARAGRAPH, paragraph.getType());
        
        assertEquals(2, paragraph.size());
        
        TextComponent sentence1 = paragraph.get(0);
        assertEquals(TextComponentType.SENTENCE, sentence1.getType());
        
        assertEquals(2, sentence1.size());
    }

    @Test
    public void testToStringRestoresText() {
        String input = "Hello, world! Java is nice.";
        TextComponent doc = factory.createComposite(TextComponentType.TEXT);
        parser.parse(input, doc);
        
        assertEquals(input, doc.toString());
    }
}
