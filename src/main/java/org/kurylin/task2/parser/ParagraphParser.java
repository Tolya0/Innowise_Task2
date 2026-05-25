package org.kurylin.task2.parser;

import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.entity.TextComponentType;
import org.kurylin.task2.factory.TextComponentFactory;
import org.kurylin.task2.regex.Regex;

public class ParagraphParser extends AbstractTextParser {

    public ParagraphParser(TextComponentFactory factory) {
        super(factory);
    }

    @Override
    public void parse(String input, TextComponent parent) {
        if (input == null || parent == null) {
            return;
        }
        String[] sentences = input.split(Regex.SENTENCE_SPLIT_REGEX);
        for (int i = 0; i < sentences.length; i++) {
            String sentenceText = sentences[i];
            if (sentenceText == null || sentenceText.trim().isEmpty()) {
                continue;
            }
            TextComponent sentence = factory.createComposite(TextComponentType.SENTENCE);
            parent.add(sentence);
            if (nextParser != null) {
                nextParser.parse(sentenceText.trim(), sentence);
            }
        }
    }
}
