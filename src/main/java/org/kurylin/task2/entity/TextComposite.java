package org.kurylin.task2.entity;

import java.util.ArrayList;
import java.util.List;

public class TextComposite extends TextComponent {
    private final List<TextComponent> components = new ArrayList<>();

    public TextComposite(TextComponentType type) {
        super(type);
    }

    @Override
    public void add(TextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        components.remove(component);
    }

    @Override
    public TextComponent get(int index) {
        return components.get(index);
    }

    @Override
    public void set(int index, TextComponent component) {
        components.set(index, component);
    }

    @Override
    public int size() {
        return components.size();
    }

    public List<TextComponent> getChildren() {
        return components;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String delimiter = getDelimiter();
        for (int i = 0; i < components.size(); i++) {
            builder.append(components.get(i).toString());
            if (i < components.size() - 1) {
                builder.append(delimiter);
            }
        }
        return builder.toString();
    }

    private String getDelimiter() {
        switch (getType()) {
            case TEXT:
                return "\n";
            case PARAGRAPH:
            case SENTENCE:
                return " ";
            default:
                return "";
        }
    }
}
