package battleship;

import java.io.IOException;
import java.util.Scanner;

public class Battle {
    char [][] fog = new char[11][11];


    static String [] ships = new String[]{"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
    int ship = 0;

    boolean game = true;
    public void createMap(String str) {
        System.out.println("Player "+str+", place your ships on the game field");
        System.out.print(" ");
        for (int i = 1; i < 11; i++) {
            System.out.print(i);
            if (i < 10) {
                System.out.print(" ");
            }
        }
        System.out.println();
        for (char a = 'A'; a < 'K'; a++) {

            System.out.print(a + " ");
            for (int j = 1; j < 11; j++) {
                System.out.print("~");
                if (j < 10) {
                    System.out.print(" ");
                }
                fog[a-64][j] = '~';
            }
            System.out.println();

        }
    }

    public void createMapPlayer(char ch1, char ch2, int coord1Int, int coord2Int, int cells, int ship) {
        outerloop:
        {
            for (char ch = ch1; ch <= ch2; ch++) {
                for (int coord = coord1Int; coord <= coord2Int; coord++) {
                    if (coord == 10 && ch == 'J') {
                        if (fog[ch - 64][coord] == 'O' || fog[ch - 64 - 1][coord] == 'O' || fog[ch - 64][coord - 1] == 'O'
                                || fog[ch - 64 - 1][coord - 1] == 'O') {
                            System.out.println("Error! You placed it too close to another one. Try again:");

                            playerShips(cells, ship);
                            break outerloop;
                        }
                    } else if (coord == 10) {
                        if (fog[ch - 64][coord] == 'O' || fog[ch - 64 - 1][coord] == 'O' || fog[ch - 64 + 1][coord] == 'O'
                                || fog[ch - 64][coord - 1] == 'O' || fog[ch - 64 - 1][coord - 1] == 'O' || fog[ch - 64 + 1][coord - 1] == 'O') {
                            System.out.println("Error! You placed it too close to another one. Try again:");

                            playerShips(cells, ship);
                            break outerloop;
                        }

                    } else if (ch == 'J') {
                        if (fog[ch - 64][coord] == 'O' || fog[ch - 64 - 1][coord] == 'O' || fog[ch - 64][coord - 1] == 'O'
                                || fog[ch - 64][coord + 1] == 'O' || fog[ch - 64 - 1][coord - 1] == 'O'
                                || fog[ch - 64 - 1][coord + 1] == 'O') {
                            System.out.println("Error! You placed it too close to another one. Try again:");

                            playerShips(cells, ship);
                            break outerloop;
                        }
                    }
                    else if (fog[ch - 64][coord] == 'O' || fog[ch - 64 - 1][coord] == 'O' || fog[ch - 64 + 1][coord] == 'O' || fog[ch - 64][coord - 1] == 'O'
                            || fog[ch - 64][coord + 1] == 'O' || fog[ch - 64 - 1][coord - 1] == 'O' || fog[ch - 64 + 1][coord + 1] == 'O'
                            || fog[ch - 64 - 1][coord + 1] == 'O' || fog[ch - 64 + 1][coord - 1] == 'O') {
                        System.out.println("Error! You placed it too close to another one. Try again:");

                        playerShips(cells, ship);
                        break outerloop;
                    }
                }
            }


            {
                System.out.println();
                System.out.print(" ");

                for (int i = 1; i < 11; i++) {
                    System.out.print(i);
                    if (i < 10) {
                        System.out.print(" ");
                    }
                }
                System.out.println();
                for (char a = 'A'; a < 'K'; a++) {
                    System.out.print(a + " ");

                    for (int j = 1; j < 11; j++) {
                        if (j >= coord1Int && j <= coord2Int && a == ch1) {
                            fog[a - 64][j] = 'O';
                        } else if (j == coord1Int && a >= ch1 && a <= ch2) {
                            fog[a - 64][j] = 'O';

                        }

                        System.out.print(fog[a - 64][j]);
                        if (j < 10) {
                            System.out.print(" ");
                        }

                    }
                    System.out.println();

                }
            }
        }

    }

    public void playerShips (int cells, int ship) {
        System.out.println("Enter the coordinates of the " + ships[ship] +" ("+cells+" cells):");
        Scanner scaner = new Scanner(System.in);
        String str1 = scaner.next();
        String str2 = scaner.next();
        char ch1 = str1.charAt(0);
        char coord1 = str1.charAt(1);
        int coord1Int = Character.digit(coord1, 10);
        if (str1.length() == 3 && coord1 == '1' && str1.charAt(2) == '0') {
            coord1Int = 10;
        }
        char ch2 = str2.charAt(0);
        char coord2 = str2.charAt(1);
        int coord2Int = Character.digit(coord2, 10);
        if (str2.length() == 3 && coord2 == '1' && str2.charAt(2) == '0') {
            coord2Int = 10;
        }



        if (ch1 == ch2 && Math.abs(coord2Int-coord1Int) == cells -1) {
            createMapPlayer(ch1, ch2, Math.min(coord1Int, coord2Int), Math.max(coord1Int, coord2Int), cells, ship);
        } else if (Math.abs(ch1 - ch2) == cells - 1 && coord2Int == coord1Int){
            if (ch2 > ch1) {
                createMapPlayer(ch1, ch2, coord1Int, coord2Int, cells, ship);
            } else {createMapPlayer(ch2, ch1, coord1Int, coord2Int, cells, ship);}
        } else {
            System.out.println("Error!");

            playerShips(cells, ship);
        }
    }
    public void paintMap () {
        System.out.print(" ");
        for (int i = 1; i < 11; i++) {
            System.out.print(i);
            if (i < 10) {
                System.out.print(" ");
            }
        }
        System.out.println();
        for (char a = 'A'; a < 'K'; a++) {

            System.out.print(a + " ");
            for (int j = 1; j < 11; j++) {
                System.out.print(fog[a-64][j]);
                if (j < 10) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static String shot () {
        System.out.println("\nTake a shot!\n");
        Scanner scaner = new Scanner(System.in);
        String str = scaner.next();
        char ch = str.charAt(0);
        char coord = str.charAt(1);
        int coordInt = Character.digit(coord, 10);
        if (str.length() == 3 && coord == '1' && str.charAt(2) == '0') {
            coordInt = 10;
        } else if (str.length() == 3 && coord != '1' || str.length() == 3 && str.charAt(2) != '0') {
            str = "Error! You entered the wrong coordinates! Try again:";
        }
        if (ch < 'A' || ch > 'J' || coordInt < 1) {
            str = "Error! You entered the wrong coordinates! Try again:";
        }
        return str;
    }

    public void testShot (String str) {
        if (str.equals("Error! You entered the wrong coordinates! Try again:")) {
            System.out.println("\n"+str);
            testShot(shot());
        } else {
            char ch = str.charAt(0);
            char coord = str.charAt(1);
            int coordInt = Character.digit(coord, 10);
            if (str.length() == 3 && coord == '1' && str.charAt(2) == '0') {
                coordInt = 10;
            }
            if (fog[ch - 64][coordInt] == 'O' || fog[ch - 64][coordInt] == 'X') {
                fog[ch - 64][coordInt] = 'X';
                fogOfWar(fog);
                outForTestShot:
                {
                    for (int i = 1; i < 11; i++) {
                        for (int j = 1; j < 11; j++) {
                            if (fog[i][j] == 'O') {
                                break outForTestShot;
                            }
                        }
                    }
                    System.out.println("You sank the last ship. You won. Congratulations!");
                    game = false;
                }
                testSank(ch, coordInt);

            } else if (fog [ch - 64][coordInt]  == '~' || fog [ch - 64][coordInt]  == 'M') {
                fog [ch - 64][coordInt] = 'M';
                fogOfWar(fog);
                System.out.println("\nYou missed!\n");
                //paintMap();
            }
        }
    }

    public void testSank (char ch, int coordInt) {
        if (coordInt == 10 && ch == 'J') {
            if (fog[ch - 64 - 1][coordInt] == 'O' || fog[ch - 64][coordInt - 1] == 'O'
                    || fog[ch - 64 - 1][coordInt - 1] == 'O') {
                System.out.println("\nYou hit a ship!\n");
            } else System.out.println("\nYou sank a ship!\n");
        } else if (coordInt == 10) {
            if (fog[ch - 64 - 1][coordInt] == 'O' || fog[ch - 64 + 1][coordInt] == 'O'
                    || fog[ch - 64][coordInt - 1] == 'O' || fog[ch - 64 - 1][coordInt - 1] == 'O'
                    || fog[ch - 64 + 1][coordInt - 1] == 'O') {
                System.out.println("\nYou hit a ship!\n");
            } else System.out.println("\nYou sank a ship!\n");

        } else if (ch == 'J') {
            if (fog[ch - 64 - 1][coordInt] == 'O' || fog[ch - 64][coordInt - 1] == 'O'
                    || fog[ch - 64][coordInt + 1] == 'O' || fog[ch - 64 - 1][coordInt - 1] == 'O'
                    || fog[ch - 64 - 1][coordInt + 1] == 'O') {
                System.out.println("\nYou hit a ship!\n");
            } else System.out.println("\nYou sank a ship!\n");
        }
        else if (fog[ch - 64 - 1][coordInt] == 'O' || fog[ch - 64 + 1][coordInt] == 'O' || fog[ch - 64][coordInt - 1] == 'O'
                || fog[ch - 64][coordInt + 1] == 'O' || fog[ch - 64 - 1][coordInt - 1] == 'O' || fog[ch - 64 + 1][coordInt + 1] == 'O'
                || fog[ch - 64 - 1][coordInt + 1] == 'O' || fog[ch - 64 + 1][coordInt - 1] == 'O') {
            System.out.println("\nYou hit a ship!\n");
        } else System.out.println("\nYou sank a ship!\n");
    }



    public void fogOfWar (char [][] fogVs) {
        System.out.println(" ");
        System.out.print(" ");
        for (int i = 1; i < 11; i++) {
            System.out.print(i);
            if (i < 10) {
                System.out.print(" ");
            }
        }
        System.out.println();
        for (char a = 'A'; a < 'K'; a++) {

            System.out.print(a + " ");
            for (int j = 1; j < 11; j++) {
                if (fogVs[a-64][j] == 'O') {
                    System.out.print('~');
                } else {System.out.print(fogVs[a-64][j]);}
                if (j < 10) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void moveAnotherPlayer () {
        System.out.println("Press Enter and pass the move to another player");
        try {
            System.in.read();
            System.out.print("\n".repeat(99));
            System.out.println("...\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
