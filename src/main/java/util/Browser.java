package util;

public enum Browser {
    CHROME("CHROME"),
    FIREFOX("FIREFOX"),
    EDGE("EDGE");

    private final String value;

    Browser(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
