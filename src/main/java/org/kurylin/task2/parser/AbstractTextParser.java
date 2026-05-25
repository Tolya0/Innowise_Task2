package org.kurylin.task2.parser;

import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.factory.TextComponentFactory;

public abstract class AbstractTextParser {
    protected AbstractTextParser nextParser;
    protected final TextComponentFactory factory;

    protected AbstractTextParser(TextComponentFactory factory) {
        if (factory == null) {
            throw new IllegalArgumentException();
        }
        this.factory = factory;
    }

    public void setNextParser(AbstractTextParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract void parse(String input, TextComponent parent);
}
