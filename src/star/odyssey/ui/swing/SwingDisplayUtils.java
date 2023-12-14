package star.odyssey.ui.swing;

import star.odyssey.character.Player;
import star.odyssey.ui.ConsoleDisplayUtils;
import star.odyssey.ui.swing.callbacks.CallBackString;
import star.odyssey.ui.swing.callbacks.SwingCallBackColoredTextList;
import star.odyssey.ui.swing.callbacks.SwingCallBackPlayerStatusChanged;
import star.odyssey.ui.swing.text.ColoredText;
import star.odyssey.ui.swing.text.TextColor;

import java.util.ArrayList;
import java.util.List;

public class SwingDisplayUtils {
    private static SwingCallBackColoredTextList gameTextChangeCallback;
    private static SwingCallBackPlayerStatusChanged playerStatusChangedCallBack;
    private static SwingDisplayUtils instance;

    private SwingDisplayUtils(){

    }

    public static void initializeSwingDisplay(SwingCallBackColoredTextList callBack, SwingCallBackPlayerStatusChanged playerStatusChangedCallBack){
        setCallBack(callBack);
        setPlayerStatusChangedCallBack(playerStatusChangedCallBack);

        if(instance == null)
            instance = new SwingDisplayUtils();
    }

    public static SwingDisplayUtils getInstance(){
        if(instance == null)
            throw new NullPointerException("instace is null in SwingDisplayUtils.java, you must first initialize it by calling the static method initializeSwingDisplay.");
        return instance;
    }

    public static void pauseDisplay(CallBackString consoleCallback) {
        // SWING
        List<ColoredText> coloredTextList = new ArrayList<>(); // SWING
        coloredTextList.add(new ColoredText("\n"));
        coloredTextList.add(new ColoredText("\n"));
        coloredTextList.add(new ColoredText(ConsoleDisplayUtils.centerText(100, "Press \"ENTER\" to continue..."), TextColor.RED));
        SwingDisplayUtils.getInstance().displayTextNl(coloredTextList, consoleCallback);
    }

    public void displayText(List<ColoredText> text, CallBackString consoleCallback){
        if(gameTextChangeCallback == null)
            throw new NullPointerException("SwingCallBackString must be initialized first in class SwingDisplayUtils.");
        gameTextChangeCallback.callback(text, consoleCallback);
    }

    public void displayTextNl(List<ColoredText> text, CallBackString consoleCallback){
        text.add(new ColoredText("\n"));
        displayText(text, consoleCallback);
    }

    public static void clearScreen(){
        getInstance().displayText(null, null);
    }

    public static void setCallBack(SwingCallBackColoredTextList callBack) {
        SwingDisplayUtils.gameTextChangeCallback = callBack;
    }

    public static void setPlayerStatusChangedCallBack(SwingCallBackPlayerStatusChanged playerStatusChangedCallBack){
        SwingDisplayUtils.playerStatusChangedCallBack = playerStatusChangedCallBack;
    }

    public void updatePlayer(Player player){
        playerStatusChangedCallBack.callback(player);
    }
}
