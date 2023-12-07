package star.odyssey.ui.swing;

import javax.swing.*;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JButton clickMeButton;
    private JLabel label1;

    public MainFrame(){
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 500);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }
}
