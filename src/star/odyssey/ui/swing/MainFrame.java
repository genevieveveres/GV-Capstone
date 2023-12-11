package star.odyssey.ui.swing;

import star.odyssey.ui.swing.callbacks.CallBackString;
import star.odyssey.ui.swing.callbacks.CallBackVoid;
import star.odyssey.ui.swing.text.ColoredText;
import star.odyssey.ui.swing.text.TextColor;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JButton clickMeButton;
    private JTextField textField1;
    private JTextPane textPane1;

    StyleContext sc = new StyleContext();
    final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
    private final Map<TextColor, Style> styleMap = new HashMap<>();
    private CallBackVoid consoleCallback;
    private CallBackString consoleCallbackString;

    public MainFrame(){
        SwingDisplayUtils.setCallBack(this::displayTextInsidePane);

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

        clickMeButton.addActionListener(this::clickMeButton_Click);
        textField1.addActionListener(this::clickMeButton_Click);

        initializeStyleMap();
    }

    private void initializeStyleMap(){

        Font font = new Font("Courier New", Font.PLAIN, 12);
        textPane1.setFont(font);

        Style styleRed = textPane1.addStyle("RedStyle", null);
        StyleConstants.setForeground(styleRed, Color.RED);
        styleMap.put(TextColor.RED, styleRed);
        Style styleBlue = textPane1.addStyle("BlueStyle", null);
        StyleConstants.setForeground(styleBlue, Color.BLUE);
        styleMap.put(TextColor.BLUE, styleBlue);
        Style styleGreen = textPane1.addStyle("GreenStyle", null);
        StyleConstants.setForeground(styleGreen, Color.GREEN);
        styleMap.put(TextColor.GREEN, styleGreen);
        Style styleMagenta = textPane1.addStyle("MagentaStyle", null);
        StyleConstants.setForeground(styleMagenta, Color.MAGENTA);
        styleMap.put(TextColor.MAGENTA, styleMagenta);
        Style styleCyan = textPane1.addStyle("CyanStyle", null);
        StyleConstants.setForeground(styleCyan, Color.CYAN);
        styleMap.put(TextColor.CYAN, styleCyan);

        Style fontStyle = textPane1.addStyle("MyFont", null);
        styleMap.put(TextColor.NONE, fontStyle);
        StyleConstants.setForeground(fontStyle, Color.WHITE);
        for (var style : styleMap.values()){
            StyleConstants.setBold(style, false);
        }
    }

    private void clickMeButton_Click(ActionEvent e){
        if(textField1.getText().equals("")){
            consoleCallbackString.callback(null);
        }else {
            consoleCallbackString.callback(textField1.getText());
        }
        textField1.setText("");
    }

    public void displayTextInsidePane(java.util.List<ColoredText> text, CallBackString callback){
        this.consoleCallbackString = callback;
        if(text == null){
            textPane1.setText("");
        }else {
            StyledDocument styledDoc = textPane1.getStyledDocument();
            Style defaultStyle = textPane1.getStyle(StyleContext.DEFAULT_STYLE);
            try {
                for (var elem : text) {
                    styledDoc.insertString(styledDoc.getLength(), elem.getText(), styleMap.get(elem.getTextColor()));
                }
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }
}
