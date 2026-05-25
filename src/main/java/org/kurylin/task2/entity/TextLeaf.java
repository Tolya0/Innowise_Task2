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
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(TextComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TextComponent get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void set(int index, TextComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
