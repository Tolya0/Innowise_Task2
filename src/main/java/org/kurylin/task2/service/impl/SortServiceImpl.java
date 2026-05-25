package org.kurylin.task2.service.impl;

import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.entity.TextComponentType;
import org.kurylin.task2.entity.TextComposite;
import org.kurylin.task2.service.SortService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortServiceImpl implements SortService {
    private static final Logger logger = LoggerFactory.getLogger(SortServiceImpl.class);

    @Override
    public List<TextComponent> sortSentences(TextComponent component, char letter) {
        logger.info("Sorting sentences by letter '{}'", letter);
        List<TextComponent> sentences = new ArrayList<>();
        collectSentences(component, sentences);
        sentences.sort(Comparator.comparingInt((TextComponent s) -> countLetter(s, letter)).thenComparing(TextComponent::toString));
        return sentences;
    }

    @Override
    public int countLetter(TextComponent component, char letter) {
        if (component == null) {
            return 0;
        }
        char target = Character.toLowerCase(letter);
        String text = component.toString().toLowerCase();
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == target) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void collectSentences(TextComponent component, List<TextComponent> array) {
        if (component == null || array == null) {
            return;
        }
        if (component.getType() == TextComponentType.SENTENCE) {
            array.add(component);
        } else if (component instanceof TextComposite) {
            TextComposite composite = (TextComposite) component;
            int size = composite.size();
            for (int i = 0; i < size; i++) {
                collectSentences(composite.get(i), array);
            }
        }
    }
}
