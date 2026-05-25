package org.kurylin.task2.parser;

import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.factory.TextComponentFactory;

public class WordParser extends AbstractTextParser {

    public WordParser(TextComponentFactory factory) {
        super(factory);
    }

    @Override
    public void parse(String input, TextComponent parent) {
        if (input == null || parent == null) {
            return;
        }
        if (nextParser != null) {
            nextParser.parse(input, parent);
        }
    }
}
