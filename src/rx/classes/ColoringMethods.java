package rx.classes;

import java.util.HashMap;

public class ColoringMethods {

    // hashmap with all values (text)
    public static HashMap<String, String> InitTextColors() {
        HashMap<String, String> Colors = new HashMap<>();
        Colors.put("black", Constants.ANSI_BLACK);
        Colors.put("red", Constants.ANSI_RED);
        Colors.put("green", Constants.ANSI_GREEN);
        Colors.put("yellow", Constants.ANSI_YELLOW);
        Colors.put("blue", Constants.ANSI_BLUE);
        Colors.put("purple", Constants.ANSI_PURPLE);
        Colors.put("cyan", Constants.ANSI_CYAN);
        Colors.put("white", Constants.ANSI_WHITE);
        Colors.put("no color", Constants.ANSI_RESET);
        return Colors;
    }

    // hashmap with all values (background)
    public static HashMap<String, String> InitBgColors() {
        HashMap<String, String> Colors = new HashMap<>();
        Colors.put("black", Constants.ANSI_BLACK_BACKGROUND);
        Colors.put("red", Constants.ANSI_RED_BACKGROUND);
        Colors.put("green", Constants.ANSI_GREEN_BACKGROUND);
        Colors.put("yellow", Constants.ANSI_YELLOW_BACKGROUND);
        Colors.put("blue", Constants.ANSI_BLUE_BACKGROUND);
        Colors.put("purple", Constants.ANSI_PURPLE_BACKGROUND);
        Colors.put("cyan", Constants.ANSI_CYAN_BACKGROUND);
        Colors.put("white", Constants.ANSI_WHITE_BACKGROUND);
        Colors.put("no color", Constants.ANSI_RESET);
        return Colors;
    }

    // Matching color to number (for updating purposes)
    public static HashMap<String, Integer> InitColorCodes() {
        HashMap<String, Integer> Colors = new HashMap<>();
        Colors.put("black", 1);
        Colors.put("red", 2);
        Colors.put("green", 3);
        Colors.put("yellow", 4);
        Colors.put("blue", 5);
        Colors.put("purple", 6);
        Colors.put("cyan", 7);
        Colors.put("white", 8);
        Colors.put("no color", 9);
        return Colors;
    }

    // Matching number to color (for updating purposes)
    public static HashMap<Integer, String> InitColorCodesReverse() {
        HashMap<Integer, String> Colors = new HashMap<>();
        Colors.put(1, "black");
        Colors.put(2, "red");
        Colors.put(3, "green");
        Colors.put(4, "yellow");
        Colors.put(5, "blue");
        Colors.put(6, "purple");
        Colors.put(7, "cyan");
        Colors.put(8, "white");
        Colors.put(9, "no color");
        return Colors;
    }
}
