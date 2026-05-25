package org.kurylin.task2.parser;

import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.entity.TextComponentType;
import org.kurylin.task2.factory.TextComponentFactory;

public class SymbolParser extends AbstractTextParser {

    public SymbolParser(TextComponentFactory factory) {
        super(factory);
    }

    @Override
    public void parse(String input, TextComponent parent) {
        if (input == null || parent == null) {
            return;
        }
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            TextComponent symbol = factory.createLeaf(TextComponentType.SYMBOL, c);
            parent.add(symbol);
        }
    }
}
