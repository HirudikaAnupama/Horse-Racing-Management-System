package org.example.rapidrun;

import java.util.List;

public class Array {

    private static final String[][] Details_Array = new String[20][8];
    private static boolean isArrayFull = false;
    private static boolean isHorseIDExist = false;

    private static boolean entryDeleted = false;

    public void addDetails(String horseID, String horseName, String jockeyName, String age, String breed, String raceRecord, String group, List<String> imagePaths) {
        // Check if the entry already exists
        if (isEntryExists(horseID)) {
            isHorseIDExist = true;
            return; // Exit the method if the entry already exists
        } else {
            isHorseIDExist = false;
        }

        // Find the first empty row in the array
        int emptyRowIndex = -1;
        for (int i = 0; i < Details_Array.length; i++) {
            if (Details_Array[i] == null) { // Check if the row itself is null
                Details_Array[i] = new String[8]; // Initialize the row if it is null
            }
            if (Details_Array[i][0] == null) { // Check if the first element of the row is null
                emptyRowIndex = i;
                break;
            }
        }

        // If an empty row is found, add the new data
        if (emptyRowIndex != -1) {
            Details_Array[emptyRowIndex] = new String[]{horseID, horseName, jockeyName, age, breed, raceRecord, group, String.valueOf(imagePaths)};
        } else {
            isArrayFull = true;
        }
    }


    // Check if entry horse ID already exists
    private static boolean isEntryExists(String id) {
        for (String[] row : Details_Array) {
            if (row != null && row.length > 0 && row[0] != null && row[0].equals(id)) {
                return true; // Entry with the specified ID already exists
            }
        }
        return false;
    }

    public static void deleteEntry(String idToDelete) {
        // Search for the ID in the entries array and remove if found
        for (int i = 0; i < Details_Array.length; i++) {
            // Ensure the current row is not null before accessing its elements
            if (Details_Array[i] != null && Details_Array[i].length > 0 && Details_Array[i][0] != null && Details_Array[i][0].equals(idToDelete)) {
                // Found the ID, remove the entry by setting the row to null
                Details_Array[i] = null;
                entryDeleted = true; // Set entryDeleted to true since an entry was deleted
                return; // Exit the method after removing the entry
            }
        }
        entryDeleted = false; // Set entryDeleted to false since no entry was deleted
    }


    public String[][] getDetails_Array() {
        return Details_Array;
    }

    public boolean isArrayFull() {
        return isArrayFull;
    }

    public boolean isIsHorseIDExist() {
        return isHorseIDExist;
    }

    public static boolean isEntryDeleted() {
        return entryDeleted;
    }



}
