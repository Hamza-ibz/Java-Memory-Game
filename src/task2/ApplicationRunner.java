package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ApplicationRunner {

    public static String[][] board = new String[8][8];
    public static String[][] cards = new String[8][8];
    public static Scanner scanner = new Scanner(System.in);
    private static String[] words = new String[64];

    public static void main(String[] args) {

        String filepath = System.getProperty("user.dir");

        String dataFile = filepath + File.separator + "small.txt";
        String dataFile2 = filepath + File.separator + "medium.txt";
        String dataFile3 = filepath + File.separator + "large.txt";

        File fileobj = new File(dataFile);
        File fileobj2 = new File(dataFile2);
        File fileobj3 = new File(dataFile3);

        Scanner input = null;
        Scanner input2 = null;
        Scanner input3 = null;

        try {

            input = new Scanner(fileobj);
            input2 = new Scanner(fileobj2);
            input3 = new Scanner(fileobj3);

            while (true) {
                System.out.println("-----------------------------------------");
                System.out.println("    WELCOME TO THE MEMORY SQUARE GAME");
                System.out.println("-----------------------------------------");
                System.out.println("");

                System.out.println("EASY ..................... 1");
                System.out.println("MEDIUM ................... 2");
                System.out.println("HARD ..................... 3");
                System.out.println("QUIT ..................... 0");
                String nq = scanner.nextLine();
                if (nq.equals("0")) {
                    System.out.println("Exitting....");
                    break;
                } else if (nq.equals("1")) {
                    System.out.println("-----------------------------------------");
                    System.out.println("EASY MODE SELECTED");
                    System.out.println("-----------------------------------------");
                    System.out.println("Remember to enter selection (squares) as 2 digits representing rows and column.");
                    System.out.println("For example to enter the square at row 2 column 3, enter \"23\" at the prompt (without quotes).");
                    System.out.println("Enjoy your game.");
                    System.out.println("");
                    int i = 0;
                    int a = 15;
                    while (input.hasNext()) { // while there is stuff in the file the loop will run.
                        String line = input.nextLine().trim(); //input.nextLine().trim() reads line by line
                        words[i] = line;
                        words[a] = line;
                        i++;
                        a--;
                    }

                    easy(4, 4);
                    break;

                } else if (nq.equals("2")) {
                    System.out.println("-----------------------------------------");
                    System.out.println("MEDIUM MODE SELECTED");
                    System.out.println("-----------------------------------------");
                    System.out.println("Remember to enter selection (squares) as 2 digits representing rows and column.");
                    System.out.println("For example to enter the square at row 2 column 3, enter \"23\" at the prompt (without quotes).");
                    System.out.println("Enjoy your game.");
                    System.out.println("");
                    int i = 0;
                    int a = 35;
                    while (input2.hasNext()) { // while there is stuff in the file the loop will run.
                        String line = input2.nextLine().trim(); //input.nextLine().trim() reads line by line
                        words[i] = line;
                        words[a] = line;
                        i++;
                        a--;
                    }

                    medium(6, 6);
                    break;

                } else if (nq.equals("3")) {
                    System.out.println("-----------------------------------------");
                    System.out.println("HARD MODE SELECTED");
                    System.out.println("-----------------------------------------");
                    System.out.println("Remember to enter selection (squares) as 2 digits representing rows and column.");
                    System.out.println("For example to enter the square at row 2 column 3, enter \"23\" at the prompt (without quotes).");
                    System.out.println("Enjoy your game.");
                    System.out.println("");
                    int i = 0;
                    int a = 63;
                    while (input3.hasNext()) { // while there is stuff in the file the loop will run.
                        String line = input3.nextLine().trim(); //input.nextLine().trim() reads line by line
                        words[i] = line;
                        words[a] = line;
                        i++;
                        a--;
                    }

                    hard(8, 8);
                    break;

                } else {
                    System.out.println("Invalid Character...");
                    continue;
                }
            }

        } catch (FileNotFoundException fnf) {
            System.out.println("where is this file?");
            System.exit(0);

        } finally {
            input.close();
        }
    }

    public static void printBoard(int x, int sqr) {
        for (int z = 1; z <= x; z++) {
            System.out.printf("%13s", z);
            System.out.print("  ");
        }
        System.out.println("");
        System.out.print("---+");
        for (int y = 0; y < sqr; y++) {
            System.out.print("---------------");
        }
        System.out.println("");
        for (int i = 0; i < sqr; i++) {
            System.out.print(" " + (i + 1) + " ");
            System.out.print("|");
            for (int j = 0; j < sqr; j++) {
                System.out.printf("%14s", board[i][j]);
                System.out.print("|");
            }
            System.out.println();
        }
    }

    public static void shuffleCards(int num_cards, int sqr) {
        Random random = new Random();

        ArrayList<String> letters = new ArrayList<String>();

        for (int i = 0; i < num_cards; i++) {
            letters.add(words[i]);
        }

        int index;

        for (int i = 0; i < sqr; i++) {
            for (int j = 0; j < sqr; j++) {
                index = random.nextInt(letters.size());// chooses random number from 1 - 16
                cards[i][j] = letters.get(index);
                letters.remove(index);
            }
        }
    }

    public static void checkInput(String[][] cards, int z, int sqr) {
        int count = 0;
        while (true) {
            if (!gameOver(sqr)) {
                boolean second = true;
                boolean error = true;
                System.out.println("1st choice - co-oridinate1: ");
                String row11 = scanner.next();
                for (int q = 11; q <= (sqr * 11); q++) {
                    if (row11.equals("" + q)) {
                        error = false;
                        break;
                    }
                }
                if (error == true) {
                    System.out.println("incorrect format entered, try again");
                    continue;
                }
                int g1x = Integer.valueOf(row11.substring(0, 1)) - 1;
                int g1y = Integer.valueOf(row11.substring(1, 2)) - 1;
                error = true;
                if (!board[g1x][g1y].equals(" xxxxxxxxxxxx ")) {
                    System.out.println();

                    printBoard(z, sqr);
                    System.out.println();
                    System.out.println("Already Entered");
                    continue;

                } else {
                    board[g1x][g1y] = " " + cards[g1x][g1y] + " ";
                    printBoard(z, sqr);
                }
                while (second) {
                    System.out.println("2nd choice - co-oridinate2: ");
                    String row22 = scanner.next();

                    for (int q = 11; q <= (sqr * 11); q++) {
                        if (row22.equals("" + q)) {
                            error = false;
                            break;
                        }
                    }
                    if (error == true) {
//                    board[g1x][g1y] = " xxxxxxxxxxxx ";
                        printBoard(z, sqr);
                        System.out.println();
                        System.out.println("incorrect format entered, try again");
                        continue;
                    }
                    int g2x = Integer.valueOf(row22.substring(0, 1)) - 1;
                    int g2y = Integer.valueOf(row22.substring(1, 2)) - 1;
                    error = true;

                    if (!board[g2x][g2y].equals(" xxxxxxxxxxxx ")) {
//                    board[g1x][g1y] = " xxxxxxxxxxxx ";

                        System.out.println();
                        printBoard(z, sqr);
                        System.out.println();
                        System.out.println("Already Entered");
                        continue;

                    } else {
                        count++;
                        board[g2x][g2y] = " " + cards[g2x][g2y] + " ";
                        if (board[g1x][g1y].equals(board[g2x][g2y])) {
                            printBoard(z, sqr);
                            System.out.println("Correct!!!");
                            while (true) {
                                System.out.println("number of gusses so far....... " + count);
                                System.out.println("");
                                System.out.println("do you want to continue?");
                                System.out.println("yes....... press y");
                                System.out.println("no........ press n");
                                String yn = scanner.next();
                                if (yn.equals("y")) {
                                    printBoard(z, sqr);
                                    break;
                                } else if (yn.equals("n")) {
                                    return;
                                } else {
                                    System.out.println("incorrect input");
                                    continue;
                                }
                            }
                        } else {
                            printBoard(z, sqr);
                            System.out.println("");
                            System.out.println("Incorrect!!");
                            while (true) {
                                System.out.println("number of gusses so far....... " + count);
                                System.out.println("");
                                System.out.println("do you want to continue?");
                                System.out.println("yes....... press y");
                                System.out.println("no........ press n");
                                String yn = scanner.next();
                                if (yn.equals("y")) {
                                    board[g1x][g1y] = " xxxxxxxxxxxx ";
                                    board[g2x][g2y] = " xxxxxxxxxxxx ";
                                    printBoard(z, sqr);
                                    break;
                                } else if (yn.equals("n")) {
                                    return;
                                } else {
                                    System.out.println("incorrect input");
                                    continue;
                                }
                            }
                        }
                        second = false;
                    }
                }
            } else {
                System.out.println("you completed the game in " + count + " guesses");
                break;
            }
        }
    }

    public static boolean gameOver(int sqr) {
        for (int i = 0; i < sqr; i++) {
            for (int j = 0; j < sqr; j++) {
                if (board[i][j].equals(" xxxxxxxxxxxx ")) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void easy(int i, int sqr) {
        shuffleCards(16, sqr);

        for (int x = 0; x < i; x++) {
            for (int j = 0; j < i; j++) {
                board[x][j] = " xxxxxxxxxxxx ";
            }
        }
        printBoard(i, sqr);
        checkInput(cards, i, sqr);
    }

    public static void medium(int i, int sqr) {
        shuffleCards(36, sqr);

        for (int x = 0; x < i; x++) {
            for (int j = 0; j < i; j++) {
                board[x][j] = " xxxxxxxxxxxx ";
            }
        }
        printBoard(i, sqr);
        checkInput(cards, i, sqr);
    }

    public static void hard(int i, int sqr) {   
        shuffleCards(64, sqr);

        for (int x = 0; x < i; x++) {
            for (int j = 0; j < i; j++) {
                board[x][j] = " xxxxxxxxxxxx ";
            }
        }
        printBoard(i, sqr);
        checkInput(cards, i, sqr);
    }

}
