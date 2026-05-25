package org.kurylin.task2.entity;

public abstract class TextComponent {
    private final TextComponentType type;

    protected TextComponent(TextComponentType type) {
        if (type == null) {
            throw new IllegalArgumentException();
        }
        this.type = type;
    }

    public TextComponentType getType() {
        return type;
    }

    public abstract void add(TextComponent component);
    public abstract void remove(TextComponent component);
    public abstract TextComponent get(int index);
    public abstract void set(int index, TextComponent component);
    public abstract int size();
}
