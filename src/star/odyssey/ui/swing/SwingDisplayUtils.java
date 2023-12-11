package star.odyssey.ui.swing;

import star.odyssey.ui.ConsoleDisplayUtils;
import star.odyssey.ui.swing.callbacks.CallBackString;
import star.odyssey.ui.swing.callbacks.SwingCallBackColoredTextList;
import star.odyssey.ui.swing.text.ColoredText;
import star.odyssey.ui.swing.text.TextColor;

import java.util.ArrayList;
import java.util.List;

public class SwingDisplayUtils {
    private static SwingCallBackColoredTextList callBack;
    private static SwingDisplayUtils instance;

    private SwingDisplayUtils(){

    }

    public static SwingDisplayUtils getInstance(){
        if(instance == null)
            instance = new SwingDisplayUtils();
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
        if(callBack == null)
            throw new NullPointerException("SwingCallBackString must be initialized first in class SwingDisplayUtils.");
        callBack.callback(text, consoleCallback);
    }

    public void displayTextNl(List<ColoredText> text, CallBackString consoleCallback){
        text.add(new ColoredText("\n"));
        displayText(text, consoleCallback);
    }

    public static void clearScreen(){
        getInstance().displayText(null, null);
    }

    public static void setCallBack(SwingCallBackColoredTextList callBack) {
        SwingDisplayUtils.callBack = callBack;
    }
}
