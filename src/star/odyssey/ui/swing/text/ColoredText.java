package star.odyssey.ui.swing.text;

public class ColoredText {
    private String text;
    private TextColor textColor;
    private TextColor backgroundTextColor;

    public ColoredText(String text){
        this.text = text;
        this.textColor = TextColor.NONE;
        this.backgroundTextColor = TextColor.NONE;
    }

    public ColoredText(String text, TextColor consoleTextColor){
        this(text);
        this.textColor = consoleTextColor;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TextColor getTextColor() {
        return textColor;
    }

    public void setTextColor(TextColor textColor) {
        this.textColor = textColor;
    }

    public TextColor getBackgroundTextColor() {
        return backgroundTextColor;
    }

    public void setBackgroundTextColor(TextColor backgroundTextColor) {
        this.backgroundTextColor = backgroundTextColor;
    }
}
