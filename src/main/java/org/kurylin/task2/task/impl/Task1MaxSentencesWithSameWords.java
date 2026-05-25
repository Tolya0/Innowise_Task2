package org.kurylin.task2.task.impl;

import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.entity.TextComponentType;
import org.kurylin.task2.task.TextTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Task1MaxSentencesWithSameWords implements TextTask<Integer> {

    @Override
    public Integer execute(TextComponent root) {
        if (root == null) {
            return 0;
        }
        List<TextComponent> sentences = new ArrayList<>();
        collectSentences(root, sentences);
        Map<String, Integer> wordSentenceCounts = new HashMap<>();
        for (int i = 0; i < sentences.size(); i++) {
            TextComponent sentence = sentences.get(i);
            List<TextComponent> words = new ArrayList<>();
            collectWords(sentence, words);
            Set<String> uniqueWords = new HashSet<>();
            for (int j = 0; j < words.size(); j++) {
                String wordStr = words.get(j).toString().toLowerCase();
                if (!wordStr.isEmpty()) {
                    uniqueWords.add(wordStr);
                }
            }
            for (String word : uniqueWords) {
                Integer currentCount = wordSentenceCounts.get(word);
                int count = (currentCount == null) ? 0 : currentCount;
                wordSentenceCounts.put(word, count + 1);
            }
        }
        int maxSentences = 0;
        for (Integer count : wordSentenceCounts.values()) {
            if (count != null && count > maxSentences) {
                maxSentences = count;
            }
        }
        return maxSentences;
    }

    private void collectSentences(TextComponent component, List<TextComponent> array) {
        if (component.getType() == TextComponentType.SENTENCE) {
            array.add(component);
        } else {
            try {
                int size = component.size();
                for (int i = 0; i < size; i++) {
                    collectSentences(component.get(i), array);
                }
            } catch (UnsupportedOperationException e) {
            }
        }
    }

    private void collectWords(TextComponent component, List<TextComponent> array) {
        if (component.getType() == TextComponentType.WORD) {
            array.add(component);
        } else {
            try {
                int size = component.size();
                for (int i = 0; i < size; i++) {
                    collectWords(component.get(i), array);
                }
            } catch (UnsupportedOperationException e) {
            }
        }
    }
}
