package star.odyssey.ui.swing;

import star.odyssey.command.HelpCommand;
import star.odyssey.game.GameUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class HelpFrame extends JFrame {

    private static JTextArea helpTextArea = new JTextArea();

    public HelpFrame(){
        this.add(helpTextArea);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(460,500);
        this.setVisible(true);
        this.setResizable(false);
        loadHelpText();
        helpTextArea.setEditable(false);
        helpTextArea.setMargin(new Insets(10,10,0,0));
    }

    public static void main(String[] args) {
        HelpFrame theHelpFrame = new HelpFrame();
    }

    private static void loadHelpText(){
        String gameTxtFilePath = "./data/gameText.json";
        String helpText = GameUtil.jsonToString(gameTxtFilePath, "helpText");
        helpTextArea.append(helpText);
    }

    public static JTextArea getHelpTextArea() {
        return helpTextArea;
    }

    public void setHelpTextArea(JTextArea helpTextArea) {
        HelpFrame.helpTextArea = helpTextArea;
    }
}