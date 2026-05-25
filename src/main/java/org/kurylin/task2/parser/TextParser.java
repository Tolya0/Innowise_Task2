package org.kurylin.task2.parser;

import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.entity.TextComponentType;
import org.kurylin.task2.factory.TextComponentFactory;
import org.kurylin.task2.regex.Regex;

public class TextParser extends AbstractTextParser {

    public TextParser(TextComponentFactory factory) {
        super(factory);
        ParagraphParser paragraphParser = new ParagraphParser(factory);
        SentenceParser sentenceParser = new SentenceParser(factory);
        LexemeParser lexemeParser = new LexemeParser(factory);
        WordParser wordParser = new WordParser(factory);
        SymbolParser symbolParser = new SymbolParser(factory);

        this.setNextParser(paragraphParser);
        paragraphParser.setNextParser(sentenceParser);
        sentenceParser.setNextParser(lexemeParser);
        lexemeParser.setNextParser(wordParser);
        wordParser.setNextParser(symbolParser);
    }

    @Override
    public void parse(String input, TextComponent parent) {
        if (input == null || parent == null) {
            return;
        }
        String[] paragraphs = input.split(Regex.PARAGRAPH_SPLIT_REGEX);
        for (int i = 0; i < paragraphs.length; i++) {
            String paragraphText = paragraphs[i];
            if (paragraphText == null || paragraphText.trim().isEmpty()) {
                continue;
            }
            TextComponent paragraph = factory.createComposite(TextComponentType.PARAGRAPH);
            parent.add(paragraph);
            if (nextParser != null) {
                nextParser.parse(paragraphText.trim(), paragraph);
            }
        }
    }
}
