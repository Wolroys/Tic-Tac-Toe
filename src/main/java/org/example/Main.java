package org.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
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

            makeMove(gameBoard, place, "user");
            printGameBoard(gameBoard);
            System.out.println("------------------");
            Random random = new Random();
            int rand = random.nextInt(9) + 1;

            System.out.println("Bot has made a turn");
            makeMove(gameBoard, rand, "Bot");
            printGameBoard(gameBoard);

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
        if (user.equals("Bot"))
            sign = '0';

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

}