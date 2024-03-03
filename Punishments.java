import java.util.*;

class Punishments {
    public static ArrayList<String> tamePunishment = new ArrayList<>();
    public static ArrayList<String> mediumPunishment = new ArrayList<>();
    public static ArrayList<String> extremePunishment = new ArrayList<>();
    public static ArrayList<String> customPunishment = new ArrayList<>();

    public static void initialize() {
        // Tame
        tamePunishment.add("Make a prank call");
        tamePunishment.add("Sing a song in front of a big crowd");
        tamePunishment.add("Wear a funny suit everywhere you go for a day");
        tamePunishment.add("Dye your hair a different color");
        tamePunishment.add("Speak with an accent for an entire day");
        // Medium
        mediumPunishment.add("Give a foot massage to the winner");
        mediumPunishment.add("Hit a minimum of 6 fortnite Dances (10 seconds each) in a big crowd");
        mediumPunishment.add("Wear a funny custom shirt chosen by winner for a day");
        mediumPunishment.add("Wear a wig for a day.");
        // Extreme
        extremePunishment.add("Get haircut from winner");
        extremePunishment.add("Get a tattoo");
        extremePunishment.add("Recite cheesy pickup lines to strangers");
        extremePunishment.add("Carry a rubber ducky and nonstop use it during conversations for whole day");
        extremePunishment.add("Wear wet underwear on your head in public for whole day");
    }
}