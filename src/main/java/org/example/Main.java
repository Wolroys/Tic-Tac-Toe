package org.example;

import java.util.*;

public class Main {
    private static List<Integer> playerTurns = new ArrayList<>();
    private static List<Integer> botTurns = new ArrayList<>();

    public static void main(String[] args) {
        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}
        };

        printGameBoard(gameBoard);

        while (true) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Please enter your placement (1-9): ");
            int place = scanner.nextInt();
            while (playerTurns.contains(place) || botTurns.contains(place)){
                System.out.println("This position is taken. Choose an other position:");
                place = scanner.nextInt();
            }

            makeMove(gameBoard, place, "player");
            printGameBoard(gameBoard);
            String resultOfGame = checkWinner();

            if (!resultOfGame.isEmpty()){
                System.out.println(resultOfGame);
                break;
            }

            System.out.println("------------------");
            Random random = new Random();
            int rand = random.nextInt(9) + 1;

            while (playerTurns.contains(rand) || botTurns.contains(rand)) {
                System.out.println("This position is taken. Choose an other position:");
                rand = random.nextInt(9) + 1;
            }
            System.out.println("Bot has made a turn");
            makeMove(gameBoard, rand, "Bot");
            printGameBoard(gameBoard);
            resultOfGame = checkWinner();

            if (!resultOfGame.isEmpty()){
                System.out.println(resultOfGame);
                break;
            }
        }
    }

    public static void printGameBoard(char[][] gameBoard){
        for (char[] row : gameBoard){
            for (char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void makeMove(char[][] gameBoard, int place, String user){
        char sign = 'X';
        if (user.equals("player")){
            playerTurns.add(place);
        }
        if (user.equals("Bot")) {
            sign = '0';
            botTurns.add(place);
        }
        switch (place){
            case 1 -> gameBoard[0][0] = sign;
            case 2 -> gameBoard[0][2] = sign;
            case 3 -> gameBoard[0][4] = sign;
            case 4 -> gameBoard[2][0] = sign;
            case 5 -> gameBoard[2][2] = sign;
            case 6 -> gameBoard[2][4] = sign;
            case 7 -> gameBoard[4][0] = sign;
            case 8 -> gameBoard[4][2] = sign;
            case 9 -> gameBoard[4][4] = sign;
        }
    }

    public static String checkWinner(){
        List<Integer> topRow = Arrays.asList(1, 2, 3);
        List<Integer> middleRow = Arrays.asList(4, 5, 6);
        List<Integer> lastRow = Arrays.asList(7, 8, 9);
        List<Integer> leftCol = Arrays.asList(1, 4, 7);
        List<Integer> rightCol = Arrays.asList(3, 6, 9);
        List<Integer> rCross = Arrays.asList(1, 5, 9);
        List<Integer> lCross = Arrays.asList(3, 5, 7);
        List<Integer> middleCol = Arrays.asList(2, 5, 8);

        List<List<Integer>> variantsOfWin = new ArrayList<>();
        variantsOfWin.add(topRow);
        variantsOfWin.add(middleRow);
        variantsOfWin.add(lastRow);
        variantsOfWin.add(leftCol);
        variantsOfWin.add(rightCol);
        variantsOfWin.add(lCross);
        variantsOfWin.add(rCross);
        variantsOfWin.add(middleCol);

        for (List<Integer> v : variantsOfWin){
            if (playerTurns.containsAll(v))
                return "Congratulations you have won!";
            else if (botTurns.containsAll(v))
                return "You have lost :(";
            else if ((botTurns.size() + playerTurns.size()) == 9)
                return "It's a tie";
        }

        return "";
    }

}