package org.kurylin.task2.regex;

public final class Regex {
    public static final String PARAGRAPH_SPLIT_REGEX = "\\n+";
    public static final String SENTENCE_SPLIT_REGEX = "(?<=[.!?])\\s+";
    public static final String LEXEME_SPLIT_REGEX = "\\s+";
    public static final String WORD_PATTERN_REGEX = "[\\p{L}\\d]+(?:['\\-][\\p{L}\\d]+)*";

    private Regex() {
    }
}
