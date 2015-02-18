/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textBoard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class consoleBoard {

    private static String board[][] = new String[8][8];
    static Map map = new HashMap();
    static Map ints = new HashMap();
    static String piece1;
    static String piece2;
    static File file = new File("C://tmp/board.txt");
    static FileReader reader;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        reader = new FileReader(file);
        br = new BufferedReader(reader);
        //[numbers][letters a-h]
        String startingPieces[] = {" R ", " N ", " B ", " K ", " Q ", " B ", " N ", " R "};
        //-64
        map.put('K', "K");
        map.put('Q', "Q");
        map.put('B', "B");
        map.put('N', "N");
        map.put('R', "R");
        map.put('P', "P");

        ints.put('A', "0");
        ints.put('B', "1");
        ints.put('C', "2");
        ints.put('D', "3");
        ints.put('E', "4");
        ints.put('F', "5");
        ints.put('G', "6");
        ints.put('H', "7");
        System.out.println("# A  B  C  D  E  F  G  H ");
        for (int i = 0; i < 8; i++) {
            for (int o = 0; o < 8; o++) {
                board[i][o] = " - ";
                board[1][o] = " P ";
                board[6][o] = " p ";
                if (o < 8) {
                    board[0][o] = startingPieces[o];
                    board[7][o] = startingPieces[o].toLowerCase();

                }
            }
        }

        printBoard(br);

        for (int i = 0; i < 8; i++) {
            System.out.print(9 - (i + 1));
            for (int o = 0; o < 8; o++) {
                System.out.print(board[i][o]);
                if (o == 7) {
                    System.out.println();
                }
            }
        }

    }


    public static String piece(char pieceCode) {
        return (String) map.get(pieceCode);
    }

    public static void printBoard(BufferedReader br) throws IOException {
        while (br.ready()) {
            pieces(br.readLine());
            printPieces();
        }
    }
    
    
    public static void pieces(String command) {
        command = command.toUpperCase();
            piece1 = command.substring(0, 4);
            piece2 = command.substring(4, 8);
            if(piece1.contains("L")) {
                piece1 = piece1.toLowerCase();
            }
            if(piece2.contains("L")) {
                piece2 = piece2.toLowerCase();
            }
    }

    public static void printPieces() throws IOException {
        
        String piece1LayoutChar = (String) ints.get(piece1.toUpperCase().charAt(2));
        String piece2LayoutChar = (String) ints.get(piece2.toUpperCase().charAt(2));
        
        
        board[8 - Integer.parseInt(Character.toString(piece1.charAt(3)))][Integer.parseInt(piece1LayoutChar)] = " - ";
        board[8 - Integer.parseInt(Character.toString(piece2.charAt(3)))][Integer.parseInt(piece2LayoutChar)] = " " + piece2.charAt(0) + " ";
        printBoard(br);
    }
}
