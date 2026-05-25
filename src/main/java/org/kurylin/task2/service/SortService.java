package org.kurylin.task2.service;

import org.kurylin.task2.entity.TextComponent;
import java.util.List;

public interface SortService {
    List<TextComponent> sortSentences(TextComponent component, char letter);
    int countLetter(TextComponent component, char letter);
    void collectSentences(TextComponent component, List<TextComponent> array);
}
