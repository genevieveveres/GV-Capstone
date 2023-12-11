package star.odyssey.ui.swing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class HelpPop extends JFrame implements ActionListener {

    Popup helpPop;

    HelpPop(){
        JFrame helpPopFrame = new MainFrame();
        JLabel helpPopLabel = new JLabel("I am a popup");

        helpPopFrame.setSize(400, 400);

        PopupFactory pf = new PopupFactory();

        JPanel helpPopPanel = new JPanel();

        helpPopPanel.add(helpPopLabel);

        helpPop = pf.getPopup(helpPopFrame, helpPopPanel, 100, 100);

        JButton b = new JButton("click");

        b.addActionListener(this);

        JPanel p1 = new JPanel();
        p1.add(b);
        helpPopFrame.add(p1);
        helpPopFrame.show();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}