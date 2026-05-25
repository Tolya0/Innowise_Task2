package org.kurylin.task2.task.impl;

import org.kurylin.task2.entity.TextComponent;
import org.kurylin.task2.service.SortService;
import org.kurylin.task2.task.TextTask;

import java.util.List;

public class Task2SortSentencesByLetterCount implements TextTask<List<TextComponent>> {
    private final SortService sortService;
    private final char targetLetter;

    public Task2SortSentencesByLetterCount(SortService sortService, char targetLetter) {
        if (sortService == null) {
            throw new IllegalArgumentException();
        }
        this.sortService = sortService;
        this.targetLetter = targetLetter;
    }

    @Override
    public List<TextComponent> execute(TextComponent root) {
        return sortService.sortSentences(root, targetLetter);
    }
}
