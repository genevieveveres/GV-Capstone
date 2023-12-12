package star.odyssey.ui.swing;

import star.odyssey.game.GameUtil;
import star.odyssey.sound.SoundEffect;
import star.odyssey.ui.swing.callbacks.CallBackString;
import star.odyssey.ui.swing.callbacks.CallBackVoid;
import star.odyssey.ui.swing.text.ColoredText;
import star.odyssey.ui.swing.text.TextColor;
import star.odyssey.command.SFXCommand;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JButton clickMeButton;
    private JButton helpButton;
    private JLabel label1;
    private JTextField textField1;
    private JTextPane textPane1;
    private boolean sfxStatus = true;

    StyleContext sc = new StyleContext();
    final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
    private final Map<TextColor, Style> styleMap = new HashMap<>();
    private CallBackVoid consoleCallback;
    private CallBackString consoleCallbackString;

    public MainFrame(){

        menuBar();

        SwingDisplayUtils.setCallBack(this::displayTextInsidePane);

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(820, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);



        clickMeButton.addActionListener(this::clickMeButton_Click);
        textField1.addActionListener(this::clickMeButton_Click);
        helpButton.addActionListener(this::displayHelpPopup);

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

    private void displayHelpPopup(ActionEvent e){
        String helpText = GameUtil.jsonToString("./data/gameText.json", "helpText2");
        JOptionPane.showMessageDialog(this,helpText);
    }

    private void menuBar(){

        //Create the menu Bar
        JMenuBar menuBar = new JMenuBar();

        //Create and add the sfxMenu
        JMenu sfxMenu = sfxMenu();
        menuBar.add(sfxMenu);

        //TODO: Create and add the musicMenu

        //Set the menuBar
        setJMenuBar(menuBar);
    }

    private JMenu sfxMenu(){
        SFXCommand sfxCom = new SFXCommand();

        JMenu sfxMenu = new JMenu("SFX");

        //Create, prep, and then add sfxOnOff to the sfxMenu
        JMenuItem sfxOnOff = new JMenuItem("SFX Off");
        sfxOnOff.addActionListener((event) -> {
            if(SoundEffect.isSoundEnabled()){ //if sound is currently on
                sfxCom.execute("off");//turn sound off
                sfxOnOff.setText("SFX On"); //change button text to opposite
            }
            else { //if sound is currently off
                sfxCom.execute("on"); //turn sound off
                sfxOnOff.setText("SFX Off"); //change button text to opposite
            }
        });
        sfxMenu.add(sfxOnOff);

        //Create, prep, and then add the volSlider to the sfxMenu
        JSlider volSlider = new JSlider(JSlider.VERTICAL, 75, 95, 85);
        Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        labelTable.put(75, new JLabel("Low") );
        labelTable.put(85, new JLabel("Med") );
        labelTable.put(95, new JLabel("High") );
        volSlider.setLabelTable(labelTable);
        volSlider.setPaintLabels(true);
        volSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int slidePointer = volSlider.getValue();
                System.out.println(slidePointer);
            }
        });


        sfxMenu.add(volSlider);

        return sfxMenu;
    }
}
