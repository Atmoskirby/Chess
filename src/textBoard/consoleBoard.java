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
    private static String board[][] = new String[9][9];
    static Map map = new HashMap();
    static Map ints = new HashMap();
    static String piece1;
    static String piece2;
    static File file = new File("C://tmp/board.txt");
    static FileReader reader;
    static BufferedReader br;
    static boolean shortCode = false;
    
    
    public static void main(String[] args) throws IOException{
        reader = new FileReader(file);
        br = new BufferedReader(reader);
        //[numbers][letters a-h]
        String startingPieces[] = {"1"," R "," N "," B "," K "," Q "," B "," N "," R "};
        //-64
        map.put('K', "K");
        map.put('Q', "Q");
        map.put('B', "B");
        map.put('N', "N");
        map.put('R', "R");
        map.put('P', "P");
        
        ints.put('A', "1");
        ints.put('B', "2");
        ints.put('C', "3");
        ints.put('D', "4");
        ints.put('E', "5");
        ints.put('F', "6");
        ints.put('G', "7");
        ints.put('H', "8");
        
         for(int i = 0; i < 9;i++) {
            for(int o = 0; o < 9;o++) {
                char top = 65;
                if(o == 0) {
                    board[o][i] = " " + Character.toString((char) (top + (i - 1))) + " ";
                    board[i][o] = "" +  (9 - i);
                } else {
                    board[i][o] = " - ";
                    board[2][o] = " P ";
                    board[7][o] = " p ";
                    if(o < 9) {
                        board[1][o] = startingPieces[o];
                        board[8][o] = startingPieces[o].toLowerCase();
                    }
                }
            }
        }
         
         
         
        printBoard(br);
        
        
        
        for(int i = 0; i < 9;i++) {
            for(int o = 0; o < 9;o++) {
                System.out.print(board[i][o]);
                if(o == 8) {
                    System.out.println();
                }
            }
        }
        
    }
    
    public static void pieces(String command) throws IOException {
        command = command.toUpperCase();
        if(command.length() <= 5) {
            piece1 = command.substring(0,2);
            piece2 = command.substring(2,4);
            shortCode = true;
        } else {
            piece1 = command.substring(0,4);
            piece2 = command.substring(4,8);
        }
    }
    
    
    public static String piece(char pieceCode) {
        return (String) map.get(pieceCode);
    }
    
    public static void printBoard(BufferedReader br) throws IOException {
        board[0][0] = "#";
        while(br.ready()) {
            pieces(br.readLine());
            printPieces();
        }
        
        
        //Move pieces
        
    }
    
    public static void printPieces() throws IOException {
        if(shortCode) {
            String piece = board[9 - Integer.parseInt(Character.toString(piece1.charAt(1)))][Integer.parseInt((String) ints.get(piece1.charAt(0)))];
            board[9 - Integer.parseInt(Character.toString(piece1.charAt(1)))][Integer.parseInt((String) ints.get(piece1.charAt(0)))] = " - ";
            board[9 - Integer.parseInt(Character.toString(piece2.charAt(1)))][Integer.parseInt((String) ints.get(piece2.charAt(0)))] = piece;
        } else {
            board[9 - Integer.parseInt(Character.toString(piece1.charAt(3)))][Integer.parseInt((String) ints.get(piece1.charAt(2)))] = " - ";
            board[9 - Integer.parseInt(Character.toString(piece2.charAt(3)))][Integer.parseInt((String) ints.get(piece2.charAt(2)))] = " " + piece2.charAt(0) + " ";
        }
        printBoard(br);
    }
}
