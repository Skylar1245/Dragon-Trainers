
import java.util.Random;
import java.util.Scanner;

public class Main {
  private static final String[] DRAGONS = { "Plant", "Water", "Fire" };

  public static int getNumberOfMatches(Scanner input) {
    int matches = -1;
    while (matches <= 0) { // repeat until a proper number is there
      System.out.print("How many matches will we play? ");
      matches = input.nextInt();
      input.nextLine();
      if (matches <= 0) {
        System.out.println("ERROR - number of matches must be positive!");
      }
    }
    return matches;
  }

  public static String promptForDragon(Scanner input) {
    String dragon = "";
    while (dragon.length() <= 0) { // repeat until a proper dragon is given
      System.out.print("Please select a dragon [Plant/Water/Fire]: ");
      dragon = input.nextLine();
      if (dragon.length() <= 0) {
        System.out.println("ERROR - Dragon prompt cannot be empty.");
      }
    }
    return dragon;
  }

  public static int dragonToNumber(char dragon) {
    int dragonNum;
    if (dragon == 'P') {
      dragonNum = 0;
    } else if (dragon == 'W') {
      dragonNum = 1;
    } else if (dragon == 'F') {
      dragonNum = 2;
    } else {
      dragonNum = -1;
    }
    return dragonNum;
  }

  /**
   * Takes a number representing the player's choice and another representing
   * the computer's choice. Returns a 0 if they tie, a 1 if the player wins,
   * and a -1 if the player loses. Note that the values map to the indexes of
   * the array DRAGONS above (0 is a Plant dragon, 1 is a Water dragon, 2 is a
   * Fire Dragon).
   *
   * @param player
   *               - value 0-2 representing player dragon choice
   * @param cpu
   *               - value 0-2 representing computer dragon choice
   * @return 1 if the player wins, -1 if the computer wins, 0 if they tie
   */
  public static int determineWinner(int player, int cpu) {
    int results = 0;
    if ((player + 1 == cpu) || (player == 2 && cpu == 0)) {
      results = 1;
    } else if ((cpu + 1 == player) || (cpu == 2 && player == 0)) {
      results = -1;
    } else {
      results = 0;
    }
    return results;
  }

  /**
   * Takes a number representing the player's choice and another number
   * representing the computer's choice, and a third number that is positive
   * if the player is the winner, negative if the computer is the winner, and
   * 0 if they tied. Then displays the appropriate player defeats computer or
   * computer defeats player or tie message as given in the project
   * description.
   *
   * @param player
   *               index into the DRAGONS array representing the player choice
   * @param cpu
   *               index into the DRAGONS array representing the computer's
   *               choice
   * @param winner
   *               0 for a tie, positive for a player win, negative for a
   *               computer win
   */
  public static void displayMatchResult(int player, int cpu, int winner) {
    if (winner == 1) {
      System.out.println(DRAGONS[player] + " defeats " + DRAGONS[cpu] + " - you win!");
    } else if (winner == -1) {
      System.out.println(DRAGONS[cpu] + " defeats " + DRAGONS[player] + " - you lose!");
    } else {
      System.out.println("A tie!");
    }
  }

  /**
   * Takes the number of wins, losses and ties and displays the final message
   * and summary statistics as given in the project description.
   *
   * @param wins
   *               number of total wins for the player
   * @param losses
   *               number of total losses for the player
   * @param ties
   *               number of ties
   */
  public static void displayFinalResult(int wins, int losses, int ties) {
    System.out.println("The results are in!");
    System.out.println("You won " + wins + " match(es).");
    System.out.println("I won " + losses + " match(es).");
    System.out.println("We tied " + ties + " match(es).");
  }

  /**
   * NOTE: The main method has been completed for you. If you correctly
   * complete the methods above, the main method will "just work" and produce
   * the correct output.
   */
  public static void main(String[] args) {
    // Prompt for a random number seed
    Scanner keyboard = new Scanner(System.in);
    System.out.print("Enter a random seed: ");
    int seed = Integer.parseInt(keyboard.nextLine());
    // Create a Random instance with the seed
    Random rnd = new Random(seed);
    // Prompt for number of matches to play
    int totalMatches = getNumberOfMatches(keyboard);

    // Start with wins, losses and ties at 0.
    // Repeat until all matches have been played (use the sum of the
    // results so we don't need another variable)
    int wins = 0, losses = 0, ties = 0;
    while ((wins + losses + ties) < totalMatches) {
      // Ask the user for a dragon to use
      String input = promptForDragon(keyboard);

      // Get the first character of the user's input as an uppercase
      // value.
      char dChar = input.toUpperCase().charAt(0);

      // Convert the user's input to an index for the DRAGONS array
      int playerDragon = dragonToNumber(dChar);

      // Generate a choice between 0 and 2 for the computer
      int cpuDragon = rnd.nextInt(3);

      // Display the results
      System.out.println("I chose: " + DRAGONS[cpuDragon] + " dragon.");

      // If the player didn't enter a valid choice, print out an error
      // message and increase the number of losses.
      if (playerDragon == -1) {
        System.out.println("You don't have the " + input + " dragon.");
        System.out.println("So no dragon fights for you.");
        System.out.println("I win by default!");
        losses++;
      } else {
        // Print out the player's choice.
        System.out.println("You chose: " + DRAGONS[playerDragon] + " dragon.");
        // Determine who won the match.
        int winner = determineWinner(playerDragon, cpuDragon);
        // Display the result of the match.
        displayMatchResult(playerDragon, cpuDragon, winner);

        // Increase the count of wins, losses or ties according to
        // who won the match.
        if (winner > 0) {
          wins++;
        } else if (winner < 0) {
          losses++;
        } else {
          ties++;
        }
      }
      System.out.println();
    }
    // Display the final summary of the match to the screen.
    displayFinalResult(wins, losses, ties);

    // Don't forget to close the Scanner!
    keyboard.close();
  }

}