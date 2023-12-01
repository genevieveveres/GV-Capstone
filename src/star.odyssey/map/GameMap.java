package star.odyssey.map;

import java.util.*;
import static star.odyssey.ui.ConsoleDisplayUtils.*;


public class GameMap {

    private static Map<String, Integer> roomValues = new HashMap<>();
    private static List<Map.Entry<String, Integer>> sortedRooms;

    public static String drawGameMap() {
        // Create a list of entries and sort them based on values
        LocationProcessor locationProcessor = new LocationProcessor();
        roomValues = locationProcessor.processLocations();
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
            printRows(numRows, Integer.toString(level));
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

    private static void printRows(int numRows, String level) {
        LocationProcessor locationProcessor = new LocationProcessor();
        for (Map.Entry<String, Integer> entry : sortedRooms) {
            if (entry.getValue().toString().startsWith(level)) {
                if (entry.getKey().startsWith("blank_room")) {
                    System.out.print("                               ");
                } else {
                    if (locationProcessor.hasNorth(entry.getKey())) {
                        System.out.print(makeBrown(" ╔═════════════╩══════════════╗"));
                    } else {
                        System.out.print(makeBrown(" ╔════════════════════════════╗"));
                    }
                }
            }
        }
        System.out.println();
        for (Map.Entry<String, Integer> entry : sortedRooms) {
            if (entry.getValue().toString().startsWith(level)) {
                if (entry.getKey().startsWith("blank_room")) {
                    System.out.print("                               ");
                } else {
                    System.out.print(makeBrown(" ║                            ║"));
                }
            }
        }
        System.out.println();
        for (Map.Entry<String, Integer> entry : sortedRooms) {
            String west;
            String east;
            if (entry.getValue().toString().startsWith(level)) {
                if (entry.getKey().startsWith("blank_room")) {
                    System.out.print("                               ");
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
                    printCentered(locationProcessor.getRoomNameByIndex(entry.getKey()), 28, west, east);
                }
            }
        }
        System.out.println();

        for (Map.Entry<String, Integer> entry : sortedRooms) {
            if (entry.getValue().toString().startsWith(level)) {
                if (entry.getKey().startsWith("blank_room")) {
                    System.out.print("                               ");
                } else {
                    System.out.print(makeBrown(" ║                            ║"));
                }
            }
        }
        System.out.println();
        for (Map.Entry<String, Integer> entry : sortedRooms) {
            if (entry.getValue().toString().startsWith(level)) {
                if (entry.getKey().startsWith("blank_room")) {
                    System.out.print("                               ");
                } else {
                    if (locationProcessor.hasSouth(entry.getKey())) {
                        System.out.print(makeBrown(" ╚═════════════╦══════════════╝"));
                    } else {
                        System.out.print(makeBrown(" ╚════════════════════════════╝"));
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
        System.out.print( makeBrown(west) + " ".repeat(padding) + makeGreen(text) + " ".repeat(padding) + extraSpace + makeBrown(east));
    }

    private static int getMaxRoomNumber(String level) {
        return sortedRooms.stream()
                .filter(entry -> entry.getValue().toString().startsWith(level))
                .map(entry -> Integer.parseInt(entry.getValue().toString().substring(1)))
                .max(Integer::compareTo)
                .orElse(0);
    }

//    public static void main(String[] args) {
//        drawGameMap();
//    }

}
