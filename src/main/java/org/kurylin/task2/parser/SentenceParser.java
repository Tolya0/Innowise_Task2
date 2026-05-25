package org.kurylin.task2.parser;

import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.entity.TextComponentType;
import org.kurylin.task2.factory.TextComponentFactory;
import org.kurylin.task2.regex.Regex;

public class SentenceParser extends AbstractTextParser {

    public SentenceParser(TextComponentFactory factory) {
        super(factory);
    }

    @Override
    public void parse(String input, TextComponent parent) {
        if (input == null || parent == null) {
            return;
        }
        String[] lexemes = input.split(Regex.LEXEME_SPLIT_REGEX);
        for (int i = 0; i < lexemes.length; i++) {
            String lexemeText = lexemes[i];
            if (lexemeText == null || lexemeText.trim().isEmpty()) {
                continue;
            }
            TextComponent lexeme = factory.createComposite(TextComponentType.LEXEME);
            parent.add(lexeme);
            if (nextParser != null) {
                nextParser.parse(lexemeText.trim(), lexeme);
            }
        }
    }
}
