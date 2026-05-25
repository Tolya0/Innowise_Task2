package org.kurylin.task2.service.impl;

import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.entity.TextComponentType;
import org.kurylin.task2.entity.TextComposite;
import org.kurylin.task2.entity.TextLeaf;
import org.kurylin.task2.service.TextCountService;

import java.util.ArrayList;
import java.util.List;

public class TextCountServiceImpl implements TextCountService {

    @Override
    public int countLetters(TextComponent root) {
        if (root == null) {
            return 0;
        }
        List<TextLeaf> symbols = collectSymbols(root);
        int count = 0;
        for (int i = 0; i < symbols.size(); i++) {
            TextLeaf leaf = symbols.get(i);
            if (leaf.getType() == TextComponentType.SYMBOL && Character.isLetter(leaf.getValue())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countSymbols(TextComponent root) {
        if (root == null) {
            return 0;
        }
        return collectSymbols(root).size();
    }

    private List<TextLeaf> collectSymbols(TextComponent component) {
        List<TextLeaf> leaves = new ArrayList<>();
        collectSymbolsRecursive(component, leaves);
        return leaves;
    }

    private void collectSymbolsRecursive(TextComponent component, List<TextLeaf> leaves) {
        if (component instanceof TextLeaf) {
            leaves.add((TextLeaf) component);
        } else if (component instanceof TextComposite) {
            TextComposite composite = (TextComposite) component;
            int size = composite.size();
            for (int i = 0; i < size; i++) {
                collectSymbolsRecursive(composite.get(i), leaves);
            }
        }
    }
}
