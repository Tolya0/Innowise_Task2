package org.kurylin.task2.factory.impl;

import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.entity.TextComposite;
import org.kurylin.task2.entity.TextLeaf;
import org.kurylin.task2.entity.TextComponentType;
import org.kurylin.task2.factory.TextComponentFactory;

public class TextComponentFactoryImpl implements TextComponentFactory {

    @Override
    public TextComponent createComposite(TextComponentType type) {
        return new TextComposite(type);
    }

    @Override
    public TextComponent createLeaf(TextComponentType type, char value) {
        return new TextLeaf(type, value);
    }
}
