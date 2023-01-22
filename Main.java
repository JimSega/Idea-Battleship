package battleship;


public class Main {

    public static void main(String[] args) {
        Battle player1 = new Battle();
        Battle player2 = new Battle();
        player1.createMap("1");
        for (int cells = 1; cells < 5; cells++) {

            if (cells == 3) {
                player1.playerShips(3, 2);
                player1.ship++;
            }
            player1.playerShips (6 - cells, player1.ship);
            player1.ship++;
        }
        Battle.moveAnotherPlayer();
        player2.createMap("2");
        for (int cells = 1; cells < 5; cells++) {

            if (cells == 3) {
                player2.playerShips(3, 2);
                player2.ship++;
            }
            player2.playerShips (6 - cells, player2.ship);
            player2.ship++;
        }
        //System.out.println("\nThe game starts!");

        while (player1.game && player2.game) {
            Battle.moveAnotherPlayer();
            player1.fogOfWar(player2.fog);
            System.out.println("---------------------");
            player1.paintMap();
            System.out.println("Player 1, it's your turn:");
            player2.testShot(Battle.shot());
            Battle.moveAnotherPlayer();
            player2.fogOfWar(player1.fog);
            System.out.println("---------------------");
            player2.paintMap();
            System.out.println("Player 2, it's your turn:");
            player1.testShot(Battle.shot());
        }

    }


}

