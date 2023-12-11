package star.odyssey.ui.swing;

import star.odyssey.command.HelpCommand;
import star.odyssey.game.GameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.Popup;

public class HelpFrame extends JFrame {

    private static JTextPane helpTextPane = new JTextPane();
    private static JPanel helpPanel = new JPanel();

    public HelpFrame(){
        loadHelpPanel();
        this.add(helpPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(460,550);
        this.setVisible(true);
        this.setResizable(false);

        JPopupMenu popup = new JPopupMenu();

    }

    //Test main method to use when changing box appearance
    public static void main(String[] args) {
        HelpFrame theHelpFrame = new HelpFrame();
    }

    private static void loadHelpPanel(){
        helpPanel.setLayout(new BorderLayout());
        loadHelpText();
        helpPanel.add(helpTextPane);
    }

    private static void loadHelpText(){
        String gameTxtFilePath = "./data/gameText.json";
        String helpText = GameUtil.jsonToString(gameTxtFilePath, "helpText");
        helpTextPane.setContentType("text/html");
        helpTextPane.setText(helpText);
        helpTextPane.setEditable(false);
        helpTextPane.setMargin(new Insets(10,10,0,0));
    }

    public static JTextPane getHelpTextArea() {
        return helpTextPane;
    }

    public void setHelpTextArea(JTextPane helpTextPane) {
        HelpFrame.helpTextPane = helpTextPane;
    }
}