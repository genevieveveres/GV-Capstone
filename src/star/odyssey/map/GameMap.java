package star.odyssey.map;

import java.util.*;
import static star.odyssey.ui.ConsoleDisplayUtils.makeBrown;
import static star.odyssey.ui.ConsoleDisplayUtils.makeGreen;


public class GameMap {

    // INSTANCE VARIABLES
    private static Map<String, Integer> roomValues = new HashMap<>();
    private static List<Map.Entry<String, Integer>> sortedRooms;

    // CONSTRUCTORS

    // METHODS
    public static String drawGameMap(List<String> visitedLocations, String playerLocationIndex) {
        // Create a list of entries and sort them based on values
        LocationProcessor locationProcessor = new LocationProcessor();
        roomValues = locationProcessor.processLocations();

        // keep only the values that are present in the visitedLocations list
        roomValues.entrySet().removeIf(entry -> !visitedLocations.contains(entry.getKey()));

        sortedRooms = new ArrayList<>(roomValues.entrySet());
        sortedRooms.sort(Map.Entry.comparingByValue());

        // Count how many levels there are
        Set<Integer> levels = new HashSet<>();
        for (Map.Entry<String, Integer> entry : sortedRooms) {
            levels.add(Integer.parseInt(entry.getValue().toString().substring(0, 1)));
        }

        // Analyze the map and put missing entries for each level
        for (int level : levels) {
            analyzeAndFillMissingEntries(Integer.toString(level));
        }

        StringBuilder mapStringBuilder = new StringBuilder();

        // Print rows for each level
        for (int level : levels) {
            int numRows = (int) sortedRooms.stream().filter(entry -> entry.getValue().toString().startsWith(Integer.toString(level))).count();
            printRows(numRows, Integer.toString(level), playerLocationIndex);
            System.out.println();
        }

        return mapStringBuilder.toString();
    }

    private static void analyzeAndFillMissingEntries(String level) {
        int maxRoomNumber = getMaxRoomNumber(level);
        maxRoomNumber = (Integer.parseInt(level) * 10) + maxRoomNumber;

        for (int roomNumber = Integer.parseInt(level + "1"); roomNumber <= maxRoomNumber; roomNumber++) {
            String roomKey = level + roomNumber % 10;
            if (!roomValues.containsValue(roomNumber)) {
                // Add missing entry
                roomValues.put("blank_room_" + level + roomNumber % 10, roomNumber);
            }
        }

        // Sort the list again after adding missing entries
        sortedRooms = new ArrayList<>(roomValues.entrySet());
        sortedRooms.sort(Map.Entry.comparingByValue());

    }

    private static void printRows(int numRows, String level, String playerLocationIndex) {
        LocationProcessor locationProcessor = new LocationProcessor();
        for (Map.Entry<String, Integer> entry : sortedRooms) {
            if (entry.getValue().toString().startsWith(level)) {
                if (entry.getKey().startsWith("blank_room")) {
                    System.out.print("                                   ");
                } else {
                    if (locationProcessor.hasNorth(entry.getKey())) {
                        System.out.print(makeBrown(" ╔═══════════════╩════════════════╗"));
                    } else {
                        System.out.print(makeBrown(" ╔════════════════════════════════╗"));
                    }
                }
            }
        }
        System.out.println();
        for (Map.Entry<String, Integer> entry : sortedRooms) {
            if (entry.getValue().toString().startsWith(level)) {
                if (entry.getKey().startsWith("blank_room")) {
                    System.out.print("                                   ");
                } else {
                    System.out.print(makeBrown(" ║                                ║"));
                }
            }
        }
        System.out.println();
        for (Map.Entry<String, Integer> entry : sortedRooms) {
            String west;
            String east;
            String playerPin;
            String extraSpace;
            if (entry.getKey().equalsIgnoreCase(playerLocationIndex)) {
                playerPin = " \uD83D\uDC64";
            } else {
                playerPin = "";
            }
            if (entry.getValue().toString().startsWith(level)) {
                if (entry.getKey().startsWith("blank_room")) {
                    System.out.print("                                   ");
                } else {
                    if (locationProcessor.hasWest(entry.getKey())) {
                        west = "═╣";
                    } else {
                        west = " ║";
                    }
                    if (locationProcessor.hasEast(entry.getKey())) {
                        east = "╠";
                    } else {
                        east = "║";
                    }
                    String resultText = locationProcessor.getRoomNameByIndex(entry.getKey()) + playerPin;
                    printCentered(resultText, 32, west, east);
                }
            }
        }
        System.out.println();

        for (Map.Entry<String, Integer> entry : sortedRooms) {
            if (entry.getValue().toString().startsWith(level)) {
                if (entry.getKey().startsWith("blank_room")) {
                    System.out.print("                                   ");
                } else {
                    System.out.print(makeBrown(" ║                                ║"));
                }
            }
        }
        System.out.println();
        for (Map.Entry<String, Integer> entry : sortedRooms) {
            if (entry.getValue().toString().startsWith(level)) {
                if (entry.getKey().startsWith("blank_room")) {
                    System.out.print("                                   ");
                } else {
                    if (locationProcessor.hasSouth(entry.getKey())) {
                        System.out.print(makeBrown(" ╚═══════════════╦════════════════╝"));
                    } else {
                        System.out.print(makeBrown(" ╚════════════════════════════════╝"));
                    }

                }
            }
        }
    }

    private static void printCentered(String text, int width, String west, String east) {
        String extraSpace = "";
        int padding = Math.max(0, (width - text.length()) / 2);
        // odd number then add extra space
        if (text.length() % 2 != 0) {
            extraSpace = " ";
        }
        System.out.print(makeBrown(west) + " ".repeat(padding) + makeGreen(text) + " ".repeat(padding) + extraSpace + makeBrown(east));
    }

    // GETTERS AND SETTERS
    private static int getMaxRoomNumber(String level) {
        return sortedRooms.stream()
                .filter(entry -> entry.getValue().toString().startsWith(level))
                .map(entry -> Integer.parseInt(entry.getValue().toString().substring(1)))
                .max(Integer::compareTo)
                .orElse(0);
    }
}
