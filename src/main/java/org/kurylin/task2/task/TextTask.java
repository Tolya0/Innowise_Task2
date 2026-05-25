package org.kurylin.task2.task;

import org.kurylin.task2.entity.TextComponent;

public interface TextTask<T> {
    T execute(TextComponent root);
}
