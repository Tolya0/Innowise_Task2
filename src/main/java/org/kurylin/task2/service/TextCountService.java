package org.kurylin.task2.service;

import org.kurylin.task2.entity.TextComponent;

public interface TextCountService {
    int countLetters(TextComponent root);
    int countSymbols(TextComponent root);
}
