package star.odyssey.ui.swing;

import star.odyssey.ui.swing.callbacks.CallBackString;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingNavigationPanel {
    private JPanel navPanel = new JPanel();
    private JLabel northLabel;
    private JLabel southLabel;
    private JLabel eastLabel;
    private JLabel westLabel;

    public SwingNavigationPanel(MainFrame frame){
        navPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        navPanel.setLayout(new BorderLayout());
        initNavLabels();
        addListeners(frame);
    }

    private void initNavLabels(){
        northLabel = new JLabel("▲", SwingConstants.CENTER);
        navPanel.add(northLabel, BorderLayout.NORTH);

        southLabel = new JLabel("▼", SwingConstants.CENTER);
        navPanel.add(southLabel, BorderLayout.SOUTH);

        eastLabel = new JLabel("▶");
        navPanel.add(eastLabel, BorderLayout.EAST);

        westLabel = new JLabel("◀");
        navPanel.add(westLabel, BorderLayout.WEST);
    }

    private void addListeners(MainFrame frame){
        //add labels to an array
        JLabel[] dir = new JLabel[]{northLabel, southLabel, eastLabel, westLabel};

        //iterate through array instead of writing these 4 times
        for (JLabel l : dir) {
            l.setFont(new Font("Serif", Font.PLAIN, 30));
            l.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    frame.navLabelClickedEvent(l, e);
                }
            });
        }
    }

    public JPanel getNavPanel() {
        return navPanel;
    }

}