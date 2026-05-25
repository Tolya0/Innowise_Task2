package org.kurylin.task2.factory;

import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.entity.TextComponentType;

public interface TextComponentFactory {
    TextComponent createComposite(TextComponentType type);
    TextComponent createLeaf(TextComponentType type, char value);
}
