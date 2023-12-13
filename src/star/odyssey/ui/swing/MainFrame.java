package star.odyssey.ui.swing;

import star.odyssey.character.Player;
import star.odyssey.game.GameUtil;
import star.odyssey.inventory.Item;
import star.odyssey.ui.swing.callbacks.CallBackString;
import star.odyssey.ui.swing.callbacks.CallBackVoid;
import star.odyssey.ui.swing.text.ColoredText;
import star.odyssey.ui.swing.text.TextColor;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private static int ITEM_ICON_SIZE = 56;
    private JPanel mainPanel;
    private JButton clickMeButton;
    private JButton helpButton;
    private JTextField textField1;
    private JTextPane textPane1;
    private JProgressBar playerHealthProgressBar;
    private JPanel playerAttributesPanel;
    private JProgressBar playerStrengthProgressBar;
    private JProgressBar playerDefenseProgressBar;
    private JPanel playerPanel;
    private JPanel playerBodyPanel;
    private JPanel panelBottom;
    private JPanel panelTop;
    private JPanel panelCenter;
    private JPanel rightPanel;
    private JLabel astronautLabel;
    private JLabel roomInventoryLabel;
    private JPanel roomInventoryPanel;

    // Custom components:
    private JLabel playerWeaponLabel = new JLabel();

    // List of labels that display the inventory
    private ArrayList<JLabel> inventoryLabelList = new ArrayList<>();
    //List of labels that display the rooms inventory
    private ArrayList<JLabel> roomInventoryLabelList = new ArrayList<>();

    StyleContext sc = new StyleContext();
    final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
    private final Map<TextColor, Style> styleMap = new HashMap<>();
    private CallBackVoid consoleCallback;
    private CallBackString consoleCallbackString;

    public MainFrame(){

        SwingDisplayUtils.initializeSwingDisplay(
                this::displayTextInsidePane,
                this::playerStatusChanged
        );

        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1300, 900);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);

        initializeAstronautPanel();
        initializeRoomItemsPanel();
        playerAttributesPanel.setLayout(new GridLayout(3,2));

        clickMeButton.addActionListener(this::clickMeButton_Click);
        textField1.addActionListener(this::clickMeButton_Click);
        helpButton.addActionListener(this::displayHelpPopup);

        initializeStyleMap();

        astronautLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
    }

    /*
     * This method will initialize the Astronaut panel with all its components
     * The astronaut panel is the one with the astronaut background and the inventory
     */
    private void initializeAstronautPanel(){
        // Setting the layout to null, so we can place components on top of each other
        // This is how we can place whatever we want exactly where we want it
        playerBodyPanel.setLayout(null);
        // Setting the size of the astronaut background picture and its position
        astronautLabel.setBounds(0, 0, 310, 465);
        // Setting the position of the astronaut equiped weapon and its size
        playerWeaponLabel.setBounds(47, 245, ITEM_ICON_SIZE, ITEM_ICON_SIZE);

        playerBodyPanel.add(playerWeaponLabel);
        playerBodyPanel.setComponentZOrder(playerWeaponLabel, 0);
        playerBodyPanel.setComponentZOrder(astronautLabel, 1);

        playerWeaponLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                inventoryLabelClickedEvent(playerWeaponLabel, e);
            }
        });

        int xOffset = 5;
        int yOffset = 345;
        int xSeparation = 7;
        int ySeparation = 7;
        int size = ITEM_ICON_SIZE;
        for (int y = 0; y < 2; y++){
            for(int x = 0; x < 5; x++) {
                JLabel label = new JLabel();
                //label.setIcon(new ImageIcon("data/images/ore.png"));
                label.setBounds(xOffset + x * (size + xSeparation), yOffset + y * (size + ySeparation), size, size);
                playerBodyPanel.add(label);
                playerBodyPanel.setComponentZOrder(label, 1);
                inventoryLabelList.add(label);

                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        inventoryLabelClickedEvent(label, e);
                    }
                });
            }
        }
    }

    /*
     * This method will initialize the RoomItemsPanel with the 10 labels that are inside
     * The itemsPanel is the one that shows what items are in the current room
     */
    private void initializeRoomItemsPanel(){
        // Layout null so we can place components on top of each other as needed
        roomInventoryPanel.setLayout(null);

        roomInventoryLabel.setBounds(0, 0, 312, 124);

        roomInventoryPanel.setComponentZOrder(roomInventoryLabel, 0);

        int xOffset = 5;
        int yOffset = 5;
        int xSeparation = 7;
        int ySeparation = 7;
        int size = ITEM_ICON_SIZE;
        for (int y = 0; y < 2; y++){
            for(int x = 0; x < 5; x++) {
                JLabel label = new JLabel();
                //label.setIcon(new ImageIcon("data/images/ore.png"));
                label.setBounds(xOffset + x * (size + xSeparation), yOffset + y * (size + ySeparation), size, size);
                roomInventoryPanel.add(label);
                roomInventoryPanel.setComponentZOrder(label, 0);
                roomInventoryLabelList.add(label);
                label.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        inventoryLabelClickedEvent(label, e);
                    }
                });
            }
        }
    }

    /*
     * This gets triggered when the user clicks on an inventory item
     * It receives the label that was clicked...
     * And a MouseEvent e
     */
    private void inventoryLabelClickedEvent(JLabel label, MouseEvent e){
        //label.setIcon(null);
        Object name = label.getClientProperty("name");
        Object leftClick = label.getClientProperty("leftClick");
        Object rightClick = label.getClientProperty("rightClick");
        Object middleClick = label.getClientProperty("middleClick");

        if(name != null){
            // Now we check what mouse button was pressed, 1: LEFT CLICK, 2: MIDDLE BUTTON CLICK, 3: RIGHT CLICK
            int a = e.getButton();
            if(a == 1 && leftClick != null){
                consoleCallbackString.callback(leftClick.toString());
            }
            if(a == 2 && middleClick != null){
                consoleCallbackString.callback(middleClick.toString());
            }
            if(a == 3 && rightClick != null){
                consoleCallbackString.callback(rightClick.toString());
            }
        }
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
        Style styleBrown = textPane1.addStyle("BrownStyle", null);
        StyleConstants.setForeground(styleBrown, Color.YELLOW);
        styleMap.put(TextColor.BROWN, styleBrown);

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

    // This method will get triggered by the back end, the player will get passed as parameter so the UI can update its values
    private void playerStatusChanged(Player player){
        // Modifying the values of the health, strength and defense bars
        playerHealthProgressBar.setValue(player.getHealth());
        playerHealthProgressBar.setToolTipText(String.valueOf(player.getHealth()));
        playerStrengthProgressBar.setValue(player.getStrength());
        playerStrengthProgressBar.setToolTipText(String.valueOf(player.getStrength()));
        playerDefenseProgressBar.setValue(player.getDefense());
        playerDefenseProgressBar.setToolTipText(String.valueOf(player.getDefense()));

        // This loop will render the inventory
        for (int i = 0; i < 10; i++){
            JLabel label = inventoryLabelList.get(i);
            if(i < player.getInventory().size()) {
                Item item = player.getInventory().get(i);
                label.setIcon(new ImageIcon("data/images/" + item.getIndex() + ".png"));
                label.putClientProperty("index", item.getIndex());
                label.putClientProperty("name", item.getName());
                label.putClientProperty("leftClick", "equip " + item.getName());
                label.putClientProperty("rightClick", "drop " + item.getName());
                label.setToolTipText(item.getName());
            }else{
                label.setIcon(null);
                label.putClientProperty("index", null);
                label.putClientProperty("name", null);
                label.putClientProperty("leftClick", null);
                label.putClientProperty("rightClick", null);
                label.setToolTipText(null);
            }
        }

        // This loop will render the items in the room
        for (int i = 0; i < 10; i++){
            JLabel label = roomInventoryLabelList.get(i);
            if(i < player.getLocation().getItems().size()) {
                Item item = player.getLocation().getItems().get(i);
                label.setIcon(new ImageIcon("data/images/" + item.getIndex() + ".png"));
                label.putClientProperty("index", item.getIndex());
                label.putClientProperty("name", item.getName());
                label.putClientProperty("leftClick", "get " + item.getName());
                label.setToolTipText(item.getName());
            }else{
                label.setIcon(null);
                label.putClientProperty("index", null);
                label.putClientProperty("name", null);
                label.putClientProperty("leftClick", null);
                label.setToolTipText(null);
            }
        }

        // Render the equipped weapon
        if(player.getEquippedWeapon() != null){
            playerWeaponLabel.setIcon(new ImageIcon("data/images/" + player.getEquippedWeapon().getIndex() + ".png"));
            // Adding
            playerWeaponLabel.putClientProperty("index", player.getEquippedWeapon().getIndex());
            playerWeaponLabel.putClientProperty("name", player.getEquippedWeapon().getName());
            playerWeaponLabel.putClientProperty("rightClick", "drop " + player.getEquippedWeapon().getName());
            playerWeaponLabel.setToolTipText(player.getEquippedWeapon().getName());
        }else{
            playerWeaponLabel.putClientProperty("index", null);
            playerWeaponLabel.putClientProperty("name", null);
            playerWeaponLabel.putClientProperty("rightClick", null);
            playerWeaponLabel.setToolTipText(null);
        }
    }
}
