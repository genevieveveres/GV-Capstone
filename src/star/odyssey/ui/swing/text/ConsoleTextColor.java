package star.odyssey.ui.swing.text;

public enum ConsoleTextColor {
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m"),
    WHITE("\u001B[37m"),
    RESET("\u001B[0m"),
    NONE("");
    private final String color;

    ConsoleTextColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
