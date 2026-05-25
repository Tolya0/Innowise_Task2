package org.kurylin.task2.task.impl;

import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.entity.TextComposite;
import org.kurylin.task2.entity.TextComponentType;
import org.kurylin.task2.task.TextTask;

import java.util.ArrayList;
import java.util.List;

public class Task3SwapFirstLastLexemeInSentences implements TextTask<Void> {

    @Override
    public Void execute(TextComponent root) {
        if (root == null) {
            return null;
        }
        List<TextComponent> sentences = new ArrayList<>();
        collectSentences(root, sentences);
        for (int i = 0; i < sentences.size(); i++) {
            TextComponent sentence = sentences.get(i);
            if (sentence instanceof TextComposite) {
                TextComposite composite = (TextComposite) sentence;
                List<TextComponent> children = composite.getChildren();
                if (children != null && children.size() >= 2) {
                    TextComponent first = children.get(0);
                    TextComponent last = children.get(children.size() - 1);
                    composite.set(0, last);
                    composite.set(children.size() - 1, first);
                }
            }
        }
        return null;
    }

    private void collectSentences(TextComponent component, List<TextComponent> array) {
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
