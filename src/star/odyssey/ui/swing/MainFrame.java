package star.odyssey.ui.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JButton clickMeButton;
    private JLabel label1;
    private JTextField textField1;

    public MainFrame(){
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

        clickMeButton.addActionListener(this::clickMeButton_Click);
        textField1.addActionListener(this::clickMeButton_Click);
    }

    private void clickMeButton_Click(ActionEvent e){
        label1.setText(textField1.getText());
        textField1.setText("");
    }
}
