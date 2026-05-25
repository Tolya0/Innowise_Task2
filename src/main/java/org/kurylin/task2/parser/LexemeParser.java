package org.kurylin.task2.parser;

import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.entity.TextComponentType;
import org.kurylin.task2.factory.TextComponentFactory;
import org.kurylin.task2.regex.Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractTextParser {
    private final Pattern wordPattern;

    public LexemeParser(TextComponentFactory factory) {
        super(factory);
        this.wordPattern = Pattern.compile(Regex.WORD_PATTERN_REGEX);
    }

    @Override
    public void parse(String input, TextComponent parent) {
        if (input == null || parent == null) {
            return;
        }
        Matcher matcher = wordPattern.matcher(input);
        int lastIndex = 0;
        while (matcher.find()) {
            int start = matcher.start();
            for (int i = lastIndex; i < start; i++) {
                char c = input.charAt(i);
                TextComponent punctuation = factory.createLeaf(TextComponentType.PUNCTUATION, c);
                parent.add(punctuation);
            }
            String wordPart = matcher.group();
            TextComponent word = factory.createComposite(TextComponentType.WORD);
            parent.add(word);
            if (nextParser != null) {
                nextParser.parse(wordPart, word);
            }
            lastIndex = matcher.end();
        }
        for (int i = lastIndex; i < input.length(); i++) {
            char c = input.charAt(i);
            TextComponent punctuation = factory.createLeaf(TextComponentType.PUNCTUATION, c);
            parent.add(punctuation);
        }
    }
}
