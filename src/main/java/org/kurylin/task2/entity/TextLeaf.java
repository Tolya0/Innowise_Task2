package org.kurylin.task2.entity;

public class TextLeaf extends TextComponent {
    private final char value;

    public TextLeaf(TextComponentType type, char value) {
        super(type);
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    @Override
    public void add(TextComponent component) {
    }

    @Override
    public void remove(TextComponent component) {
    }

    @Override
    public TextComponent get(int index) {
        return null;
    }

    @Override
    public void set(int index, TextComponent component) {
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
