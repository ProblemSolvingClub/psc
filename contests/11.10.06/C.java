import java.util.*;

public class C {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int numPlayers = in.nextInt();
        in.skip(" ");
        while (numPlayers != 0) {
            String rolls = in.nextLine();
            ArrayList<Integer> players = new ArrayList<Integer>();
            for (int i = 0; i < numPlayers; i++) {
                players.add(3);
            }
            int currPlayer = 1;
            int middle = 0;
            int i = 0;
            while (i < rolls.length) {
                char roll1 = rolls.charAt(i);        
                i++;
                char roll2 = rolls.charAt(i);        
                i++;
                char roll3 = rolls.charAt(i);        
                i++;
            }
            numPlayers = in.nextInt();
            in.skip(" ");
        }
    }

    public static action(ArrayList<Integer> players, char roll) {
        if (roll == "L") {
            giveLeft(players, currPlayer);                
        }
        else if (roll == "R") {
            giveRight(players, currPlayer);
        }
        else {
            players.set(currPlayer-1, players.get(currPlayer-1)-1);                    
        }
    }



    public static class Game {
        public ArrayList<Integer> players;
        public int middle;
        public int currPlayer;
        public Game(int numPlayers) {
            this.players = new ArrayList<Integer>(numPlayers);
            for (int i = 0; i < numPlayers; i++) {
                this.players.add(3);
            }
            this.middle = 0;
            this.currPlayer = 0;
        }
        public void simulate(String rolls, int gameNumber) {
            int i = 0;
            System.out.printf("Game %d:\n", gameNumber);
            while (i < rolls.length) {
                ArrayList<Character> playerRolls = new ArrayList<Character>(3);
                playerRolls.add(rolls.charAt(i));
                i++;
                playerRolls.add(rolls.charAt(i));
                i++;
                playerRolls.add(rolls.charAt(i));
                i++;
                this.processRolls(playerRolls);
                this.nextPlayer();
                if (this.winCondition())
                    this.displayWin();
                    break;
            }
            this.displayEnd();
        }
        private void displayWin() {
            for (int i = 0; i < this.players.size(); i++) {
                System.out.printf
            }
        }
        private void winCondition() {
            int numPlayerWithChips = 0;
            for (int chips : this.players) {
                if (chips > 0)
                    numPlayersWithChips++;
            }
            return (numPlayersWithChips == 1);
        }
        private void nextPlayer() {
            int possible = (currPlayer+1) % this.players.size();
            while (this.players.get(possible) == 0) {
                possible = (possible+1) % this.players.size();
            }
            this.currPlayer = possible;
        }
        private void processRolls(ArrayList<Character> rolls) {
            for (char roll : rolls) {
                if (roll == "L") {
                    this.giveLeft();
                }
                else if (roll == "R") {
                    this.giveRight();
                }
                else if (roll == "C") {
                    this.giveMiddle();
                }
            }
        }
        private void giveLeft() {
            int nextPlayer = (this.currPlayer+this.players.size()-1) % this.players.size();
            players.set(nextPlayer, players.get(nextPlayer)+1);
            players.set(currPlayer, players.get(currPlayer)-1);
        }
        private void giveRight() {
            int nextPlayer = (currPlayer+1) % players.size();
            players.set(nextPlayer, players.get(nextPlayer)+1);
            players.set(currPlayer, players.get(currPlayer)-1);
        }
        private void giveMiddle() {
            this.players.set(currPlayer, players.get(currPlayer)-1);                    
            this.middle++;
        }
    }
}
