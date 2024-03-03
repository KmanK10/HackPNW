import java.util.ArrayList;

class Scoreboard {
    private static ArrayList<String> names = new ArrayList<>(); // Stores the names of the players
    private static ArrayList<ArrayList<Integer>> scores = new ArrayList<>(); // Stores the scores of the players
    private static ArrayList<String> winners = new ArrayList<>(); // winners
    private static ArrayList<Integer> topScores = new ArrayList<>(); // scores
    private static ArrayList<String> losers = new ArrayList<>(); // losers
    private static ArrayList<Integer> lowScores = new ArrayList<>(); // scores

    /**
     * Initializes the scoreboard.
     * @param pNames The names of the players.
     * @param pScores The scores of the players.
     */
    public static void initialize(ArrayList<String> pNames, ArrayList<ArrayList<Integer>> pScores) {
        names = pNames;
        scores = pScores;
    }

    /**
     * Returns the names of the players.
     * @return The names of the players.
     */
    public static ArrayList<String> getNames(){
        return names;
    }

    /**
     * Adds a score to the scoreboard.
     * @param name The name of the player.
     * @param score The score of the player.
     */
    public static void addScore(String name, int score){
        if (names.indexOf(name) == -1) {
            names.add(name);
            scores.add(new ArrayList<>());
            scores.get(names.indexOf(name)).add(score);

            return;
        }

        for (int i = 0; i < scores.get(names.indexOf(name)).size(); i++) {
            if (score > scores.get(names.indexOf(name)).get(i)) {
                scores.get(names.indexOf(name)).add(i, score);

                break;
            }
        }

        scores.get(names.indexOf(name)).add(score);
    }

    /**
     * Returns the scores of the player.
     * @param name The name of the player.
     * @return The scores of the player.
     */
    public static ArrayList<Integer> getScores(String name){
        return scores.get(names.indexOf(name));
    }

    /**
     * Returns the scores of the scoreboard.
     * @return The scores of the scoreboard.
     */
    public static ArrayList<ArrayList<Integer>> getScores(){
        return scores;
    }

    /**
     * Returns the top score of the player.
     * @param name The name of the player.
     * @return The top score of the player.
    */
    public static int getTopScore(String name){
        return scores.get(names.indexOf(name)).get(0);
    }

    public static int getBottomScore(String name){
        return scores.get(names.indexOf(name)).get(0);
    }

    /**
     * Prints the scoreboard.
     */
    public static void printScores(){
        for(int i = 0; i < names.size(); i++){
            System.out.println(names.get(i));

            for(int score : scores.get(i)){
                System.out.print(score + ", ");
            }
            System.out.println("\n");
        }
    }

    /**
     * Get winners
     * @param numWinners number of winners
     * @return winners
     */
    public static ArrayList<String> getWinners(int numWinners) {
        // Variables
        ArrayList<String> tempNames = new ArrayList<>(getNames()); // get names from scoreboard
        ArrayList<ArrayList<Integer>> tempScores = new ArrayList<>(getScores()); // get scores from scoreboard
        int temp; // temporary variable for finding highest score
        int index; // index of highest score

        numWinners = Math.min(numWinners, getNames().size()); // prevent index out of bounds

        // Loop for numWinners times
        for (int i = 0; i < numWinners; i++) {
            // Variables
            temp = tempScores.get(0).get(0);
            index = 0;

            // Find highest score
            for (int j = 0; j < tempScores.size(); j++) {
                if (temp < tempScores.get(j).get(0)) {
                    temp = tempScores.get(j).get(0);
                    index = j;
                }
            }

            winners.add(tempNames.get(index)); // add winner
            topScores.add(temp); // add score

            tempNames.remove(index); // remove name
            tempScores.remove(index); // remove score        
        }

        return winners;
    }

    public static ArrayList<String> getLosers(int numLosers) {
        // Variables
        ArrayList<String> tempNames = new ArrayList<>(getNames()); // get names from scoreboard
        ArrayList<ArrayList<Integer>> tempScores = new ArrayList<>(getScores()); // get scores from scoreboard
        int temp; // temporary variable for finding lowest score
        int index; // index of lowest score

        numLosers = Math.min(numLosers, getNames().size()); // prevent index out of bounds

        // Loop for numLosers times
        for (int i = 0; i < numLosers; i++) {
            // Variables
            temp = tempScores.get(0).get(0);
                index = 0;

            // Find lowest score
            for (int j = 0; j < tempScores.size(); j++) {
                if (temp > tempScores.get(j).get(0)) {
                    temp = tempScores.get(j).get(0);
                    index = j;
                }
            }

            losers.add(tempNames.get(index)); // add loser
            lowScores.add(temp); // add score

            tempNames.remove(index); // remove name
            tempScores.remove(index); // remove score        
        }

        return losers;
    }
}

//disclaimer: MICAH IS STUPID AND DUMB AS POSSIBLE